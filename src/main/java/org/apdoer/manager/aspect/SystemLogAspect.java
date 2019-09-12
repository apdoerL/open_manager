package org.apdoer.manager.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.apdoer.manager.annotations.SystemControllerLog;
import org.apdoer.manager.config.OpenManagerThreadPool;
import org.apdoer.manager.model.pojo.RecordPo;
import org.apdoer.manager.utils.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Spring AOP实现日志管理
 * @author apdoer
 */
@Aspect
@Component
@Slf4j
public class SystemLogAspect {


    @Autowired
    private MSystemService systemService;

    @Autowired(required = false)
    private HttpServletRequest request;


    /**
     *  @Pointcut("execution(* *..controller..*Controller*.*(..))")
     */
    @Pointcut("@annotation(org.apdoer.manager.annotations.SystemControllerLog)")
    public void controllerAspect() {
        log.info("========controllerAspect===========");
    }

    /**
     * 后置通知(在方法执行之后返回) 用于拦截Controller层操作
     * @param joinPoint 切点
     */
    @After("controllerAspect()")
    public void after(JoinPoint joinPoint) {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            if (currentUser != null && currentUser.getPrincipal() != null && StringUtils.isNotBlank(currentUser.getPrincipal().toString())) {
                String user = currentUser.getPrincipal().toString();
                String logUrl = request.getRequestURI();
                String logIp = NetUtils.getIpAdrress(request);
                String logAddress = NetUtils.getAddByIp(logIp);
                String operType = null;
                try {
                    operType = getControllerMethodDescription(joinPoint);
                } catch (Exception e) {
                    log.error(" get operationType error reason:{}", e);
                }
                // 记录类型 1.登录 2.其他
                String recordType = "用户登陆".equals(operType) ? "1" : "2";

                RecordPo recordPo = new RecordPo(user, logUrl, logIp, logAddress, operType, recordType, new Date());
                OpenManagerThreadPool.getInstance().execute(new SaveRecordPoThread(recordPo, systemService));
            }
        } catch (Exception e) {
            log.error("AOP后置通知异常", e);
        }
    }

    /**
     * 保存日志
     */
    private static class SaveRecordPoThread implements Runnable {
        private RecordPo recordPo;
        private MSystemService systemService;

        public SaveRecordPoThread(RecordPo recordPo, MSystemService systemService) {
            this.recordPo = recordPo;
            this.systemService = systemService;
        }

        @Override
        public void run() {
            systemService.insertRecord(recordPo);
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        // 获取目标类名
        String targetName = joinPoint.getTarget().getClass().getName();
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取相关参数
        Object[] arguments = joinPoint.getArgs();
        // 生成类对象
        Class targetClass = Class.forName(targetName);
        // 获取该类中的方法
        Method[] methods = targetClass.getMethods();

        String description = "";

        for (Method method : methods) {
            if (!method.getName().equals(methodName)) {
                continue;
            }
            Class[] clazzs = method.getParameterTypes();
            // 比较方法中参数个数与从切点中获取的参数个数是否相同，原因是方法可以重载哦
            if (clazzs.length != arguments.length) {
                continue;
            }
            description = method.getAnnotation(SystemControllerLog.class).description();
        }
        return description;
    }

}
