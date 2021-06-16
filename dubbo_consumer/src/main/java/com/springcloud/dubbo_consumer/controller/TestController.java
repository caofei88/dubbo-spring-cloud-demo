package com.springcloud.dubbo_consumer.controller;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.springcloud.dubbo_api.loadBalance.CommonLoadBalance;
import com.springcloud.dubbo_api.service.HelloService;
import com.springcloud.dubbo_consumer.feign.HelloFeign;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
//import org.apache.dubbo.registry.integration.RegistryProtocol;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.RoundRobinLoadBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Date: 2019/9/3
 * @Time: 22:14
 * @email: inwsy@hotmail.com
 * Description:
 */
@RestController
@RequestMapping("/consumer")
public class TestController {
    @DubboReference
    private HelloService helloService;
    @Autowired
    private HelloFeign helloFeign;
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    /**
     *  DiscoveryClient是Spring cloud提供的一个接口，可以用它来查询服务注册信息
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/dubboTest/{tagParam}")
    public String dubboTest(@PathVariable("tagParam") String tagParam) {
        RpcContext.getContext().setAttachment("dcCode", "dcCode2");
        // RpcContext.getContext().setAttachment("dubbo.tag",tagParam);
       // nacosDiscoveryProperties.getMetadata();
        ServiceInstance serviceInstance = loadBalancerClient.choose("dubbo-spring-cloud-provider");
        return helloService.hello("Dubbo test!");
    }

    @PostMapping("/restTest")
    public String restTest(@RequestParam(value = "paramStr", required = true)String paramStr) {
        return helloFeign.hello(paramStr);
    }

    /**
     * 测试：内容中心总能找到用户新增的实例
     * @return 用户中心的所有实例
     */
    @GetMapping("/getInstances")
    public List<ServiceInstance> getInstances(){
        return this.discoveryClient.getInstances("dubbo-spring-cloud-provider");
    }
}
