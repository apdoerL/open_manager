package org.apdoer.manager.handler.impl;

import org.apdoer.manager.check.WebGroupCheckService;
import org.apdoer.manager.constants.ManagerConstant;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.handler.WebGroupHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;
import org.apdoer.manager.service.WebGroupService;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author apdoer
 */
@Service
public class WebGroupHandlerImpl implements WebGroupHandler {
    private WebGroupService webGroupService;
    private WebGroupCheckService webGroupCheckService;

    @Autowired
    public void setWebGroupCheckService(WebGroupCheckService webGroupCheckService) {
        this.webGroupCheckService = webGroupCheckService;
    }

    @Autowired
    public void setWebGroupService(WebGroupService webGroupService) {
        this.webGroupService = webGroupService;
    }

    @Override
    public ResultVo queryGroupList(PageBean<WebGroupVo> pageBean, WebGroupVo webGroupVo) {
        ResultVo resultVo = webGroupCheckService.checkQueryGroupParam(pageBean,webGroupVo);
        if (resultVo == null || resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        // todo
        return null;
    }

    @Override
    public ResultVo createGroup(GroupCreateVo groupCreateVo) {
        ResultVo resultVo = webGroupCheckService.checkCreateGroupParam(groupCreateVo);
        if (resultVo == null || resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        //todo

        return null;
    }

    @Override
    public ResultVo deletGroup(Integer groupId) {
        return null;
    }

    @Override
    public ResultVo updateGroup(GroupUpdateVo groupUpdateVo) {
        ResultVo resultVo = webGroupCheckService.checkUpdateGroupParam(groupUpdateVo);
        if (resultVo == null || resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        //todo
        return null;
    }

    @Override
    public ResultVo addGroupMember(GroupMemberVo groupMemberVo) {
        ResultVo resultVo = webGroupCheckService.checkAddGroupMemberParam(groupMemberVo);
        if (resultVo == null || resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        // todo
        return null;
    }

    @Override
    public ResultVo updateGroupStatus(Integer groupId, Integer status) {
        if (status < ManagerConstant.STATUS_DISABLED || status > ManagerConstant.STATUS_DELETE){
            return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.REQUEST_PARAM_INVALID.getCode(),ExceptionCodeEnum.REQUEST_PARAM_INVALID.getValue());
        }
        // todo
        return null;
    }
}
