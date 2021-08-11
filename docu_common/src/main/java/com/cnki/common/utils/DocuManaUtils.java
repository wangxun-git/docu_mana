package com.cnki.common.utils;

import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 系统工具类
 */
public class DocuManaUtils {

    /**
     * 判断是否为空
     * @return
     */
    public static boolean isEmpty(Object object){
        if (object == null){
            return true;
        }
        if (object instanceof String){
            if (object == null || (String) object == ""){
                return true;
            }
        }else if (object instanceof List){
            if (object == null || ((List) object).isEmpty()){
                return true;
            }
        }else if (object instanceof Set){
            if (object == null || ((Set) object).isEmpty()){
                return true;
            }
        }else if (object instanceof Map){
            if (object == null || ((Map) object).isEmpty()){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断值非空
     * @param object
     * @return
     */
    public static boolean isNotEmpty(Object object){
        return !isEmpty(object);
    }

    /**
     * 获取加密密码
     * @param salt
     * @param pswd
     * @return
     */
    public static String getMD5Pswd(String salt, String pswd) {
        pswd = DigestUtils.md5DigestAsHex((salt + pswd + pswd).getBytes());
        return pswd;
    }

}
