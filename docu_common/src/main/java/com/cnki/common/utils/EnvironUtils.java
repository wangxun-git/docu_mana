package com.cnki.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironUtils {

    @Autowired
    private Environment environment;

    /**
     * 获取每页查询数量
     * @return
     */
    public Integer getPageNumber() {
        String number = environment.getProperty("mybatis.select.number");
        return Integer.valueOf(number);
    }

    /**
     * 获取配置文件中内容
     * @param key
     * @return
     */
    public String getProperty(String key) {
        String property = environment.getProperty(key);
        return property;
    }

}
