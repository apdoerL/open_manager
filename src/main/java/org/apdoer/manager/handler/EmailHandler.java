package org.apdoer.manager.handler;

import org.apdoer.manager.model.vo.EmailConfigVo;
import org.apdoer.manager.model.vo.EmailVo;
import org.apdoer.manager.model.vo.ResultVo;
import org.springframework.http.ResponseEntity;

/**
 * @author apdoer
 */
public interface EmailHandler {
    /**
     * 获取邮件配置
     * @return
     */
    ResultVo get();

    /**
     * 添加邮件配置
     * @param emailConfig
     * @return
     */
    ResultVo update(EmailConfigVo emailConfig);

    /**
     * 发送邮件
     * @param emailVo
     * @return
     */
    ResponseEntity send(EmailVo emailVo);
}
