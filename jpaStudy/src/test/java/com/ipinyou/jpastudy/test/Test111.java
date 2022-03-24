package com.ipinyou.jpastudy.test;

import com.ipinyou.jpastudy.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test111 {

    @Resource
    private UserRepository userRepository;

    @Test
    @Transactional
    public void test() {
        System.out.println("userRepository.getOne(1L) = " + userRepository.getOne(1L));
    }

}
