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
public interface AlipayService {

    /**
     * 查询配置
     * @return
     */
    AlipayPo queryConfig();

    /**
     * 更新配置 如果放缓存用cachePut 返回值为 AlipayPo ,如果不放缓存,返回值为void
     * @param alipayConfig
     * @return
     */
    void updateConfig(AlipayPo alipayConfig);
}
