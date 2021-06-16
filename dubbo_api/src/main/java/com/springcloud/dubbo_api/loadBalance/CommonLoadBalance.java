package com.springcloud.dubbo_api.loadBalance;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;
import java.util.List;
import java.util.Map;
@Slf4j
public class CommonLoadBalance extends AbstractLoadBalance {

    public CommonLoadBalance() {
       log.info("灰度发布-服务选择器已启动...");
    }
    private static CommonLoadBalance instance;


    public static CommonLoadBalance getInstance() {
        if (instance == null) {
            instance = new CommonLoadBalance();
        }
        return instance;
    }
    @Override
    protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        log.info("***********start 666666666***************");
        List list= Lists.newArrayList();
        list.addAll(invokers);
        Map<String, String> paramMap= invocation.getAttachments();
        //默认走轮需的策略
        log.info("***********end  888888888888***************");
        return CommonLoadBalance.getInstance().select(list, url, invocation);
    }
}
