package com.ipinyou.shop.controller;

import com.ipinyou.shop.service.impl.IShopService;
import common.api.ApiDataObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private IShopService shopService;

    @GetMapping("/get")
    public ApiDataObject<Void> get() {
        shopService.get();
        return new ApiDataObject<>();
    }


}
