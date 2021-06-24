package com.springcloud.dubbo_api.loadBalance;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.router.AbstractRouter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
public class GrayRouter extends AbstractRouter {
    @Override
    public <T> List<Invoker<T>> route(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        //新建一个list集合，用来存放符合dcCode的Invoker
        List<Invoker<T>> list = new ArrayList<>();

        //获取consumer设置的dcCode
        String consumerDcCode = invocation.getAttachment("dcCode");
        log.info("娃哈哈9999999");
        Iterator<Invoker<T>> iterator = invokers.iterator();

        //遍历provider的Invoker
        while (iterator.hasNext()) {
            Invoker<T> invoker = iterator.next();
            //获取provider上的dcCode
            String providerDcCode = invoker.getUrl().getParameter("dcCode");

            //两个dcCode相同，将provider的Invoker添加进新的集合
            if (consumerDcCode != null && consumerDcCode.trim().length() > 0
                    && consumerDcCode.equals(providerDcCode)) {
                list.add(invoker);
            }
        }
        //排空
        if (list.size() == 0) {
            log.info("没有provider的dcCode与之相对应,走默认轮询算法负载");
            list.addAll(invokers);
        }
        //调用官方的random负载均衡方法
        return list;
    }
}
