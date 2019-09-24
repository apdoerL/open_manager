package org.apdoer.manager.annotations;

import java.lang.annotation.*;

/**
 * @author apdoer
 * @date 2018-11-24
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD }) // 作用于参数或方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {
	String value() default "";
}
