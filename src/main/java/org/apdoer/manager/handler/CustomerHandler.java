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
     * @param pageBean
     * @param webUserVo
     * @return
     */
    ResultVo queryCustomerList(PageBean<WebUserVo> pageBean, WebUserVo webUserVo);
}
