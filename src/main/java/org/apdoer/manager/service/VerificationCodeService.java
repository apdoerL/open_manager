package org.apdoer.manager.service;


import org.apdoer.manager.model.pojo.VerificationCodePo;
import org.apdoer.manager.model.vo.EmailVo;

/**
 * @author apdoer
 * @date 2018-12-26
 */
public interface VerificationCodeService {

    /**
     * 发送邮件验证码
     * @param code
     */
    EmailVo sendEmail(VerificationCodePo code);

    /**
     * 验证
     * @param code
     */
    void validated(VerificationCodePo code);
}
