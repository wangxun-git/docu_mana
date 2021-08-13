package com.cnki.pub.controller;

import com.cnki.common.entity.home.Banner;
import com.cnki.common.utils.Result;
import com.cnki.pub.service.IBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@Api(value = "首页轮播管理")
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private IBannerService bannerService;

    @ApiOperation(value = "获取banner列表")
    @GetMapping("/getBanner/{index}/{number}")
    public Result getBannerList(@PathVariable("index") Integer index, @PathVariable("number") Integer number) {
        List<Banner> bannerList = bannerService.getBannerListByIndex(index, number);
        return Result.ok().data("data", bannerList);
    }

}
