package org.apdoer.manager.annotations;

import java.lang.annotation.*;

/**
 *
 * @Description: 幂等
 * @Author: yang
 * @Date: 19/4/28/0028 0:13
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresIdempotency {

    /**
     * 过期时间，单位：秒
     */
    int expire() default 3600;
}
