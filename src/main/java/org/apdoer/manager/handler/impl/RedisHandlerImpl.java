package org.apdoer.manager.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.handler.RedisHandler;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

/**
 *
 * @author apdoer
 * @date 2019/08/30
 */
@Component
@Slf4j
public class RedisHandlerImpl implements RedisHandler {


    @Override
    @CacheEvict(cacheManager = "webRedisCacheManager",value = "COMMON",key = "'COMMON_allCreatedRole")
    public void cleanAllCreatedRole() {
       log.info("clean all created role cache success");
    }
}
