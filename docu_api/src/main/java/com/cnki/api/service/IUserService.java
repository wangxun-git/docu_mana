package com.cnki.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cnki.common.entity.User;

public interface IUserService extends IService<User> {

    public String userLogin(User user);

}
