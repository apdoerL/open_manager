package org.apdoer.manager.service;

import org.apdoer.manager.model.pojo.WebGroupPo;
import org.apdoer.manager.model.vo.GroupMemberVo;
import org.apdoer.manager.model.vo.WebGroupVo;

import java.util.List;

/**
 * @author apdoer
 */
public interface WebGroupService {
    /**
     * 查询组列表
     * @param webGroupVo vp
     * @return r
     */
    List<WebGroupVo> queryGroupList(WebGroupVo webGroupVo);

    /**
     * 添加组
     * @param groupPo po
     */
    void createGroup(WebGroupPo groupPo);

    /**
     * 删除组
     * @param groupId groupId
     */
    void deleteGroup(Integer groupId);

    /**
     * 更新组
     * @param groupPo po
     */
    void updateGroup(WebGroupPo groupPo);

    /**
     * 查询用户的组
     * @param userId userId
     * @return groupId
     */
    Integer queryUserGroupId(Integer userId);

    /**
     * 添加组员
     * @param groupMemberVo vo
     */
    void addGroupMember(GroupMemberVo groupMemberVo);

    /**
     * 更新组状态
     * @param groupId groupId
     * @param status status
     */
    void updateGroupStatus(Integer groupId, Integer status);
}
