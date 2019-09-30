package org.apdoer.manager.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.exception.QueryMysqlException;
import org.apdoer.manager.exception.UpdateMysqlException;
import org.apdoer.manager.mapper.RoleMapper;
import org.apdoer.manager.model.pojo.RolePo;
import org.apdoer.manager.model.vo.PermissionVo;
import org.apdoer.manager.model.vo.RoleIdDescVo;
import org.apdoer.manager.model.vo.RoleVo;
import org.apdoer.manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author apdoer
 * @date 2018-12-03
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
    private RoleMapper roleMapper;

    @Autowired
    @SuppressWarnings("all")
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleVo> queryRoleList(RoleVo roleVo) {
        try {
            return roleMapper.queryRoleList(roleVo);
        }catch (Exception e){
            log.error("query role list error;reason:",e);
            throw new QueryMysqlException(ExceptionCodeEnum.QUERY_MYSQL_ERROR);
        }
    }

    @Override
    @Cacheable(cacheManager = "webRedisCacheManager",value = "COMMON",key = "'COMMON_allCreatedRole")
    public List<RoleIdDescVo> queryAllCreatedRole() {
        try {
            return roleMapper.queryAllCreatedRole();
        }catch (Exception e){
            log.error("query all created role error;reason:",e);
            throw new QueryMysqlException(ExceptionCodeEnum.QUERY_MYSQL_ERROR);
        }
    }

    @Override
    public List<PermissionVo> queryAllPerm() {
        try {
            return roleMapper.queryAllPerm();
        }catch (Exception e){
            log.error("query perm List error;reason:",e);
            throw new QueryMysqlException(ExceptionCodeEnum.QUERY_MYSQL_ERROR);
        }
    }

    @Override
    public void insertRole(RolePo po) {
        try {
            roleMapper.insertSelective(po);
        }catch (Exception e){
            log.error("create role error po:{},reason:{}",po.toString(),e);
            throw new UpdateMysqlException(ExceptionCodeEnum.UPDATE_MYSQL_ERROR);
        }
    }

    @Override
    public void updateRole(RolePo po) {
        try {
            roleMapper.updateByPrimaryKeySelective(po);
        }catch (Exception e){
            log.error("update role error po:{},reason:{}",po.toString(),e);
            throw new UpdateMysqlException(ExceptionCodeEnum.UPDATE_MYSQL_ERROR);
        }
    }

    @Override
    public void deleteRole(Integer roleId) {
        try {
            roleMapper.deleteByPrimaryKey(roleId);
        }catch (Exception e){
            log.error("delete role error roleId:{},reason:{}",roleId,e);
            throw new UpdateMysqlException(ExceptionCodeEnum.UPDATE_MYSQL_ERROR);
        }
    }

    @Override
    public void updateRoleStatus(Integer roleId, Integer status) {
        try {
            roleMapper.updateByPrimaryKeySelective(RolePo.builder().roleId(roleId).enabled(status).build());
        }catch (Exception e){
            log.error("update role status error roleId:{},reason:{}",roleId,e);
            throw new UpdateMysqlException(ExceptionCodeEnum.UPDATE_MYSQL_ERROR);
        }
    }

    @Override
    public List<Integer> queryUserByRoleId(Integer roleId) {
        try {
            return roleMapper.queryUserByRoleId(roleId);
        }catch (Exception e){
            log.error("query perm List error;reason:",e);
            throw new QueryMysqlException(ExceptionCodeEnum.QUERY_MYSQL_ERROR);
        }
    }
}
