package com.cnki.api.controller;

import com.cnki.api.service.IUserService;
import com.cnki.common.entity.User;
import com.cnki.common.utils.JwtTokenUtils;
import com.cnki.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "用户管理")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public Result login(@RequestBody User user) {
        log.info("用户信息 : {}", user);
        String token = userService.userLogin(user);
        log.info("用户id : {}", JwtTokenUtils.getId(token));
        return Result.ok().data("token", token);
    }

}
