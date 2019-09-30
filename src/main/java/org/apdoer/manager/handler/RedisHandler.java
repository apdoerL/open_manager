package org.apdoer.manager.handler;

/**
 *
 * @author apdoer
 * @date 2019/8/30 15:17
 */
public interface RedisHandler {
    /**
     * 清除所有权限的缓存
     */
    void cleanAllCreatedRole();

    /**
     * 清除角色的权限
     * @param roleId roleId
     */
    void cleanPermByRoleId(Integer roleId);

    /**
     * 清除用户的角色
     * @param var userId
     */
    void cleanUserRoleByUserId(Integer var);
}
