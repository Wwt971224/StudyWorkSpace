package com.ipinyou.other.features;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class CountDownLatchDemo {

    @SneakyThrows
    @Test
    public void test() {
        var executorService = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
        var collect = IntStream.rangeClosed(1, 1).boxed().collect(Collectors.toList());
        var collect1 = IntStream.range(1, 1).boxed().collect(Collectors.toList());
        var countDownLatch = new CountDownLatch(collect.size());
        collect.forEach(
                index -> {
                    log.info("线程id{}", Thread.currentThread().getId());
                    add(countDownLatch, executorService);

                }
        );
//        add(countDownLatch, executorService);
        System.out.println(executorService.getQueue().size());
//        countDownLatch.await();
        Thread.sleep(10000);
        executorService.shutdown();
        log.info("结束了");
    }

    public void add(CountDownLatch countDownLatch, ExecutorService executorService) {
        AtomicInteger i = new AtomicInteger(1);
        executorService.submit(() -> {
            try {
                sum();
                log.info("当先线程id{}", Thread.currentThread().getId());
                while (i.get() < 1000) {
                    add(countDownLatch, executorService);
                    i.getAndIncrement();
                }
                sum();
            } finally {
                log.info(String.valueOf(countDownLatch.getCount()));
                countDownLatch.countDown();
            }
        });
    }

    public void sum() {
        var l = System.currentTimeMillis();
        long j = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            j += i;
        }
        System.out.println("aaaa"+(System.currentTimeMillis()-l));
    }

}
