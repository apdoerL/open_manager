package org.apdoer.manager.handler.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.handler.CustomerHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.model.vo.WebUserVo;
import org.apdoer.manager.service.CustomerService;
import org.apdoer.manager.utils.ResultVoBuildUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author apdoer
 */
@Service
@Slf4j
public class CustomerHandlerImpl implements CustomerHandler {
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResultVo queryCustomerList(PageBean<WebUserVo> pageBean, WebUserVo webUserVo) {
        // todo 校验参数

        PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
        List<WebUserVo> userVoList = customerService.queryCustomerList(webUserVo);
        PageInfo<WebUserVo> pageInfo = new PageInfo<>(userVoList);
        return ResultVoBuildUtils.buildSuccessResultVo(pageInfo);
    }

    @Override
    public ResultVo updateCustomerStatus(Integer userId, Integer status) {
        customerService.updateCustomerStatus(userId,status);
        // todo 清除缓存
        return ResultVoBuildUtils.buildSuccessResultVo();
    }


}
