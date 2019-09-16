//package org.apdoer.manager.controller;
//
//import org.apdoer.manager.annotations.SystemControllerLog;
//import org.apdoer.manager.exception.BadRequestException;
//import org.apdoer.manager.model.pojo.PermissionPo;
//import org.apdoer.manager.service.PermissionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * @author apdoer
// * @date 2018-12-03
// */
//@RestController
//@RequestMapping("api")
//public class PermissionController {
//
//    @Autowired
//    private PermissionService permissionService;
//
//    private static final String ENTITY_NAME = "permission";
//
//    /**
//     * 返回全部的权限，新增角色时下拉选择
//     * @return
//     */
//    @GetMapping(value = "/permissions/tree")
//    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_CREATE','PERMISSION_EDIT','ROLES_SELECT','ROLES_ALL')")
//    public ResponseEntity getTree(){
//        return new ResponseEntity(permissionService.getPermissionTree(permissionService.findByPid(0L)),HttpStatus.OK);
//    }
//
//    @SystemControllerLog("查询权限")
//    @GetMapping(value = "/permissions")
//    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_SELECT')")
//    public ResponseEntity getPermissions(CommonQueryCriteria criteria){
//        List<PermissionDTO> permissionDTOS = permissionService.queryAll(criteria);
//        return new ResponseEntity(permissionService.buildTree(permissionDTOS),HttpStatus.OK);
//    }
//
//    @SystemControllerLog("新增权限")
//    @PostMapping(value = "/permissions")
//    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_CREATE')")
//    public ResponseEntity create(@Validated @RequestBody PermissionPo resources){
//        if (resources.getId() != null) {
//            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
//        }
//        return new ResponseEntity(permissionService.create(resources),HttpStatus.CREATED);
//    }
//
//    @SystemControllerLog("修改权限")
//    @PutMapping(value = "/permissions")
//    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_EDIT')")
//    public ResponseEntity update(@Validated(PermissionPo.Update.class) @RequestBody PermissionPo resources){
//        permissionService.update(resources);
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
//
//    @SystemControllerLog("删除权限")
//    @DeleteMapping(value = "/permissions/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_DELETE')")
//    public ResponseEntity delete(@PathVariable Long id){
//        permissionService.delete(id);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//}
