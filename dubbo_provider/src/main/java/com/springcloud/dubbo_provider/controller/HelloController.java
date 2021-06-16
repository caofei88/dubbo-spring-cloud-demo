package com.springcloud.dubbo_provider.controller;
import com.springcloud.dubbo_api.service.HelloService;
import com.springcloud.dubbo_provider.common.SpringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.rpc.cluster.router.tag.TagRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Date: 2019/9/3
 * @Time: 22:14
 * @email: inwsy@hotmail.com
 * Description:
 */
@RestController
@Api(value = "Demo测试接口封装", tags = "HelloController")
@RequestMapping("/provider")
public class HelloController {

  /*  @Autowired
    private HelloService  helloService;*/
    @ApiOperation(value = "接口测试啦")
    @PostMapping("/hello")
    public String hello(@RequestParam(value = "paramStr", required = true)String paramStr) {
        HelloService  helloService= (HelloService)SpringUtil.getBean("helloServiceI");
        return helloService.hello(paramStr);
    }
}
