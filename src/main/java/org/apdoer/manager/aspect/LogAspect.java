package org.apdoer.manager.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.handler.LogHandler;
import org.apdoer.manager.model.pojo.LogPo;
import org.apdoer.manager.utils.RequestHolder;
import org.apdoer.manager.utils.SecurityUtils;
import org.apdoer.manager.utils.StringUtils;
import org.apdoer.manager.utils.ThrowableUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author apdoer
 * @date 2018-11-24
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private LogHandler logHandler;

    private long currentTime = 0L;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(org.apdoer.manager.annotations.SystemControllerLog)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        currentTime = System.currentTimeMillis();
        result = joinPoint.proceed();
        LogPo log = new LogPo("INFO",System.currentTimeMillis() - currentTime);
        logHandler.save(getUsername(), StringUtils.getIP(RequestHolder.getHttpServletRequest()),joinPoint, log);
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        LogPo log = new LogPo("ERROR",System.currentTimeMillis() - currentTime);
        log.setExceptionDetail(ThrowableUtil.getStackTrace(e).getBytes());
        logHandler.save(getUsername(), StringUtils.getIP(RequestHolder.getHttpServletRequest()), (ProceedingJoinPoint)joinPoint, log);
    }

    public String getUsername() {
        try {
            return SecurityUtils.getUsername();
        }catch (Exception e){
            return "";
        }
    }
}
