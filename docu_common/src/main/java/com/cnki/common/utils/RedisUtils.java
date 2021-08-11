package com.cnki.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis操作工具集
 */
@Component
public class RedisUtils {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 写入缓存
     * @param key
     * @param value
     */
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 写入缓存，设置过期时间
     * @param key
     * @param value
     * @param expireTime
     * @param timeUnit
     */
    public void setValue(String key, Object value, Long expireTime, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expireTime, timeUnit);
    }

    /**
     * 写入缓存
     * @param key
     * @param map
     */
    public void setHash(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 获取map集合
     * @param key
     * @return
     */
    public Map<Object, Object> getMapValue(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取集合中的值
     * @param key
     * @param hashKey
     * @return
     */
    public Object getValue(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 判断是否存在key值
     * @param key
     * @return
     */
    public boolean exits(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获取指定key的值
     * @param key
     * @return
     */
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除指定key
     * @param key
     * @return
     */
    public boolean deleteKey(String key) {
        Boolean delete = redisTemplate.delete(key);
        return delete;
    }

}
