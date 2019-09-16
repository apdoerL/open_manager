package org.apdoer.manager.service.impl;

import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import org.apdoer.manager.exception.BadRequestException;
import org.apdoer.manager.model.pojo.EmailPo;
import org.apdoer.manager.model.vo.EmailVo;
import org.apdoer.manager.service.EmailService;
import org.apdoer.manager.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author apdoer
 * @date 2018-12-26
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EmailServiceImpl implements EmailService {

//    @Autowired
//    private EmailRepository emailRepository;
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public EmailPo update(EmailPo emailConfig, EmailPo old) {
//        try {
//            if(!emailConfig.getPass().equals(old.getPass())){
//                // 对称加密
//                emailConfig.setPass(EncryptUtils.desEncrypt(emailConfig.getPass()));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return emailRepository.save(emailConfig);
//    }
//
//    @Override
//    public EmailPo find() {
//        Optional<EmailPo> emailConfig = emailRepository.findById(1L);
//        if(emailConfig.isPresent()){
//            return emailConfig.get();
//        } else {
//            return new EmailPo();
//        }
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void send(EmailVo emailVo, EmailPo emailConfig){
//        if(emailConfig == null){
//            throw new BadRequestException("请先配置，再操作");
//        }
//        /**
//         * 封装
//         */
//        MailAccount account = new MailAccount();
//        account.setHost(emailConfig.getHost());
//        account.setPort(Integer.parseInt(emailConfig.getPort()));
//        account.setAuth(true);
//        try {
//            // 对称解密
//            account.setPass(EncryptUtils.desDecrypt(emailConfig.getPass()));
//        } catch (Exception e) {
//            throw new BadRequestException(e.getMessage());
//        }
//        account.setFrom(emailConfig.getUser()+"<"+emailConfig.getFromUser()+">");
//        //ssl方式发送
//        account.setStartttlsEnable(true);
//        String content = emailVo.getContent();
//        /**
//         * 发送
//         */
//        try {
//            Mail.create(account)
//                    .setTos(emailVo.getTos().toArray(new String[emailVo.getTos().size()]))
//                    .setTitle(emailVo.getSubject())
//                    .setContent(content)
//                    .setHtml(true)
//                    //关闭session
//                    .setUseGlobalSession(false)
//                    .send();
//        }catch (Exception e){
//            throw new BadRequestException(e.getMessage());
//        }
//    }
}
