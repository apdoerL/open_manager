package org.apdoer.manager.annotations;

import org.apdoer.manager.enums.LimitTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *@Description 限流
 *@author apdoer
 *@CreateDate 2019/8/13-22:17
 *@Version 1.0
 *===============================
**/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    // 资源名称，用于描述接口功能
    String name() default "";

    // 资源 key
    String key() default "";

    // key prefix
    String prefix() default "";

    // 时间的，单位秒
    int period();

    // 限制访问次数
    int count();

    // 限制类型
    LimitTypeEnum limitType() default LimitTypeEnum.CUSTOMER;

}
