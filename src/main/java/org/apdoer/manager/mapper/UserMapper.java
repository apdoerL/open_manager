package org.apdoer.manager.mapper;

import org.apache.ibatis.annotations.Param;
import org.apdoer.manager.common.BaseMapper;
import org.apdoer.manager.model.pojo.BizUserPo;
import org.apdoer.manager.model.pojo.RolePo;
import org.apdoer.manager.model.vo.UserCreateVo;

import java.util.List;

/**
 * @author apdoer
 * @date 2018-11-23
 */
public interface UserMapper extends BaseMapper<BizUserPo> {


    /**
     * 查询该用户的角色
     *
     * @param userId userId
     * @return r
     */
    List<Integer> queryRolesByUserId(@Param("userId") Long userId);

    /**
     * 根据角色id查询权限集合
     *
     * @param roleId roleID
     * @return r
     */
    List<String> queryPermsByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色查询用户角色对象
     *
     * @param userId userid
     * @return role objs
     */
    List<RolePo> queryRoleposByUserId(@Param("userId") Long userId);

    /**
     * 添加用户并返回主键
     *
     * @param userCreateVo vo
     */
    void insertAndSelectKey(UserCreateVo userCreateVo);


}
