package org.apdoer.manager.controller;

import org.apdoer.manager.annotations.SystemControllerLog;
import org.apdoer.manager.handler.RoleHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author apdoer
 * @date 2018-12-03
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleHandler roleHandler;


    /**
     * 查询角色列表
     * @param pageBean
     * @param roleVo
     * @return
     */
    @GetMapping("/roleList")
    @SystemControllerLog("查询角色列表")
    public ResultVo queryRolesList(PageBean<RoleVo>pageBean, RoleVo roleVo){
        return roleHandler.queryRoleList(pageBean,roleVo);
    }

    /**
     * 查询所有可用角色,用户选择角色时展示
     * @return
     */
    @GetMapping("/allRole")
    @SystemControllerLog("查询所有可用角色")
    public ResultVo queryAllRole(){
        return roleHandler.queryAllCreatedRole();
    }

    /**
     * 新增角色
     * @param roleCreateVo
     * @return
     */
    @PostMapping("/role")
    @SystemControllerLog("新增角色")
    public ResultVo createRole(@RequestBody @Validated RoleCreateVo roleCreateVo){
        return roleHandler.createRole(roleCreateVo);
    }

    /**
     * 更新角色信息
     * @param roleUpdateVo
     * @return
     */
    @PutMapping("/role")
    @SystemControllerLog("更新角色信息")
    public ResultVo updateRole(@RequestBody @Validated RoleUpdateVo roleUpdateVo){
        return roleHandler.updateRole(roleUpdateVo);
    }

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    @DeleteMapping("/role/{roleId}")
    @SystemControllerLog("删除角色")
    public ResultVo deleteRole(@PathVariable("roleId") Integer roleId){
        return roleHandler.deleteRole(roleId);
    }
    /**
     * 查询单个角色的权限
     * @param roleId
     * @return
     */
    @GetMapping("/perms/{roleId}")
    @SystemControllerLog("查询单个角色的权限")
    public ResultVo getPermsByRoleId(@PathVariable("roleId") Integer roleId){
        return roleHandler.getPermById(roleId);
    }

    /**
     * 查询权限层级列表
     * @return
     */
    @GetMapping("/permList")
    @SystemControllerLog("查询权限层级列表")
    public ResultVo getPermLevel(){
        return roleHandler.queryPermLevel();
    }


    /**
     * 更新角色权限
     * @param rolePermUpdateVo
     * @return
     */
    @PutMapping("/perm")
    @SystemControllerLog("更新角色权限")
    public ResultVo updateRolePerm(@Validated @RequestBody RolePermUpdateVo rolePermUpdateVo){
        return roleHandler.updateRolePerm(rolePermUpdateVo);
    }

    @PutMapping("/roleStatus/{roleId}/{status}")
    @SystemControllerLog("更新角色状态")
    public ResultVo updateRoleStatus(@PathVariable("roleId") Integer roleId,@PathVariable("status") Integer status){
        return roleHandler.updateRoleStatus(roleId,status);
    }

//
//    @SystemControllerLog("修改角色菜单")
//    @PutMapping(value = "/roles/menu")
//    @PreAuthorize("hasAnyRole('ADMIN','ROLES_ALL','ROLES_EDIT')")
//    public ResponseEntity updateMenu(@RequestBody RolePo resources){
//        roleService.updateMenu(resources,roleService.findById(resources.getId()));
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }

}
