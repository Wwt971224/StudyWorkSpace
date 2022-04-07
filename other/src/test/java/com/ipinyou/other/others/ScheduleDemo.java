package com.ipinyou.other.others;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ScheduleDemo {


    @Test
    public void timer_test() {
        var timer = new Timer();
        log.info("任务开始");
        int j = 0;
        AtomicInteger i = new AtomicInteger(0);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                var i1 = i.incrementAndGet();
                log.info("任务开始:{}",i1);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("任务结束:{}", i1);
            }
        }, 1000, 1000);
        while (true) {

        }
    }
}
