package com.ipinyou.shop.service;

import com.alibaba.fastjson.JSON;
import com.ipinyou.shop.feign.UserServiceClient;
import com.ipinyou.shop.service.impl.IShopService;
import common.domain.vo.UserBaseVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
@Service
public class ShopServiceImpl implements IShopService {

    @Resource
    private UserServiceClient userServiceClient;
    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public void get() {
        log.info(MDC.get("traceId"));
//        threadPoolExecutor.execute(() ->
//        {
//            log.info(MDC.get("traceId"));
//            UserBaseVo data = userServiceClient.getByUserId(1L).getData();
//            System.out.println(JSON.toJSONString(data));
//        });

        CompletableFuture.runAsync(() ->
        {
            log.info(MDC.get("traceId"));
            UserBaseVo data = userServiceClient.getByUserId(1L).getData();
            System.out.println(JSON.toJSONString(data));
        }, threadPoolExecutor);

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
