/*
package com.springcloud.dubbo_provider.config;
import com.alibaba.dubbo.common.Constants;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.store.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

*/
/**
 * Dubbo线程池指标
 *
 * @author yinjihuan
 *//*

@Configuration
public class DubboThreadMetrics {
    @Autowired
    private MeterRegistry meterRegistry;
    private final Iterable<Tag> TAG = Collections.singletonList(Tag.of("thread.pool.name", "dubboThreadPool"));
    @PostConstruct
    public void init() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                DataStore dataStore = ExtensionLoader.getExtensionLoader(DataStore.class).getDefaultExtension();
                Map<String, Object> executors = dataStore.get(Constants.EXECUTOR_SERVICE_COMPONENT_KEY);
                for (Map.Entry<String, Object> entry : executors.entrySet()) {
                    ExecutorService executor = (ExecutorService) entry.getValue();
                    if (executor instanceof ThreadPoolExecutor) {
                        ThreadPoolExecutor tp = (ThreadPoolExecutor) executor;
                        Gauge.builder("dubbo.thread.pool.core.size", tp, ThreadPoolExecutor::getCorePoolSize)
                                .description("核心线程数")
                                .baseUnit("threads")
                                .register(meterRegistry);
                        Gauge.builder("dubbo.thread.pool.largest.size", tp, ThreadPoolExecutor::getLargestPoolSize)
                                .description("历史最高线程数")
                                .baseUnit("threads")
                                .register(meterRegistry);
                        Gauge.builder("dubbo.thread.pool.max.size", tp, ThreadPoolExecutor::getMaximumPoolSize)
                                .description("最大线程数")
                                .baseUnit("threads")
                                .register(meterRegistry);
                        Gauge.builder("dubbo.thread.pool.active.size", tp, ThreadPoolExecutor::getActiveCount)
                                .description("活跃线程数")
                                .baseUnit("threads")
                                .register(meterRegistry);
                        Gauge.builder("dubbo.thread.pool.thread.count", tp, ThreadPoolExecutor::getPoolSize)
                                .description("当前线程数")
                                .baseUnit("threads")
                                .register(meterRegistry);
                        Gauge.builder("dubbo.thread.pool.queue.size", tp, e -> e.getQueue().size())
                                .description("队列大小")
                                .baseUnit("threads")
                                .register(meterRegistry);
                        Gauge.builder("dubbo.thread.pool.taskCount", tp, ThreadPoolExecutor::getTaskCount)
                                .description("任务总量")
                                .baseUnit("threads")
                                .register(meterRegistry);
                        Gauge.builder("dubbo.thread.pool.completedTaskCount", tp, ThreadPoolExecutor::getCompletedTaskCount)
                                .description("已完成的任务量")
                                .baseUnit("threads")
                                .register(meterRegistry);
                    }
                }
            }
        }).start();
    }
}*/
