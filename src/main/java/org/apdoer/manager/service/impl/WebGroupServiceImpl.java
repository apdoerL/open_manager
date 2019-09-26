package org.apdoer.manager.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.exception.QueryMysqlException;
import org.apdoer.manager.exception.UpdateMysqlException;
import org.apdoer.manager.mapper.WebGroupMapper;
import org.apdoer.manager.model.pojo.WebGroupPo;
import org.apdoer.manager.model.vo.GroupMemberVo;
import org.apdoer.manager.model.vo.WebGroupVo;
import org.apdoer.manager.service.WebGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author apdoer
 */
@Service
@Slf4j
public class WebGroupServiceImpl implements WebGroupService {
    private WebGroupMapper webGroupMapper;

    @Autowired
    @SuppressWarnings("all")
    public void setWebGroupMapper(WebGroupMapper webGroupMapper) {
        this.webGroupMapper = webGroupMapper;
    }

    @Override
    public List<WebGroupVo> queryGroupList(WebGroupVo webGroupVo) {
        try {
            return webGroupMapper.queryGroupList(webGroupVo);
        }catch (Exception e){
            log.error("query group list error;reason:",e);
            throw new QueryMysqlException(ExceptionCodeEnum.QUERY_MYSQL_ERROR);
        }
    }

    @Override
    public void createGroup(WebGroupPo groupPo) {
        try {
            webGroupMapper.insertSelective(groupPo);
        }catch (Exception e){
            log.error("create group error;reason:",e);
            throw new UpdateMysqlException(ExceptionCodeEnum.UPDATE_MYSQL_ERROR);
        }
    }

    @Override
    public void deleteGroup(Integer groupId) {
        try {
            webGroupMapper.deleteByPrimaryKey(groupId);
        }catch (Exception e){
            log.error("delete group error groupId:{}.reason{}",groupId,e);
            throw new UpdateMysqlException(ExceptionCodeEnum.UPDATE_MYSQL_ERROR);
        }
    }

    @Override
    public void updateGroup(WebGroupPo groupPo) {
        try {
            webGroupMapper.updateByPrimaryKeySelective(groupPo);
        }catch (Exception e){
            log.error("update group error group:{},reason:{}",groupPo,e);
            throw new UpdateMysqlException(ExceptionCodeEnum.UPDATE_MYSQL_ERROR);
        }
    }

    @Override
    public Integer queryUserGroupId(Integer userId) {
        try {
            return webGroupMapper.queryUserGroupId(userId);
        }catch (Exception e){
            log.error("query user group id error userid:{},reason:{}",userId,e);
            throw new QueryMysqlException(ExceptionCodeEnum.QUERY_MYSQL_ERROR);
        }
    }

    @Override
    public void addGroupMember(GroupMemberVo groupMemberVo) {
        try {
            webGroupMapper.addGroupMember(groupMemberVo);
        }catch (Exception e){
            log.error("add group member error;groupMemberVo:{},reason:{}",groupMemberVo,e);
            throw new UpdateMysqlException(ExceptionCodeEnum.UPDATE_MYSQL_ERROR);
        }
    }

    @Override
    public void updateGroupStatus(Integer groupId, Integer status) {
        try {
            webGroupMapper.updateGroupStatus(groupId,status);
        }catch (Exception e){
            log.error("update group status error,groupid:{},status:{},reason:{}",groupId,status,e);
            throw new UpdateMysqlException(ExceptionCodeEnum.UPDATE_MYSQL_ERROR);
        }
    }
}
