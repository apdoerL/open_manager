package org.apdoer.manager.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.check.RoleCheckService;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.handler.RedisHandler;
import org.apdoer.manager.handler.RoleHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;
import org.apdoer.manager.service.RoleService;
import org.apdoer.manager.service.UserService;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/19 19:06
 */
@Component
@Slf4j
public class RoleHandlerImpl implements RoleHandler {
    private RoleCheckService roleCheckService;
    private UserService userService;
    private RoleService roleService;
    private RedisHandler redisHandler;


    @Autowired
    public void setRedisHandler(RedisHandler redisHandler) {
        this.redisHandler = redisHandler;
    }
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setRoleCheckService(RoleCheckService roleCheckService) {
        this.roleCheckService = roleCheckService;
    }

    @Override
    public ResultVo queryRoleList(PageBean<RoleVo> pageBean, RoleVo roleVo) {
        ResultVo resultVo = roleCheckService.checkQueryRoleListParam(pageBean,roleVo);
        if (resultVo == null || resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        // todo
        return null;
    }

    @Override
    public ResultVo queryAllCreatedRole() {
        // todo
        return null;
    }

    @Override
    public ResultVo createRole(RoleCreateVo roleCreateVo) {
        ResultVo resultVo = roleCheckService.checkRoleCreateParam(roleCreateVo);
        if (resultVo == null || resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        // todo
        return null;
    }

    @Override
    public ResultVo updateRole(RoleUpdateVo roleUpdateVo) {
        ResultVo resultVo = roleCheckService.checkRoleUpdateParam(roleUpdateVo);
        if (resultVo == null || resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        // todo
        return null;
    }

    @Override
    public ResultVo deleteRole(Integer roleId) {
        if (roleId == null){
            return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.REQUEST_PARAM_INVALID.getCode(),ExceptionCodeEnum.REQUEST_PARAM_INVALID.getValue());
        }
        // todo
        return null;
    }

    @Override
    public ResultVo getRoleById(Integer roleId) {
        if (roleId == null){
            return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.REQUEST_PARAM_INVALID.getCode(),ExceptionCodeEnum.REQUEST_PARAM_INVALID.getValue());
        }
        return ResultVoBuildUtils.buildSuccessResultVo(userService.getPermissionList(roleId));
    }

    @Override
    public ResultVo queryPermLevel() {
        // todo
        return null;
    }

    @Override
    public ResultVo updateRolePerm(RolePermUpdateVo rolePermUpdateVo) {
        // todo 更新角色权限

        // 清缓存
        return null;
    }

}
