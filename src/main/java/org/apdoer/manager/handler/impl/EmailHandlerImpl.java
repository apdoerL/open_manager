package org.apdoer.manager.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.handler.EmailHandler;
import org.apdoer.manager.model.vo.EmailConfigVo;
import org.apdoer.manager.model.vo.EmailVo;
import org.apdoer.manager.model.vo.ResultVo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author apdoer
 */
@Service
@Slf4j
public class EmailHandlerImpl implements EmailHandler {
    @Override
    public ResultVo get() {
        return null;
    }

    @Override
    public ResultVo update(EmailConfigVo emailConfig) {
        return null;
    }

    @Override
    public ResponseEntity send(EmailVo emailVo) {
        //获取配置

        //封装email信息

        //发送
        return null;
    }
}
