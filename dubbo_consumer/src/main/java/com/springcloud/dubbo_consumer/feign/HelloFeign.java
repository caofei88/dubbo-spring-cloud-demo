package com.springcloud.dubbo_consumer.feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "dubbo-spring-cloud-provider")
public interface HelloFeign {
    @PostMapping("/provider/hello")
    public String hello(@RequestParam("paramStr") String paramStr);
}
