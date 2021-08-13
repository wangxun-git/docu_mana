package com.cnki.api.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnki.common.constant.DocuConstant;
import com.cnki.common.entity.User;
import com.cnki.common.utils.DocuManaUtils;
import com.cnki.common.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ApiUtils {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 从
     * @param id
     * @return
     */
    public User getRedisUserInfo(String id) {
        Map<String, Object> userMap = redisUtils.getMapValue(DocuConstant.Redis.USER + id);
        if (DocuManaUtils.isEmpty(userMap)) {
            return null;
        }
        User user = JSONObject.parseObject(JSON.toJSONString(userMap), User.class);
        return user;
    }

}
