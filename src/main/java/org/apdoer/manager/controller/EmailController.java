package org.apdoer.manager.controller;

import org.apdoer.manager.annotations.SystemControllerLog;
import org.apdoer.manager.handler.EmailHandler;
import org.apdoer.manager.model.vo.EmailConfigVo;
import org.apdoer.manager.model.vo.EmailVo;
import org.apdoer.manager.model.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 发送邮件
 * @author apdoer
 * @date 2018/09/28 6:55:53
 */
@RestController
@RequestMapping("/email")
public class EmailController {
    private EmailHandler emailHandler;

    @Autowired
    public void setEmailHandler(EmailHandler emailHandler) {
        this.emailHandler = emailHandler;
    }




    @GetMapping("/email")
    @SystemControllerLog("获取初始邮件配置")
    public ResultVo get(){
        return emailHandler.get();
    }

    @SystemControllerLog("配置邮件")
    @PutMapping(value = "/email")
    public ResultVo emailConfig(@Validated @RequestBody EmailConfigVo emailConfig){
        return emailHandler.update(emailConfig);
    }


    @SystemControllerLog("发送邮件")
    @PostMapping(value = "/email")
    public ResponseEntity send(@Validated @RequestBody EmailVo emailVo){
        return emailHandler.send(emailVo);
    }
}
