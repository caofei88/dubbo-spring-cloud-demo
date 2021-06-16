package com.springcloud.dubbo_provider.common;

import com.google.common.collect.Lists;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Activate(group = CommonConstants.PROVIDER,order = Integer.MIN_VALUE)
public class ProviderLoadBalance extends AbstractLoadBalance {
    @Override
    protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        List list= Lists.newArrayList();
        list.addAll(invokers);
        Map<String, String> paramMap= invocation.getAttachments();


        return null;
    }
}
