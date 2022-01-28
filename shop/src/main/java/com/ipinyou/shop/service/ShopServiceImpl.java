package com.ipinyou.shop.service;

import com.alibaba.fastjson.JSON;
import com.ipinyou.shop.feign.UserServiceClient;
import com.ipinyou.shop.service.impl.IShopService;
import common.domain.vo.UserBaseVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

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

}
