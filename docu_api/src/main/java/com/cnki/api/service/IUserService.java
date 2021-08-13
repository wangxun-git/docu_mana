package com.cnki.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cnki.common.entity.User;

public interface IUserService extends IService<User> {

    /**
     * 用户登录
     * @param user
     * @return
     */
    public String userLogin(User user);

    /**
     * 用户注册
     * @param user
     */
    public void userRegister(User user);

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    public User getUserInfoByToken(String token);

}
