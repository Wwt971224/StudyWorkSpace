package com.ipinyou.other.concurrent;

import common.util.StopWatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;
import java.util.stream.IntStream;


@Slf4j
@SpringBootTest
public class ConcurrentFutureTest {
    ExecutorService executor = Executors.newFixedThreadPool(3);

    @Test
    public void test1() throws InterruptedException {
        System.out.println("提交1");
        System.out.println("提交2");
        System.out.println("提交3");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(String.valueOf(Thread.currentThread().getId()));
        log.info("{}主线程启动", Thread.currentThread().getId());
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            log.info("{}子线程启动", Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
                log.info("{}子线程完成", Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getId();
        }).thenCompose(a1 -> CompletableFuture.supplyAsync(() -> {
            log.info("{}子线程启动", Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
                log.info("{}子线程完成", Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getId();
        }));
        future.join();
        stopWatch.stop();
        log.info("{}主线程完成", Thread.currentThread().getId());
        log.info(stopWatch.prettyPrint());
    }

    @Test
    public void test2() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(String.valueOf(Thread.currentThread().getId()));
        log.info("{}主线程启动", Thread.currentThread().getId());
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            log.info("{}子线程启动", Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
                log.info("{}子线程完成", Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getId();
        }).thenComposeAsync(a1 -> CompletableFuture.supplyAsync(() -> {
            log.info("{}子线程启动", Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
                log.info("{}子线程完成", Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getId();
        }));
        future.join();
        stopWatch.stop();
        log.info("{}主线程完成", Thread.currentThread().getId());
        log.info(stopWatch.prettyPrint());
    }


    @Test
    public void test3() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(String.valueOf(Thread.currentThread().getId()));
        log.info("{}主线程启动", Thread.currentThread().getId());
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            log.info("{}子线程1启动", Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
                log.info("{}子线程1完成", Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getId();
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            log.info("{}子线程2启动", Thread.currentThread().getId());
            try {
                Thread.sleep(200);
                log.info("{}子线程2完成", Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getId();
        }), (aLong, aLong2) -> {
            log.info("{}做这个事", Thread.currentThread().getId());
            return 1L;
        });
        future.join();
        stopWatch.stop();
        log.info("{}主线程完成", Thread.currentThread().getId());
        log.info(stopWatch.prettyPrint());
    }

    @Test
    public void test4() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(String.valueOf(Thread.currentThread().getId()));
        log.info("{}主线程启动", Thread.currentThread().getId());
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            log.info("{}子线程1启动", Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
                log.info("{}子线程1完成", Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getId();
        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
            log.info("{}子线程2启动", Thread.currentThread().getId());
            try {
                Thread.sleep(200);
                log.info("{}子线程2完成", Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getId();
        }), (aLong, aLong2) -> {
            log.info("{}做这个事", Thread.currentThread().getId());
            return 1L;
        });
        future.join();
        stopWatch.stop();
        log.info("{}主线程完成", Thread.currentThread().getId());
        log.info(stopWatch.prettyPrint());
    }

    @Test
    public void test5() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(String.valueOf(Thread.currentThread().getId()));
        log.info("{}主线程启动", Thread.currentThread().getId());
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            log.info("{}子线程1启动", Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
                log.info("{}子线程1完成", Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getId();
        }).applyToEitherAsync(CompletableFuture.supplyAsync(() -> {
            log.info("{}子线程2启动", Thread.currentThread().getId());
            try {
                Thread.sleep(200);
                log.info("{}子线程2完成", Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getId();
        }), aLong -> {
            log.info("线程{}先执行完了", aLong);
            if (aLong.equals(16L)) {
                throw new RuntimeException();
            }
            return aLong;
        }).exceptionally(throwable -> {
            log.error("发生错误");
            return 999L;
        });
        log.info(future.join().toString());
        stopWatch.stop();
        log.info("{}主线程完成", Thread.currentThread().getId());
        log.info(stopWatch.prettyPrint());
    }

//    @Test
//    public void test6() {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start(String.valueOf(Thread.currentThread().getId()));
//        log.info("{}主线程启动", Thread.currentThread().getId());
//        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
//            log.info("{}子线程1启动", Thread.currentThread().getId());
//            try {
//                Thread.sleep(1000);
//                log.info("{}子线程1完成", Thread.currentThread().getId());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return Thread.currentThread().getId();
//        }).applyToEitherAsync(CompletableFuture.supplyAsync(() -> {
//            log.info("{}子线程2启动", Thread.currentThread().getId());
//            try {
//                Thread.sleep(200);
//                log.info("{}子线程2完成", Thread.currentThread().getId());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return Thread.currentThread().getId();
//        }), aLong -> {
//            log.info("线程{}先执行完了", aLong);
//            if (aLong.equals(15L)) {
//                throw new RuntimeException();
//            }
//            return aLong;
//        }).handle((aLong, throwable) -> {
//
//        });
//        log.info(future.join().toString());
//        stopWatch.stop();
//        log.info("{}主线程完成", Thread.currentThread().getId());
//        log.info(stopWatch.prettyPrint());
//    }

    @Test
    public void test7() {
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> {
                    log.info("{}子线程1启动", Thread.currentThread().getId());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("{}子线程1执行完成", Thread.currentThread().getId());
                }),
                CompletableFuture.runAsync(() -> {
                    log.info("{}子线程2启动", Thread.currentThread().getId());
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("{}子线程2执行完成", Thread.currentThread().getId());
                })
        ).join();
    }

    @Test
    public void test8() throws ExecutionException, InterruptedException {
        var forkJoinPool = new ForkJoinPool(8);
        var executorService = Executors.newFixedThreadPool(8);

        executorService.submit(() -> {
            IntStream.range(1, 10).parallel().forEach(value -> {
                try {
                    Thread.sleep(1000);
                    log.info("{}", Thread.currentThread().getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            log.info("任务提交完成{}", Thread.currentThread().getId());
        }).get();
//        CompletableFuture.runAsync(() -> {
//            IntStream.range(1, 10).parallel().forEach(value -> {
//                try {
//                    Thread.sleep(1000);
//                    log.info("{}", Thread.currentThread().getId());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//            log.info("任务提交完成{}", Thread.currentThread().getId());
//        }, executorService).join();

//        CompletableFuture.allOf(
//                CompletableFuture.runAsync(() -> IntStream.range(1, 10).parallel().forEach(value -> {
//                    try {
//                        Thread.sleep(1000);
//                        log.info("{}", Thread.currentThread().getId());
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }), forkJoinPool),
//                CompletableFuture.runAsync(() -> IntStream.range(1, 10).parallel().forEach(value -> {
//                    try {
//                        Thread.sleep(1000);
//                        log.info("{}", Thread.currentThread().getId());
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }))
//        ).join();
    }
}
