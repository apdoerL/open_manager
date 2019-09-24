package org.apdoer.manager.handler;


import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;

/**
 * @author apdoer
 */
public interface WebGroupHandler {

    /**
     * 查询组列表
     * @param pageBean
     * @param webGroupVo
     * @return
     */
    ResultVo queryGroupList(PageBean<WebGroupVo> pageBean, WebGroupVo webGroupVo);

    /**
     * 创建新组
     * @param groupCreateVo
     * @return
     */
    ResultVo createGroup(GroupCreateVo groupCreateVo);

    /**
     * 删除组
     * @param groupId
     * @return
     */
    ResultVo deletGroup(Integer groupId);

    /**
     * 更新组信息
     * @param groupUpdateVo
     * @return
     */
    ResultVo updateGroup(GroupUpdateVo groupUpdateVo);

    /**
     * 添加组员
     * @param groupMemberVo
     * @return
     */
    ResultVo addGroupMember(GroupMemberVo groupMemberVo);

    /**
     * 更新组状态
     * @param groupId
     * @param status
     * @return
     */
    ResultVo updateGroupStatus(Integer groupId, Integer status);
}
