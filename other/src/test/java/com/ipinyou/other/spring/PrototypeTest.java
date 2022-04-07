package com.ipinyou.other.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.stream.IntStream;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PrototypeTest {

    @Resource
    private PrototypeService prototypeService;

    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void test() {
        IntStream.rangeClosed(1, 10).parallel().forEach(value -> log.info(prototypeService.getService()));

        log.info("测试getBean");

        IntStream.rangeClosed(1, 10).parallel().forEach(value -> log.info(applicationContext.getBean(PrototypeService.class).getService()));

//        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);

    }

}
