package com.springcloud.dubbo_consumer;

import com.springcloud.dubbo_api.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRemoteServiceBeanFactory {
    @Reference(check=false)
    private HelloService helloService;
}