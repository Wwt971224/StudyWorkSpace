package com.ipinyou.shop;

import common.feign.fallback.UserServiceClientFallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@EnableAspectJAutoProxy
@EnableHystrix
//@EnableFeignClients(basePackages = "common.feign")
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class } )
//@ComponentScan(basePackages = {"common.feign.fallback","com.ipinyou.shop"})
public class ShopApplication {

    public static void main(String[] args) {
        var applicationContext = SpringApplication.run(ShopApplication.class, args);
        System.out.println("applicationContext.getBean(UserServiceClientFallback.class) = " + applicationContext.getBean(UserServiceClientFallback.class));
    }

}
