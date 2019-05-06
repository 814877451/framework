package com.mgl.framework.exception;

/**
 *@Description: 自定义异常
 *
 *@Author: magla
 *@Date: 2019/4/3_17:42
 *@since: v1.0.0
 */
public class MglException extends RuntimeException{

    private int code;

    private String desc;

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return  this.desc;
    }

    public MglException(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
