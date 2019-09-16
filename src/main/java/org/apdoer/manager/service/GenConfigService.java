package org.apdoer.manager.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author apdoer
 * @date 2019-01-14
 */
@CacheConfig(cacheNames = "genConfig")
public interface GenConfigService {

//    /**
//     * find
//     * @return
//     */
//    @Cacheable(key = "'1'")
//    GeneConfigPo find();
//
//    /**
//     * update
//     * @param genConfig
//     * @return
//     */
//    @CacheEvict(allEntries = true)
//    GeneConfigPo update(GeneConfigPo genConfig);
}
