package com.springcloud.dubbo_provider.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;

import java.util.ArrayList;
import java.util.List;
@Configuration
@EnableSwagger2 // 标记项目启用 Swagger API 接口文档
public class SwaggerConfiguration {


    // http://localhost:8080/swagger-ui.html

    /**
     * Swagger 是一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务。
     * 总体目标是使客户端和文件系统作为服务器以同样的速度来更新。
     * 文件的方法，参数和模型紧密集成到服务器端的代码，允许API来始终保持同步。
     * Swagger 让部署管理和使用功能强大的API从未如此简单。
     *
     * 前后端分离开发
     * API文档非常明确
     * 测试的时候不需要再使用URL输入浏览器的方式来访问Controller
     * 传统的输入URL的测试方式对于post请求的传参比较麻烦（当然，可以使用postman这样的浏览器插件）
     * spring-boot与swagger的集成简单的一逼
     */
    @Value("${swagger.enable:true}")
    private boolean enable;

   /* @Bean
    public Docket createRestApi() {
        //在配置好的配置类中增加此段代码即可
     *//*   ParameterBuilder ticketPar1 = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar1.name("Authorization").description("登录校验")//name表示名称，description表示描述
                .modelRef(new ModelRef("string")).required(false).defaultValue("Bearer ")
                .parameterType("header").build();//required表示是否必填，defaultvalue表示默认值

        ParameterBuilder ticketPar2 = new ParameterBuilder();
        ticketPar2.name("tenantId").description("space平台租户ID")
                .modelRef(new ModelRef("string")).required(false).defaultValue("")
                .parameterType("header").build();//required表示是否必填，defaultvalue表示默认值

        pars.add(ticketPar1.build());//添加完此处一定要把下边的带***的也加上否则不生效
        pars.add(ticketPar2.build());*//*
        return new Docket(DocumentationType.SWAGGER_2)
                //开关 生产环境关闭
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                //控制暴露出去的路径下的实例
                //如果某个接口不想暴露,可以使用以下注解
                //@ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
                .apis(RequestHandlerSelectors.basePackage("com.springcloud"))
                .paths(PathSelectors.any())
                .build();
              //  .globalOperationParameters(pars);//************把消息头添加;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("测试环境使用")
                //创建人
                .contact(new Contact("caofei","http://www.javasoso.com/","1053947231@qq.com"))
                .version("1.0")
                .build();
    }*/
    @Bean
    @Primary
    public SwaggerResourcesProvider newSwaggerResourcesProvider(Environment env, DocumentationCache documentationCache) {
        return new InMemorySwaggerResourcesProvider(env, documentationCache) {

            @Override
            public List<SwaggerResource> get() {
                // 1. 调用 InMemorySwaggerResourcesProvider
                List<SwaggerResource> resources = super.get();
                // 2. 添加 swagger-dubbo 的资源地址
                SwaggerResource dubboSwaggerResource = new SwaggerResource();
                dubboSwaggerResource.setName("dubbo");
                dubboSwaggerResource.setSwaggerVersion("2.0");
                dubboSwaggerResource.setUrl("/swagger-dubbo/api-docs");
                dubboSwaggerResource.setLocation("/swagger-dubbo/api-docs"); // 即将废弃，和 url 属性等价。
                resources.add(0, dubboSwaggerResource);
                return resources;
            }
        };
    }

}
