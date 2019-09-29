package org.apdoer.manager.service;


import org.apdoer.manager.model.vo.WebUserVo;

import java.util.List;

/**
 * @author apdoer
 */
public interface Customer0Service {
    /**
     * 更新客户状态
     * @param userId userId
     * @param status status
     */
    void updateCustomerStatus(Integer userId, Integer status);

    /**
     * 查询客户列表
     * @param webUserVo vo
     * @return list
     */
    List<WebUserVo> queryCustomerList(WebUserVo webUserVo);
}
