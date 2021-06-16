/*
package com.springcloud.dubbo_provider.exception;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DemoRpcRuntimeExceptionHandler { 

    */
/**
     * service层的RuntimeException统一处理器 * 可以将RuntimeException分装成RpcRuntimeException抛给调用端处理 或自行处理 * * @param exception
     *//*

    @AfterThrowing(throwing = "exception", pointcut = "execution(* com.springcloud.dubbo_provider.service.*.*(..))")
    public void afterThrow(Throwable exception) {
        if (exception instanceof RuntimeException) {
            log.error("DemoRpcRuntimeExceptionHandler side->exception occured: " + exception.getMessage(), exception);
            throw new DemoRpcRunTimeException(exception);
        } // log.error("DemoRpcRuntimeExceptionHandler side->exception occured: " + // exception.getMessage(), exception); }
    }
}
*/
