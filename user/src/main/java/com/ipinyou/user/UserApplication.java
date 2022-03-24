package com.ipinyou.user;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

@EnableAspectJAutoProxy
@EnableHystrix
@EnableFeignClients(basePackages = {"com.ipinyou.**.feign"})
@EnableDiscoveryClient
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(UserApplication.class, args);
        var propertiesList = applicationContext.getEnvironment().getPropertySources().stream()
                .filter(propertySource -> propertySource instanceof OriginTrackedMapPropertySource)
                .flatMap(propertySource -> Arrays.stream(((OriginTrackedMapPropertySource) propertySource).getPropertyNames()))
                .map(propertyName -> {
                    var properties = new Properties();
                    properties.setProperty(propertyName, applicationContext.getEnvironment().getProperty(propertyName));
                    return properties;
                }).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(propertiesList));
    }

}
