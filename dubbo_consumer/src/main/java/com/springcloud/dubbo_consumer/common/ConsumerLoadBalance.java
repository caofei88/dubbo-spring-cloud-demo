/*
package com.springcloud.dubbo_consumer.common;

import com.google.common.collect.Lists;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.RandomLoadBalance;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
@Component
@Activate(group = CommonConstants.CONSUMER,order = Integer.MIN_VALUE)
public class ConsumerLoadBalance extends AbstractLoadBalance {
    @Override
    protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        List list= Lists.newArrayList();
        list.addAll(invokers);
        Map<String, String> paramMap= invocation.getAttachments();


        return null;
    }
}
*/
