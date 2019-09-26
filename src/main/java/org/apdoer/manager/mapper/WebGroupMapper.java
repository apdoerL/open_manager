package org.apdoer.manager.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apdoer.manager.common.BaseMapper;
import org.apdoer.manager.model.pojo.WebGroupPo;
import org.apdoer.manager.model.vo.GroupMemberVo;
import org.apdoer.manager.model.vo.WebGroupVo;

import java.util.List;

/**
 * @author apdoer
 */
public interface WebGroupMapper extends BaseMapper<WebGroupPo> {

    void updateGroupStatus(Integer groupId, Integer status);

    void addGroupMember(GroupMemberVo groupMemberVo);

    @Select("select group_id from web_user_group where user_id=${0} and enabled = 1")
    Integer queryUserGroupId(Integer userId);

    List<WebGroupVo> queryGroupList(WebGroupVo webGroupVo);
}
