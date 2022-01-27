package com.ipinyou.shop.feign;

import common.api.ApiDataObject;
import common.domain.vo.UserBaseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/user/getById")
    ApiDataObject<UserBaseVo> getByUserId(@RequestParam("user_id") Long userId);

}
