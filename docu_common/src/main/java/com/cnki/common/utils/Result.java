package com.cnki.common.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回值包装
 */
@Data
public class Result {

    public Integer code;

    private String msg;

    private Map<String, Object> data = new HashMap<>();

    public static Result ok() {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.code());
        result.setMsg(ResultCode.SUCCESS.msg());
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(ResultCode.FIAL.code());
        result.setMsg(ResultCode.FIAL.msg());
        return result;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public Result data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

}
