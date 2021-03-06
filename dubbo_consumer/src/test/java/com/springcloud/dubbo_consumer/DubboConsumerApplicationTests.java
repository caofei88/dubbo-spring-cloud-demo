package com.springcloud.dubbo_consumer;
import com.springcloud.dubbo_api.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
public class DubboConsumerApplicationTests {
    @Reference(check=false,mock = "com.springcloud.dubbo_provider.service.HelloServiceI")
    private HelloService helloService;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void helloDubboTest() {
        helloService.hello("caofei");
    }

    /*
     * 1、mockMvc.perform执行一个请求。
     * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
     * 3、ResultActions.param添加请求传值
     * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
     * 5、ResultActions.andExpect添加执行完成后的断言。
     * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
     *   比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
     * 7、ResultActions.andReturn表示执行完成后返回相应的结果。
     */
    @Test
    public void user() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/a/b/c")
                .param("name", "trump")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(result -> {
                    String json = result.getResponse().getContentAsString();

                    log.info("获取响应信息为：\n" + json);
                });
    }
}
