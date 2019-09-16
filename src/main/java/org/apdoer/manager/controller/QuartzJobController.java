package org.apdoer.manager.controller;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.annotations.SystemControllerLog;
import org.apdoer.manager.exception.BadRequestException;
import org.apdoer.manager.handler.QuartzJobHandler;
import org.apdoer.manager.model.dto.JobQueryCriteria;
import org.apdoer.manager.model.pojo.QuartzJobPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author apdoer
 * @date 2019-01-07
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class QuartzJobController {

    private static final String ENTITY_NAME = "quartzJob";

    @Autowired
    private QuartzJobHandler quartzJobHandler;

    @SystemControllerLog("查询定时任务")
    @GetMapping(value = "/jobs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_SELECT')")
    public ResponseEntity getJobs(JobQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(quartzJobHandler.queryAll(criteria,pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/jobLogs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_SELECT')")
    public ResponseEntity getJobLogs(JobQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(quartzJobHandler.queryAllLog(criteria,pageable), HttpStatus.OK);
    }

    @SystemControllerLog("新增定时任务")
    @PostMapping(value = "/jobs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_CREATE')")
    public ResponseEntity create(@Validated @RequestBody QuartzJobPo resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(quartzJobHandler.create(resources),HttpStatus.CREATED);
    }

    @SystemControllerLog("修改定时任务")
    @PutMapping(value = "/jobs")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_EDIT')")
    public ResponseEntity update(@Validated(QuartzJobPo.Update.class) @RequestBody QuartzJobPo resources){
        quartzJobHandler.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @SystemControllerLog("更改定时任务状态")
    @PutMapping(value = "/jobs/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_EDIT')")
    public ResponseEntity updateIsPause(@PathVariable Long id){
        quartzJobHandler.updateIsPause(quartzJobHandler.findById(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @SystemControllerLog("执行定时任务")
    @PutMapping(value = "/jobs/exec/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_EDIT')")
    public ResponseEntity execution(@PathVariable Long id){
        quartzJobHandler.execution(quartzJobHandler.findById(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @SystemControllerLog("删除定时任务")
    @DeleteMapping(value = "/jobs/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_ALL','JOB_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        quartzJobHandler.delete(quartzJobHandler.findById(id));
        return new ResponseEntity(HttpStatus.OK);
    }
}
