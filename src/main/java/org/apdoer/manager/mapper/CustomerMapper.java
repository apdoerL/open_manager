package org.apdoer.manager.mapper;

import org.apache.ibatis.annotations.Param;
import org.apdoer.manager.common.BaseMapper;
import org.apdoer.manager.model.pojo.WebUserPo;
import org.apdoer.manager.model.vo.WebUserVo;

import java.util.List;

/**
 * @author apdoer
 */
public interface CustomerMapper extends BaseMapper<WebUserPo> {
    /**
     * 更新用户状态
     * @param userId userId
     * @param status status
     */
    void updateCustomerStatus(@Param("userId") Integer userId, @Param("status") Integer status);

    /**
     * 查询客户列表
     * @param webUserVo uservo
     * @return list
     */
    List<WebUserVo> queryCustomerList(WebUserVo webUserVo);
}
