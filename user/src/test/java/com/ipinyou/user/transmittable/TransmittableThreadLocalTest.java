package com.ipinyou.user.transmittable;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransmittableThreadLocalTest {

    public static TransmittableThreadLocal<String> transmittableThreadLocal = new TransmittableThreadLocal<>();
    public static ThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();

    public static void main(String[] args) {
        transmittableThreadLocal.set("name");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            executorService.execute(Objects.requireNonNull(TtlRunnable.get(() -> System.out.println(transmittableThreadLocal.get()))));
        }
        transmittableThreadLocal.remove();
        transmittableThreadLocal.set("name1");

        for (int i = 0; i < 10; i++) {
            executorService.execute(Objects.requireNonNull(TtlRunnable.get(() -> System.out.println(transmittableThreadLocal.get()))));
        }

        Executor ttlExecutor = TtlExecutors.getTtlExecutor(executorService);
        threadLocal.remove();
        threadLocal.set("age");
        for (int i = 0; i < 10; i++) {
            ttlExecutor.execute(() -> System.out.println(threadLocal.get()));
        }

        threadLocal.remove();
        threadLocal.set("age1");
        for (int i = 0; i < 10; i++) {
            ttlExecutor.execute(() -> System.out.println(threadLocal.get()));
        }

    }

}
