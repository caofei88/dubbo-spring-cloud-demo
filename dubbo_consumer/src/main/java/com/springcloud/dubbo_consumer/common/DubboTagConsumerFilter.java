package com.springcloud.dubbo_consumer.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.filter.TokenFilter;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Activate(group = {CommonConstants.CONSUMER},order = Integer.MIN_VALUE)
public class DubboTagConsumerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
                // 从上下文中获取dubbo.tag 扩展属性值标签
        HttpServletRequest  request=  RpcContext.getContext().getRequest(HttpServletRequest.class);
      //  HttpServletRequest request= SpringUtil.getBean(HttpServletRequest.class);
        if(request!=null&&StringUtils.isNotBlank(request.getHeader("accessToken"))){
            final String accessToken=request.getHeader("accessToken");
            Map<String, Object> attachments = invocation.getObjectAttachments();
            attachments.put("accessToken",accessToken);
            return invoker.invoke(invocation);
        }
        return invoker.invoke(invocation);
    }
}
