
package org.apdoer.manager.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * spring package scanner
 * @author apdoer
 */
@Configuration
@ComponentScan({
        "org.apdoer.manager.handler",
        "org.apdoer.manager.service"})
@MapperScan(basePackages = {
        "org.apdoer.manager.mapper"})
public class ServiceScanConfig {

}
