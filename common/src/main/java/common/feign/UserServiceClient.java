package common.feign;

import common.api.ApiDataObject;
import common.domain.vo.UserBaseVo;
import common.feign.fallback.UserServiceClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", fallbackFactory = UserServiceClientFallback.class)
public interface UserServiceClient {

    @GetMapping("/user/getById")
    ApiDataObject<UserBaseVo> getByUserId(@RequestParam("user_id") Long userId);

}
