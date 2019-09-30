package org.apdoer.manager.service;

import org.apdoer.manager.model.pojo.RolePo;
import org.apdoer.manager.model.vo.PermissionVo;
import org.apdoer.manager.model.vo.RoleIdDescVo;
import org.apdoer.manager.model.vo.RoleVo;

import java.util.List;


/**
 * @author apdoer
 * @date 2018-12-03
 */
public interface RoleService {
    /**
     * 查询角色列表
     * @param roleVo rolevo
     * @return r
     */
    List<RoleVo> queryRoleList(RoleVo roleVo);

    /**
     * 查询所有可用的角色
     * @return r
     */
    List<RoleIdDescVo> queryAllCreatedRole();

    /**
     * 查询所有权限层级
     * @return r
     */
    List<PermissionVo> queryAllPerm();

    /**
     * 新增角色
     * @param po po
     */
    void insertRole(RolePo po);

    /**
     * 更新角色
     * @param po po
     */
    void updateRole(RolePo po);

    /**
     * 删除角色
     * @param roleId roleId
     */
    void deleteRole(Integer roleId);

    /**
     * 更新角色状态
     * @param roleId roleID
     * @param status status
     */
    void updateRoleStatus(Integer roleId, Integer status);

    /**
     * 查询角色下所有的用户
     * @param roleId roleId
     * @return ids
     */
    List<Integer> queryUserByRoleId(Integer roleId);

}
