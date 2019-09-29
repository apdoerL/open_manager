package org.apdoer.manager.mapper;


import org.apache.ibatis.annotations.Param;
import org.apdoer.manager.common.BaseMapper;
import org.apdoer.manager.model.pojo.WebGroupPo;
import org.apdoer.manager.model.vo.GroupMemberVo;
import org.apdoer.manager.model.vo.WebGroupVo;

import java.util.List;

/**
 * @author apdoer
 */
public interface WebGroupMapper extends BaseMapper<WebGroupPo> {
    /**
     * 更新组状态
     * @param groupId groupId
     * @param status status
     */
    void updateGroupStatus(@Param("groupId") Integer groupId, @Param("status") Integer status);

    /**
     * 添加组员
     * @param groupMemberVo GroupMembervo
     */
    void addGroupMember(GroupMemberVo groupMemberVo);

    /**
     * 查询用户的组id
     * @param userId userid
     * @return groupId
     */
    Integer queryUserGroupId(@Param("userId") Integer userId);


    /**
     * 查询组列表
     * @param webGroupVo
     * @return
     */
    List<WebGroupVo> queryGroupList(WebGroupVo webGroupVo);
}
