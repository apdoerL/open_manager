package org.apdoer.manager.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apdoer.manager.annotations.RequiresIdempotency;
import org.apdoer.manager.redis.RedisCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * @description: 校验接口幂等拦截器
 * @Version:
 * @author: yang
 * @create: 2019-04-28 09:52
 */
@Slf4j
@Component
public class RequiresIdempotencyInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisCacheService redisCacheService;

    // 请求头key
    private static final String HEADER_UNIQUE = "UNIQUE";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 将handler强转为HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        // 获取出方法上的Access注解
        RequiresIdempotency idempotency = method.getAnnotation(RequiresIdempotency.class);
        if (idempotency == null) {
            // 表示不需要拦截, 直接放过
            return true;
        }
        // 需要拦截时，校验是否有unique请求头
        String uuid = this.getUnique(request);
        if (StringUtils.isBlank(uuid)) {
            log.error("URI:{} , Missing request UNIQUE header.", request.getRequestURI());
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Header parameters error.");
            return false;
        }
        // 校验redis是否存在
        if (redisCacheService.hasKey(this.buildKey(uuid))) {
            // 存在，则表示重复提交
            log.error("URI:{} , submit repeatedly.UNIQUE_HEADER:{}", request.getRequestURI(), uuid);
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Submit repeatedly error.");
            return false;
        }
        // 若不存在，则正常处理
        redisCacheService.setex(this.buildKey(uuid), uuid, idempotency.expire());
        return true;
    }

    /**
     * 获取unique头
     */
    private String getUnique(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        String unique = "";
        while (headerNames.hasMoreElements()) {
            // 获取unique头
            if (headerNames.nextElement().equalsIgnoreCase(HEADER_UNIQUE)) {
                unique = request.getHeader(HEADER_UNIQUE);
                break;
            }
        }
        return unique;
    }

    /**
     * 构造redis key
     */
    private String buildKey(String uuid) {
        return HEADER_UNIQUE + "_" + uuid;
    }
}
