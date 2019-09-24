package org.apdoer.manager.service;

import org.apdoer.manager.model.pojo.AlipayPo;
import org.apdoer.manager.model.vo.TradeVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author apdoer
 * @date 2018-12-31
 */
@CacheConfig(cacheNames = "alipay")
public interface AlipayService {

    /**
     * 处理来自PC的交易请求
     * @param alipay
     * @param trade
     * @return
     * @throws Exception
     */
    String toPayAsPC(AlipayPo alipay, TradeVo trade) throws Exception;

    /**
     * 处理来自手机网页的交易请求
     * @param alipay
     * @param trade
     * @return
     * @throws Exception
     */
    String toPayAsWeb(AlipayPo alipay, TradeVo trade) throws Exception;

    /**
     * 查询配置
     * @return
     */
    @Cacheable(key = "'1'")
    AlipayPo find();

    /**
     * 更新配置
     * @param alipayConfig
     * @return
     */
    @CachePut(key = "'1'")
    AlipayPo update(AlipayPo alipayConfig);
}
