package org.apdoer.manager.controller;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.annotations.SystemControllerLog;
import org.apdoer.manager.model.pojo.EmailPo;
import org.apdoer.manager.model.vo.EmailVo;
import org.apdoer.manager.model.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 发送邮件
 * @author apdoer
 * @date 2018/09/28 6:55:53
 */
@Slf4j
@RestController
@RequestMapping("email")
public class EmailController {
    private EmailHandler emailHandler;

    @Autowired
    public void setEmailHandler(EmailHandler emailHandler) {
        this.emailHandler = emailHandler;
    }




    public ResultVo



    @GetMapping(value = "/email")
    @SystemControllerLog("")
    public ResponseEntity get(){
        return new ResponseEntity(emailService.find(),HttpStatus.OK);
    }

    @SystemControllerLog("配置邮件")
    @PutMapping(value = "/email")
    public ResponseEntity emailConfig(@Validated @RequestBody EmailPo emailConfig){
        emailService.update(emailConfig,emailService.find());
        return new ResponseEntity(HttpStatus.OK);
    }

    @SystemControllerLog("发送邮件")
    @PostMapping(value = "/email")
    public ResponseEntity send(@Validated @RequestBody EmailVo emailVo) throws Exception {
        log.warn("REST request to send Email : {}" +emailVo);
        emailService.send(emailVo,emailService.find());
        return new ResponseEntity(HttpStatus.OK);
    }
}
