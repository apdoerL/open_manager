package org.apdoer.manager.controller;

import org.apdoer.manager.annotations.SystemControllerLog;
import org.apdoer.manager.handler.CustomerHandler;
import org.apdoer.manager.model.dto.PageBean;
import org.apdoer.manager.model.vo.ResultVo;
import org.apdoer.manager.model.vo.WebUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author web 用户
 * @version 1.0
 * @date 2019/9/20 18:02
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerHandler customerHandler;

    @Autowired
    public void setCustomerHandler(CustomerHandler customerHandler) {
        this.customerHandler = customerHandler;
    }

    @GetMapping("customerList")
    @SystemControllerLog("查询客户列表")
    public ResultVo queryCustomerList(PageBean<WebUserVo>pageBean, WebUserVo webUserVo){
        return customerHandler.queryCustomerList(pageBean,webUserVo);
    }

    @PutMapping("/customerStatus/{id}/{status}")
    @SystemControllerLog("更新客户状态")
    public ResultVo updateCustomerStatus(@PathVariable("id") Integer userId, @PathVariable("status") Integer status){
        return customerHandler.updateCustomerStatus(userId,status);
    }
}
