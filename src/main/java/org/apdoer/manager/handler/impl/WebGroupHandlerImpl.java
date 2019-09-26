package org.apdoer.manager.handler.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apdoer.manager.check.WebGroupCheckService;
import org.apdoer.manager.constants.ManagerConstant;
import org.apdoer.manager.enums.CommonStatusEnum;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.handler.WebGroupHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.pojo.WebGroupPo;
import org.apdoer.manager.model.vo.*;
import org.apdoer.manager.service.WebGroupService;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


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
        PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
        List<WebGroupVo> groupVos = webGroupService.queryGroupList(webGroupVo);
        PageInfo<WebGroupVo>pageInfo = new PageInfo<>(groupVos);
        return ResultVoBuildUtils.buildSuccessResultVo(pageInfo);
    }

    @Override
    public ResultVo createGroup(GroupCreateVo groupCreateVo) {
        ResultVo resultVo = webGroupCheckService.checkCreateGroupParam(groupCreateVo);
        if (resultVo == null || resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        WebGroupPo groupPo = WebGroupPo.builder()
                .groupName(groupCreateVo.getGroupName())
                .description(groupCreateVo.getDescription())
                .createTime(new Date())
                .enabled(CommonStatusEnum.ENABLED.getCode()).build();
        webGroupService.createGroup(groupPo);
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo deletGroup(Integer groupId) {
        webGroupService.deleteGroup(groupId);
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo updateGroup(GroupUpdateVo groupUpdateVo) {
        ResultVo resultVo = webGroupCheckService.checkUpdateGroupParam(groupUpdateVo);
        if (resultVo == null || resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        WebGroupPo groupPo = WebGroupPo.builder()
                .groupId(groupUpdateVo.getGroupId())
                .groupName(groupUpdateVo.getGroupName())
                .description(groupUpdateVo.getDescription()).build();
        webGroupService.updateGroup(groupPo);
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVo addGroupMember(GroupMemberVo groupMemberVo) {
        ResultVo resultVo = webGroupCheckService.checkAddGroupMemberParam(groupMemberVo);
        if (resultVo == null || resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        // 检查关系是否已经存在
        Integer groupId = webGroupService.queryUserGroupId(groupMemberVo.getUserId());
        if (groupId != null && groupId == 0){
            //添加关系
            webGroupService.addGroupMember(groupMemberVo);
            // todo 清除缓存
            return ResultVoBuildUtils.buildSuccessResultVo();
        }
        return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.USER_GROUP_EXISTS.getCode(),ExceptionCodeEnum.USER_GROUP_EXISTS.getValue());
    }

    @Override
    public ResultVo updateGroupStatus(Integer groupId, Integer status) {
        if (status < ManagerConstant.STATUS_DISABLED || status > ManagerConstant.STATUS_DELETE){
            return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.REQUEST_PARAM_INVALID.getCode(),ExceptionCodeEnum.REQUEST_PARAM_INVALID.getValue());
        }
        webGroupService.updateGroupStatus(groupId,status);
        // todo
        return ResultVoBuildUtils.buildSuccessResultVo();
    }
}
