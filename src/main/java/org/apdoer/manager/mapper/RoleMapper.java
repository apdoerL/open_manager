package org.apdoer.manager.mapper;

import org.apache.ibatis.annotations.Param;
import org.apdoer.manager.common.BaseMapper;
import org.apdoer.manager.model.pojo.RolePo;
import org.apdoer.manager.model.vo.PermissionVo;
import org.apdoer.manager.model.vo.RoleIdDescVo;
import org.apdoer.manager.model.vo.RoleVo;

import java.util.List;

/**
 * @author apdoer
 * @date 2018-11-23
 */
public interface RoleMapper extends BaseMapper<RolePo> {

    /**
     * 查询所有权限
     * @return r
     */
    List<PermissionVo> queryAllPerm();

    /**
     * 查询角色列表
     * @param roleVo roleVo
     * @return list
     */
    List<RoleVo> queryRoleList(RoleVo roleVo);

    /**
     * 查询所有可用角色
     * @return r
     */
    List<RoleIdDescVo> queryAllCreatedRole();

    /**
     * 查询角色下所有用户id
     * @param roleId roleid
     * @return userIds
     */
    List<Integer> queryUserByRoleId(@Param("roleId") Integer roleId);
}
