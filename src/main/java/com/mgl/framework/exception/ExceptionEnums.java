package com.mgl.framework.exception;

import com.mgl.framework.exception.MglException;
import org.springframework.util.StringUtils;

public enum ExceptionEnums {

    SUCCESS(0, "成功"),

    SYSTEM_ERROR(100000, "系统错误"),

    PARAMS_ERROR(100001, "参数错误"),

    NOT_FIND_DATA(100002, "没有查询到数据"),

    DATA_IS_NULL(100003, "数据为null"),

    SNOWFLAKE_EXCEPTION(100004, "雪花算法异常"),
    ;

    private int code;

    private String desc;

    ExceptionEnums(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {return this.code;}

    public String getDesc() {return this.desc;}

    public MglException exp() {
        return new MglException(code, desc);
    }

    public MglException expMsg(String msg) {
        if (StringUtils.isEmpty(msg)) {
            msg = desc;
        }
        return new MglException(code, msg);
    }
}
