package com.ipinyou.shop.feign.fallback;

import com.ipinyou.shop.feign.UserServiceClient;
import common.api.ApiDataObject;
import common.domain.vo.UserBaseVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserServiceClientFallback implements FallbackFactory<UserServiceClient> {

    @Override
    public UserServiceClient create(Throwable throwable) {
        return new UserServiceClient() {
            @Override
            public ApiDataObject<UserBaseVo> getByUserId(Long userId) {
                log.error("发生异常");
                return new ApiDataObject<>();
            }
        };
    }
}
