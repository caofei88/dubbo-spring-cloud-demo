/*
package com.springcloud.dubbo_consumer.common;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

@Activate(order = Integer.MIN_VALUE)
public class DubboTagConsumerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
                // 从上下文中获取dubbo.tag 扩展属性值标签
        String tag = RpcContext.getContext().getAttachment("dubbo.tag");
        return null;
    }
}
*/
