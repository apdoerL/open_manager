package org.apdoer.manager.handler;

import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;

/**
 * @author apdoer
 * @version 1.0
 * @date 2019/9/19 19:05
 */
public interface RoleHandler {
    /**
     * 查询角色列表
     * @param pageBean page
     * @param roleVo vo
     * @return r
     */
    ResultVo queryRoleList(PageBean<RoleVo> pageBean, RoleVo roleVo);

    /**
     * 查询所有可用角色
     * @return r
     */
    ResultVo queryAllCreatedRole();

    /**
     * 创建角色
     * @param roleCreateVo vo
     * @return r
     */
    ResultVo createRole(RoleCreateVo roleCreateVo);

    /**
     * 更新角色
     * @param roleUpdateVo ro
     * @return r
     */
    ResultVo updateRole(RoleUpdateVo roleUpdateVo);

    /**
     * 删除角色
     * @param roleId id
     * @return r
     */
    ResultVo deleteRole(Integer roleId);

    /**
     * 查询指定角色的权限
     * @param roleId id
     * @return r
     */
    ResultVo getPermById(Integer roleId);

    /**
     * 查询所有可用的权限层级列表
     * @return r
     */
    ResultVo queryPermLevel();

    /**
     * 更新角色权限
     * @param rolePermUpdateVo vo
     * @return r
     */
    ResultVo updateRolePerm(RolePermUpdateVo rolePermUpdateVo);

    /**
     * 更新角色状态
     * @param roleId roleId
     * @param status status
     * @return r
     */
    ResultVo updateRoleStatus(Integer roleId, Integer status);
}
