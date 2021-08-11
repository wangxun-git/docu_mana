package com.cnki.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cnki.api.service.IUserService;
import com.cnki.common.constant.DocuConstant;
import com.cnki.common.entity.User;
import com.cnki.common.exception.DocuManaException;
import com.cnki.common.mapper.UserMapper;
import com.cnki.common.utils.DocuManaUtils;
import com.cnki.common.utils.JwtTokenUtils;
import com.cnki.common.utils.RedisUtils;
import com.cnki.common.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public String userLogin(User user) {
        //从redis中获取缓存数据
        Object value = redisUtils.getValue(DocuConstant.Redis.USER + user.getId());
        User selectOne = null;
        if (DocuManaUtils.isEmpty(value)) {
            //获取该用户是否存在
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("id, salt, password");
            queryWrapper.eq("id", user.getId());
            selectOne = baseMapper.selectOne(queryWrapper);

            if (DocuManaUtils.isEmpty(selectOne)) {
                throw new DocuManaException(ResultCode.NOT_FOUND.code(), "该账号[" + user.getId() + "]不存在");
            }
        }else {
            selectOne = (User) value;
        }
        //匹配密码信息
        String pswd = DocuManaUtils.getMD5Pswd(selectOne.getSalt(), user.getPassword());

        if (!pswd.equals(selectOne.getPassword())) {
            throw new DocuManaException(ResultCode.FIAL.code(), "密码输入错误");
        }

        //返回登录token
        String token = JwtTokenUtils.getToken(selectOne.getId(), selectOne.getName(), selectOne.getPassword());

        //存储在redis中
        redisUtils.setValue(DocuConstant.Redis.USER_ALIVE + user.getId(), token, 1L, TimeUnit.DAYS);

        return token;
    }
}
