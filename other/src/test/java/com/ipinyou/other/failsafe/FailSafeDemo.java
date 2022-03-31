package com.ipinyou.other.failsafe;

import com.google.common.util.concurrent.RateLimiter;
import com.ipinyou.other.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;
import net.jodah.failsafe.function.CheckedSupplier;
import org.junit.Test;

import java.time.Duration;
import java.util.stream.IntStream;

@Slf4j
public class FailSafeDemo {

    @Test
    public void rateLimiter_test() {
        @SuppressWarnings("all")
        var rateLimiter = RateLimiter.create(10.0);
        IntStream.rangeClosed(1, 10).parallel().forEach(
                value ->{

                    rateLimiter.acquire();
//                    try {
//                        Thread.sleep(0);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    log.info("执行了");
                }
        );

    }

    @Test
    public void retryPolicy_test() {
        var retryPolicy = new RetryPolicy<String>()
                .handleIf(ex -> {
                    if (ex instanceof BizException) {
                        return 100 == ((BizException) ex).getCode();
                    }
                    return false;
                })
                .withDelay(Duration.ofSeconds(1))
                .withMaxRetries(3)
                .onRetry(x -> log.warn("接口重试中"))
                .onFailure(x -> log.error("处理失败"));

        CheckedSupplier<String> supplier = () -> {
            System.out.println("进来了");
            if (true) {
                throw new BizException(100, "嘤嘤嘤");
            }
            return "哈哈哈";
        };
        Failsafe.with(retryPolicy, retryPolicy).get(supplier);
    }

}
