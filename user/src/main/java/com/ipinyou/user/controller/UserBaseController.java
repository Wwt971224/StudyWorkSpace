package com.ipinyou.user.controller;


import com.ipinyou.user.service.IUserBaseService;
import common.api.ApiDataObject;
import common.api.ApiDataObjectBuilder;
import common.domain.dto.UserBaseDto;
import common.domain.vo.UserBaseVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wTai
 * @since 2022-01-26
 */
@RestController
@RequestMapping("/user")
public class UserBaseController {

    @Resource
    private IUserBaseService userBaseService;

    @PostMapping("/add")
    public ApiDataObject<Boolean> addUser(@RequestBody UserBaseDto userBaseDto) {
        return ApiDataObjectBuilder.newApiDataObject(userBaseService.addUser(userBaseDto));
    }

    @PostMapping("/update")
    public ApiDataObject<Boolean> updateUser(@RequestBody UserBaseDto userBaseDto) {
        return ApiDataObjectBuilder.newApiDataObject(userBaseService.updateUser(userBaseDto));
    }

    @GetMapping("/getById")
    public ApiDataObject<UserBaseVo> getByUserId(@RequestParam("user_id") Long userId) {
        return ApiDataObjectBuilder.newApiDataObject(userBaseService.getByUserId(userId));
    }

}
