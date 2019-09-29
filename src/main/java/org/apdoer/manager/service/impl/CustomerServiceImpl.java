package org.apdoer.manager.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.enums.ExceptionCodeEnum;
import org.apdoer.manager.exception.QueryMysqlException;
import org.apdoer.manager.exception.UpdateMysqlException;
import org.apdoer.manager.mapper.CustomerMapper;
import org.apdoer.manager.model.vo.WebUserVo;
import org.apdoer.manager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author apdoer
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private CustomerMapper customerMapper;

    @Autowired
    @SuppressWarnings("all")
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public void updateCustomerStatus(Integer userId, Integer status) {
        try {
            customerMapper.updateCustomerStatus(userId,status);
        }catch (Exception e){
            log.error("update customer status error, userid:{},stats:{}reason:",userId,status,e);
            throw new UpdateMysqlException(ExceptionCodeEnum.UPDATE_MYSQL_ERROR);
        }
    }

    @Override
    public List<WebUserVo> queryCustomerList(WebUserVo webUserVo) {
        try {
            return customerMapper.queryCustomerList(webUserVo);
        }catch (Exception e){
            log.error("query customer list error reason:",e);
            throw new QueryMysqlException(ExceptionCodeEnum.QUERY_MYSQL_ERROR);
        }
    }
}
