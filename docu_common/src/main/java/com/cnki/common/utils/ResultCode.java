package com.cnki.common.utils;

public enum ResultCode {

    SUCCESS(10000, "请求成功"),

    FIAL(10001, "请求失败"),

    NOT_FOUND(10002, "请求资源不存在"),

    PARAMETER_NOT_NULL(10003, "参数不能为空"),

    FIlE_FIALED(10007, "文件操作失败");

    private Integer code;

    private String msg;

    ResultCode(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer code(){
        return code;
    }

    public String msg(){
        return msg;
    }

}
