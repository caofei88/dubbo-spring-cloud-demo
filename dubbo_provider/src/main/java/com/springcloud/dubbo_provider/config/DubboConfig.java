/*
package com.springcloud.dubbo_provider.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.deepoove.swagger.dubbo.annotations.EnableDubboSwagger;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ImportResource({ "classpath:dubbo/*.xml" })
//@PropertySource("classpath:swagger-dubbo.properties")
@DubboComponentScan(basePackages = { "com.springcloud.dubbo_provider.service" })
@EnableDubboSwagger
public class DubboConfig {

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dubbo-example-app");
        applicationConfig.setOwner("yunai");
        return applicationConfig;
    }


}
*/
