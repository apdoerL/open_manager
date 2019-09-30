package org.apdoer.manager.handler.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.check.RoleCheckService;
import org.apdoer.manager.enums.CommonStatusEnum;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.handler.RedisHandler;
import org.apdoer.manager.handler.RoleHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.pojo.RolePo;
import org.apdoer.manager.model.vo.*;
import org.apdoer.manager.service.RoleService;
import org.apdoer.manager.service.UserService;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
        List<RoleVo> roleVos = roleService.queryRoleList(roleVo);
        PageInfo<RoleVo> pageInfo = new PageInfo<>(roleVos);
        return ResultVoBuildUtils.buildSuccessResultVo(pageInfo);
    }

    @Override
    public ResultVo queryAllCreatedRole() {
        List<RoleIdDescVo>roleIdDescVos = roleService.queryAllCreatedRole();
        return ResultVoBuildUtils.buildSuccessResultVo(roleIdDescVos);
    }

    @Override
    public ResultVo createRole(RoleCreateVo roleCreateVo) {
        ResultVo resultVo = roleCheckService.checkRoleCreateParam(roleCreateVo);
        if (resultVo == null || resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        RolePo po = RolePo.builder()
                .name(roleCreateVo.getName())
                .roleDesc(roleCreateVo.getRoleDesc())
                .createTime(new Date())
                .enabled(CommonStatusEnum.ENABLED.getCode()).build();
        roleService.insertRole(po);
        redisHandler.cleanAllCreatedRole();
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo updateRole(RoleUpdateVo roleUpdateVo) {
        ResultVo resultVo = roleCheckService.checkRoleUpdateParam(roleUpdateVo);
        if (resultVo == null || resultVo.getCode() != ExceptionCodeEnum.SUCCESS.getCode()){
            return resultVo;
        }
        RolePo po = RolePo.builder()
                .roleId(roleUpdateVo.getRoleId())
                .name(roleUpdateVo.getName())
                .roleDesc(roleUpdateVo.getRoleDesc()).build();
        roleService.updateRole(po);
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo deleteRole(Integer roleId) {
        if (roleId == null){
            return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.REQUEST_PARAM_INVALID.getCode(),ExceptionCodeEnum.REQUEST_PARAM_INVALID.getValue());
        }
        roleService.deleteRole(roleId);
        redisHandler.cleanPermByRoleId(roleId);
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

    @Override
    public ResultVo getPermById(Integer roleId) {
        if (roleId == null){
            return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.REQUEST_PARAM_INVALID.getCode(),ExceptionCodeEnum.REQUEST_PARAM_INVALID.getValue());
        }
        return ResultVoBuildUtils.buildSuccessResultVo(userService.getPermissionList(roleId));
    }

    @Override
    @Cacheable(cacheManager = "webRedisCacheManager",value = "MANAGER", key = "'MANAGER_queryPermLevel'")
    public ResultVo queryPermLevel() {
        List<PermissionVo> allPerms = roleService.queryAllPerm();
        List<PermissionVo> permLevel1s = allPerms.stream().filter(c -> c.getParentId() == -1).collect(Collectors.toList());
        permLevel1s.forEach(p->{
            List<PermissionVo> permLevel2s = allPerms.stream().filter(c1 -> c1.getParentId().intValue() == p.getPermId()).collect(Collectors.toList());
            p.setChild(permLevel2s);
            permLevel2s.forEach(p2->{
                List<PermissionVo> permLevel3s = allPerms.stream().filter(c2 -> c2.getParentId().intValue() == p2.getPermId()).collect(Collectors.toList());
                p2.setChild(permLevel3s);
                permLevel3s.forEach(p3->{
                    List<PermissionVo> permLevel4s = allPerms.stream().filter(c3 -> c3.getParentId().intValue() == p3.getPermId()).collect(Collectors.toList());
                    p3.setChild(permLevel4s);
                });
            });
        });
        return ResultVoBuildUtils.buildSuccessResultVo(permLevel1s);
    }

    @Override
    public ResultVo updateRolePerm(RolePermUpdateVo rolePermUpdateVo) {
        // todo 更新角色权限

        // 清缓存
        return null;
    }

    @Override
    public ResultVo updateRoleStatus(Integer roleId, Integer status) {
        if (status < 0 || status >1){
            return ResultVoBuildUtils.buildResultVo(ExceptionCodeEnum.REQUEST_PARAM_INVALID.getCode(),ExceptionCodeEnum.REQUEST_PARAM_INVALID.getValue());
        }
        roleService.updateRoleStatus(roleId,status);
        redisHandler.cleanAllCreatedRole();
        //清除拥有该角色的用户的角色
        List<Integer> userIds = roleService.queryUserByRoleId(roleId);
        userIds.forEach(var->redisHandler.cleanUserRoleByUserId(var));
        return ResultVoBuildUtils.buildSuccessResultVo();
    }

}
