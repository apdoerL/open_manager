package org.apdoer.manager.aspect;

import lombok.extern.slf4j.Slf4j;

import org.apdoer.manager.annotations.SystemControllerLog;
import org.apdoer.manager.config.OpenManagerThreadPool;
import org.apdoer.manager.model.pojo.RecordPo;
import org.apdoer.manager.model.vo.AuthorizationUserVo;
import org.apdoer.manager.service.SystenOperationService;
import org.apdoer.manager.utils.NetUtils;
import org.apdoer.manager.utils.SecurityUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring AOP实现日志管理
 *
 * @author apdoer
 */
@Aspect
@Component
@Slf4j
public class SystemLogAspect {
    private SystenOperationService systemService;
    private HttpServletRequest request;


    @Autowired
    public void setSystemService(SystenOperationService systemService) {
        this.systemService = systemService;
    }

    @Autowired(required = false)
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }


    /**
     * @Pointcut("execution(* *..controller..*Controller*.*(..))")
     */
    @Pointcut("@annotation(org.apdoer.manager.annotations.SystemControllerLog)")
    public void controllerAspect() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 后置通知(在方法执行之后返回) 用于拦截Controller层操作
     *
     * @param joinPoint 切点
     */
    @After("controllerAspect()")
    public void after(JoinPoint joinPoint) {
        try {
            //可以优化,后面再做
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String user;
            //防止在登录的时候获取不到当前用户信息
            Map<String, String> map = getControllerMethodInfo(joinPoint);
            if (map.get("method").equals("login")){
                user = map.get("username");
            }else {
                user = SecurityUtil.getUsername();
            }
            String logUrl = request.getRequestURI();
            String logIp = NetUtils.getIpAdrress(request);
            String logAddress = NetUtils.getAddByIp(logIp);
            String operType = map.get("operType");
            // 记录类型 1.登录 2.其他
            String recordType = "用户登陆".equals(operType) ? "1" : "2";
            RecordPo recordPo = new RecordPo(user, logUrl, logIp, logAddress, operType, recordType, new Date());
            OpenManagerThreadPool.getInstance().execute(new SaveRecordPoThread(recordPo, systemService));
        } catch (Exception e) {
            log.error("AOP后置通知异常", e);
        }
    }

    /**
     * 保存日志
     */
    private static class SaveRecordPoThread implements Runnable {
        private RecordPo recordPo;
        private SystenOperationService systemService;

        SaveRecordPoThread(RecordPo recordPo, SystenOperationService systemService) {
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
     * @throws Exception e
     */
    private static Map<String,String> getControllerMethodInfo(JoinPoint joinPoint) throws Exception {
        Map<String,String>map = new HashMap<>();
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
            map.put("method",method.getName());
            if (method.getName() == "login"){
                map.put("username", ((AuthorizationUserVo) arguments[0]).getUsername());
            }
            description = method.getAnnotation(SystemControllerLog.class).value();
            map.put("operType",description);
        }
        return map;
    }

}
