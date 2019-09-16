//package org.apdoer.manager.controller;
//
//import org.apdoer.manager.annotations.SystemControllerLog;
//import org.apdoer.manager.config.DataScope;
//import org.apdoer.manager.exception.BadRequestException;
//import org.apdoer.manager.handler.JobHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
///**
//* @author apdoer
//* @date 2019-03-29
//*/
//@RestController
//@RequestMapping("api")
//public class JobController {
//
//    @Autowired
//    private JobHandler jobHandler;
//
//    @Autowired
//    private DataScope dataScope;
//
//    private static final String ENTITY_NAME = "job";
//
//    @SystemControllerLog("查询岗位")
//    @GetMapping(value = "/job")
//    @PreAuthorize("hasAnyRole('ADMIN','USERJOB_ALL','USERJOB_SELECT','USER_ALL','USER_SELECT')")
//    public ResponseEntity getJobs(JobQueryCriteria criteria,
//                                  Pageable pageable){
//        // 数据权限
//        criteria.setDeptIds(dataScope.getDeptIds());
//        return new ResponseEntity(jobHandler.queryAll(criteria, pageable),HttpStatus.OK);
//    }
//
//    @SystemControllerLog("新增岗位")
//    @PostMapping(value = "/job")
//    @PreAuthorize("hasAnyRole('ADMIN','USERJOB_ALL','USERJOB_CREATE')")
//    public ResponseEntity create(@Validated @RequestBody JobPo resources){
//        if (resources.getId() != null) {
//            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
//        }
//        return new ResponseEntity(jobHandler.create(resources),HttpStatus.CREATED);
//    }
//
//    @SystemControllerLog("修改岗位")
//    @PutMapping(value = "/job")
//    @PreAuthorize("hasAnyRole('ADMIN','USERJOB_ALL','USERJOB_EDIT')")
//    public ResponseEntity update(@Validated(JobPo.Update.class) @RequestBody JobPo resources){
//        jobHandler.update(resources);
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
//
//    @SystemControllerLog("删除岗位")
//    @DeleteMapping(value = "/job/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','USERJOB_ALL','USERJOB_DELETE')")
//    public ResponseEntity delete(@PathVariable Long id){
//        jobHandler.delete(id);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//}