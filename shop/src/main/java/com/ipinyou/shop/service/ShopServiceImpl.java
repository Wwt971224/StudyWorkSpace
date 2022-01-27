package com.ipinyou.shop.service;

import com.ipinyou.shop.feign.UserServiceClient;
import com.ipinyou.shop.service.impl.IShopService;
import common.domain.vo.UserBaseVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class ShopServiceImpl implements IShopService {

    @Resource
    private UserServiceClient userServiceClient;

    @Override
    public void get() {
        log.info(MDC.get("traceId"));
        Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
        CompletableFuture.runAsync(() -> {
            MDC.setContextMap(copyOfContextMap);
            UserBaseVo data = userServiceClient.getByUserId(1L).getData();
            System.out.println(data);
        });

    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        MDC.put("name", "连岐");
        Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
        executorService.submit(
                () ->{
                    MDC.setContextMap(copyOfContextMap);
                    System.out.println("MDC.get(\"name\") = " + MDC.get("name"));
                }
        );
    }
}
