package org.apdoer.manager.service;

import org.apdoer.manager.model.pojo.EmailPo;
import org.apdoer.manager.model.vo.EmailVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;

/**
 * @author apdoer
 * @date 2018-12-26
 */
@CacheConfig(cacheNames = "email")
public interface EmailService {

    /**
     * 更新邮件配置
     * @param emailConfig
     * @param old
     * @return
     */
    @CachePut(key = "'1'")
    EmailPo update(EmailPo emailConfig, EmailPo old);

    /**
     * 查询配置
     * @return
     */
    @Cacheable(key = "'1'")
    EmailPo find();

    /**
     * 发送邮件
     * @param emailVo
     * @param emailConfig
     * @throws Exception
     */
    @Async
    void send(EmailVo emailVo, EmailPo emailConfig) throws Exception;
}
