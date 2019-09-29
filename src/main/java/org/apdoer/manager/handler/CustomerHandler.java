package org.apdoer.manager.handler;

import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.model.vo.WebUserVo;

/**
 * @author apdoer
 */
public interface CustomerHandler {
    /**
     * 查询web用户列表
     * @param pageBean page
     * @param webUserVo vo
     * @return r
     */
    ResultVo queryCustomerList(PageBean<WebUserVo> pageBean, WebUserVo webUserVo);

    /**
     * 更新客户启用状态
     * @param userId userId
     * @param status status
     * @return r
     */
    ResultVo updateCustomerStatus(Integer userId, Integer status);
}
