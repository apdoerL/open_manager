package org.apdoer.manager.annotations;

import org.apdoer.manager.enums.LogicalEnum;

import java.lang.annotation.*;

/**
 * @author apdoer
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresPermissions {
	
	String[] value();
	
	LogicalEnum logical() default LogicalEnum.AND;
}
