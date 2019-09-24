package org.apdoer.manager.handler;

import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;

/**
 * @author Li
 * @version 1.0
 * @date 2019/9/19 19:05
 */
public interface RoleHandler {
    /**
     * 查询角色列表
     * @param pageBean
     * @param roleVo
     * @return
     */
    ResultVo queryRoleList(PageBean<RoleVo> pageBean, RoleVo roleVo);

    /**
     * 查询所有可用角色
     * @return
     */
    ResultVo queryAllCreatedRole();

    /**
     * 创建角色
     * @param roleCreateVo
     * @return
     */
    ResultVo createRole(RoleCreateVo roleCreateVo);

    /**
     * 更新角色
     * @param roleUpdateVo
     * @return
     */
    ResultVo updateRole(RoleUpdateVo roleUpdateVo);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    ResultVo deleteRole(Integer roleId);

    /**
     * 查询指定角色的权限
     * @param roleId
     * @return
     */
    ResultVo getRoleById(Integer roleId);

    /**
     * 查询所有可用的权限层级列表
     * @return
     */
    ResultVo queryPermLevel();

    /**
     * 更新角色权限
     * @param rolePermUpdateVo
     * @return
     */
    ResultVo updateRolePerm(RolePermUpdateVo rolePermUpdateVo);
}
