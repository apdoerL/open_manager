package org.apdoer.manager.config;

import org.apdoer.manager.interceptor.RequiresIdempotencyInterceptor;
import org.apdoer.manager.interceptor.RequiresPermissionsInterceptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * WebMvcConfigurerAdapter 在springboot2.0+ 过时,不推荐使用,新的实现是
 * 	1.继承 WebMvcConfigurationSupport
 * 	2.实现 WebMvcConfigurer
 * @author apdoer
 * @date 2019-09-16
 */
@Configuration
public class RequiresPermissionsConfig extends WebMvcConfigurationSupport {
	private RequiresPermissionsInterceptorService interceptor;
	private RequiresIdempotencyInterceptor requiresIdempotencyInterceptor;



	@Autowired
	public void setInterceptor(RequiresPermissionsInterceptorService interceptor) {
		this.interceptor = interceptor;
	}
	@Autowired
	public void setRequiresIdempotencyInterceptor(RequiresIdempotencyInterceptor requiresIdempotencyInterceptor) {
		this.requiresIdempotencyInterceptor = requiresIdempotencyInterceptor;
	}


	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
				.excludePathPatterns("/static/*")
				.excludePathPatterns("/common/*")
                .excludePathPatterns("/error")
				.excludePathPatterns("/system/test")
				.addPathPatterns("/**");

		registry.addInterceptor(requiresIdempotencyInterceptor)
				.excludePathPatterns("/static/*")
				.excludePathPatterns("/common/*")
				.excludePathPatterns("/error")
				.addPathPatterns("/**");
		//如果作为service给其他服务调用,加上以下内容追加到拦截链
//		super.addInterceptors(registry);
    }
}
