package org.apdoer.manager.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.check.RoleCheckService;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.handler.RoleHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.model.vo.RoleCreateVo;
import org.apdoer.manager.model.vo.RoleUpdateVo;
import org.apdoer.manager.model.vo.RoleVo;
import org.apdoer.manager.service.RoleService;
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
    private RoleService roleService;



    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
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
        return null;
    }
}
