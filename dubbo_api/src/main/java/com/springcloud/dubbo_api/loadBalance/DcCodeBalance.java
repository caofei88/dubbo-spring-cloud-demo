package com.springcloud.dubbo_api.loadBalance;

import com.springcloud.dubbo_api.service.HelloService;
import com.springcloud.dubbo_api.service.PushService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.RandomLoadBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @AUTHOR zhangxf
 * @CREATE 2020-03-04 16:52
 */
@Slf4j
@Component
public class DcCodeBalance extends AbstractLoadBalance {

    @Autowired
    HttpServletRequest request;
    public static final String NAME = "dcCode";
    private static final RandomLoadBalance randomLoadBalance = new RandomLoadBalance();

    public DcCodeBalance() {
        log.info("dcCode 灰度测试开始啦啦啦");
    }

    /*  @Autowired
      private PushService pushService;*/
    @Override
    protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        //新建一个list集合，用来存放符合dcCode的Invoker
        List<Invoker<T>> list = new ArrayList<>();
     /*   HttpServletRequest  request=  RpcContext.getServerContext().getRequest(HttpServletRequest.class);
        //获取consumer设置的dcCode
        try {
            PushService pushService= (PushService)SpringUtil.getBean("pushServiceImpl");
        } catch (Exception e) {
        }

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        String accessToken="";
        if(attributes!=null){
            HttpServletRequest requestTemp = attributes.getRequest();
             accessToken=requestTemp.getHeader("accessToken");
            if(StringUtils.isNotBlank(accessToken)){
                invocation.getAttachments().put("dcCode","20889dubbo-test-provider6");
            }
            log.info("token:{}",requestTemp.getHeader("accessToken"));
        }*/
        //String consumerDcCode = StringUtils.isEmpty(accessToken)?invocation.getAttachment("dcCode"):accessToken;
        String consumerDcCode = invocation.getAttachment("dcCode");
        log.info("娃哈哈9999999");
        Iterator<Invoker<T>> iterator = invokers.iterator();

        //遍历provider的Invoker
        while (iterator.hasNext()) {
            Invoker<T> invoker = iterator.next();
            //获取provider上的dcCode
       /*     int port=invoker.getUrl().getPort();
            String application=invoker.getUrl().getParameter("remote.application");
            String testStr=Integer.toString(port).concat(application);

            String providerDcCode = testStr;*/
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
            //  throw new RuntimeException("没有provider的dcCode与之相对应");
        }
        //调用官方的random负载均衡方法
        return randomLoadBalance.select(list, url, invocation);
        //return this.randomSelect(list, url, invocation);
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
        boolean sameWeight = true;
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
        }

        return (Invoker) invokers.get(ThreadLocalRandom.current().nextInt(length));
    }


    int getWeight(Invoker<?> invoker, Invocation invocation) {
        URL url = invoker.getUrl();
        int weight;
        if ("org.apache.dubbo.registry.RegistryService".equals(url.getServiceInterface())) {
            weight = url.getParameter("registry.weight", 100);
        } else {
            weight = url.getMethodParameter(invocation.getMethodName(), "weight", 100);
            if (weight > 0) {
                long timestamp = invoker.getUrl().getParameter("timestamp", 0L);
                if (timestamp > 0L) {
                    long uptime = System.currentTimeMillis() - timestamp;
                    if (uptime < 0L) {
                        return 1;
                    }

                    int warmup = invoker.getUrl().getParameter("warmup", 600000);
                    if (uptime > 0L && uptime < (long) warmup) {
                        weight = calculateWarmupWeight((int) uptime, warmup, weight);
                    }
                }
            }
        }

        return Math.max(weight, 0);
    }

    static int calculateWarmupWeight(int uptime, int warmup, int weight) {
        int ww = (int) ((float) uptime / ((float) warmup / (float) weight));
        return ww < 1 ? 1 : Math.min(ww, weight);
    }
}