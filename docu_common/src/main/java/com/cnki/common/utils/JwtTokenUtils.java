package com.cnki.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * token操作集
 */
public class JwtTokenUtils {

    public static final long EXPIRE = 1000 * 60 * 60 * 1;  //过期时间 1天

    /**
     * 生成token
     * @param id
     * @param name
     * @param pswd
     * @return
     */
    public static String getToken(String id, String name, String pswd) {
        String token = JWT.create().withClaim("id", id)
                .withClaim("name", name)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE))
                .sign(Algorithm.HMAC256(pswd));
        return token;
    }

    /**
     * 返回id
     * @param request
     * @return
     */
    public static String getId(HttpServletRequest request) {
        String token = request.getHeader("userToken");
        String id = JWT.decode(token).getClaim("id").asString();
        return id;
    }

    /**
     * 返回id
     * @param token
     * @return
     */
    public static String getId(String token) {
        String id = JWT.decode(token).getClaim("id").asString();
        return id;
    }

    /**
     *
     * @param request
     * @return
     */
    public static String getName(HttpServletRequest request) {
        String token = request.getHeader("userToken");
        String name = JWT.decode(token).getClaim("name").asString();
        return name;
    }

    /**
     *
     * @param token
     * @return
     */
    public static String getName(String token) {
        String name = JWT.decode(token).getClaim("name").asString();
        return name;
    }

}
