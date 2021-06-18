package com.springcloud.dubbo_api.loadBalance;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @AUTHOR zhangxf
 * @CREATE 2020-03-04 16:52
 */
@Slf4j
@Component
public class DcCodeBalance extends AbstractLoadBalance {
    public static final String NAME = "dcCode";

    public DcCodeBalance() {
        log.info("dcCode 灰度测试开始啦啦啦");
    }

    @Override
    protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
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
        return this.randomSelect(list, url, invocation);
    }

    /**
     * 官方的random负载均衡方法
     *
     * @param invokers
     * @param url
     * @param invocation
     * @param <T>
     * @return
     */
    private <T> Invoker<T> randomSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        int length = invokers.size();
       /* boolean sameWeight = true;
        int[] weights = new int[length];
        int firstWeight = this.getWeight((Invoker) invokers.get(0), invocation);
        weights[0] = firstWeight;
        int totalWeight = firstWeight;

        int offset;
        int i;
        for (offset = 1; offset < length; ++offset) {
            i = this.getWeight((Invoker) invokers.get(offset), invocation);
            weights[offset] = i;
            totalWeight += i;
            if (sameWeight && i != firstWeight) {
                sameWeight = false;
            }
        }

        if (totalWeight > 0 && !sameWeight) {
            offset = ThreadLocalRandom.current().nextInt(totalWeight);

            for (i = 0; i < length; ++i) {
                offset -= weights[i];
                if (offset < 0) {
                    return (Invoker) invokers.get(i);
                }
            }
        }*/

        return (Invoker) invokers.get(ThreadLocalRandom.current().nextInt(length));
    }

}