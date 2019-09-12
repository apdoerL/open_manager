package org.apdoer.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author li
 */
@SpringBootConfiguration
public class MySpringMvcConfig extends WebMvcConfigurerAdapter {
    private PermissionInterceptor permissionInterceptor;

    @Autowired
    public void setPermissionInterceptor(PermissionInterceptor permissionInterceptor) {
        this.permissionInterceptor = permissionInterceptor;
    }



    /**
     *  register interceptors
     * @param registry registry center
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(permissionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/system/login")
                .excludePathPatterns("/system/queryUserInfo")
                .excludePathPatterns("/system/logout")
                .excludePathPatterns("/error");
    }
}
