package com.springcloud.dubbo_provider.common;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

@Activate
@Slf4j
public class DubboTagProviderFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 从上下文中获取dubbo.tag 扩展属性值标签
        String tag = RpcContext.getContext().getAttachment("dubbo.tag");
         log.info("filter tag:{}",tag);
        return null;
    }
}
