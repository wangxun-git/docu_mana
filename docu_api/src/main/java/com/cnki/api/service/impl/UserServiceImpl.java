package com.cnki.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cnki.api.service.IUserService;
import com.cnki.api.utils.ApiUtils;
import com.cnki.common.constant.DocuConstant;
import com.cnki.common.entity.User;
import com.cnki.common.exception.DocuManaException;
import com.cnki.common.mapper.UserMapper;
import com.cnki.common.utils.DocuManaUtils;
import com.cnki.common.utils.JwtTokenUtils;
import com.cnki.common.utils.RedisUtils;
import com.cnki.common.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ApiUtils apiUtils;

    @Override
    public String userLogin(User user) {
        //从redis中获取缓存数据

        User selectOne = apiUtils.getRedisUserInfo(user.getId());
        if (DocuManaUtils.isEmpty(selectOne)) {
            //获取该用户是否存在
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("id, salt, password");
            queryWrapper.eq("id", user.getId());
            selectOne = baseMapper.selectOne(queryWrapper);

            if (DocuManaUtils.isEmpty(selectOne)) {
                throw new DocuManaException(ResultCode.NOT_FOUND.code(), "该账号[" + user.getId() + "]不存在");
            }
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

    @Override
    public void userRegister(User user) {
        //校验该用户是否存在
        User selectOne = baseMapper.selectById(user.getId());
        if (DocuManaUtils.isNotEmpty(selectOne)) {
            throw new DocuManaException(ResultCode.FIAL.code(), "该用户[" + user.getId() + "]已经存在");
        }
        //生成盐值
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        String password = user.getPassword();
        password = DocuManaUtils.getMD5Pswd(salt, password);
        user.setPassword(password);

        int count = baseMapper.insert(user);
        if (count != 1) {
            throw new DocuManaException(ResultCode.FIAL.code(), "用户注册失败");
        }

        //用户信息添加到
        Map<String, Object> userMap = JSONObject.parseObject(JSON.toJSONString(user), new TypeReference<Map<String, Object>>(){});

        redisUtils.setHash(DocuConstant.Redis.USER + user.getId(), userMap);
    }

    @Override
    public User getUserInfoByToken(String token) {
        //从token中获取用户id
        String id = JwtTokenUtils.getId(token);
        User user = apiUtils.getRedisUserInfo(id);

        if (DocuManaUtils.isEmpty(user)) {
            //获取该用户是否存在
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", user.getId());
            user = baseMapper.selectOne(queryWrapper);

            if (DocuManaUtils.isEmpty(user)) {
                throw new DocuManaException(ResultCode.NOT_FOUND.code(), "该账号[" + user.getId() + "]不存在");
            }
        }

        user.setSalt("");
        return user;
    }

}
