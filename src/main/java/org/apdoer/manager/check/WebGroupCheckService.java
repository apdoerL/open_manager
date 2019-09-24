package org.apdoer.manager.check;


import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.*;

/**
 * @author apdoer
 */
public interface WebGroupCheckService {
    /**
     * 校验查询组列表参数
     * @param pageBean
     * @param webGroupVo
     * @return
     */
    ResultVo checkQueryGroupParam(PageBean<WebGroupVo> pageBean, WebGroupVo webGroupVo);

    /**
     * 校验创建组参数
     * @param groupCreateVo
     * @return
     */
    ResultVo checkCreateGroupParam(GroupCreateVo groupCreateVo);

    /**
     * 校验更新组参数
     * @param groupUpdateVo
     * @return
     */
    ResultVo checkUpdateGroupParam(GroupUpdateVo groupUpdateVo);

    /**
     * 校验添加组员参数
     * @param groupMemberVo
     * @return
     */
    ResultVo checkAddGroupMemberParam(GroupMemberVo groupMemberVo);
}
