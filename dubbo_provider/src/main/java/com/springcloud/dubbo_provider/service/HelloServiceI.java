package com.springcloud.dubbo_provider.service;
import com.springcloud.dubbo_api.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.registry.client.RegistryProtocol;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.cluster.router.tag.TagRouter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Date: 2019/9/3
 * @Time: 21:55
 * @email: inwsy@hotmail.com
 * Description:
 */
@Service
@Api(value = "测试Test服务")
@Slf4j
public class HelloServiceI implements HelloService {
    @Override
    @ApiOperation(value = "测试1")
    public String hello(@ApiParam(value = "姓名")String name) {
        String tag = RpcContext.getContext().getAttachment("dubbo.tag");
        log.info("tag:{}",tag);
        return "Hello provider2-->>dcCode2 666" + name;
    }
}
