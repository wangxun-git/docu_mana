package com.cnki.api.controller;

import com.cnki.api.service.IUserService;
import com.cnki.common.entity.User;
import com.cnki.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        String token = userService.userLogin(user);
        return Result.ok().data("token", token);
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public Result register(@RequestBody User user) {
        userService.userRegister(user);
        return Result.ok();
    }

    @GetMapping("/userInfo")
    @ApiOperation(value = "用户信息")
    public Result getUserInfo(@RequestParam("token") String token) {
        User user = userService.getUserInfoByToken(token);
        return Result.ok().data("data", user);
    }

}
