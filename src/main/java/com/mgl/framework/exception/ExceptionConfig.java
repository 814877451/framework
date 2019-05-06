package com.mgl.framework.exception;

import com.mgl.framework.po.OpResponse;
import com.mgl.framework.utils.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(MglException.class)
    public OpResponse MglExceptionHandle(MglException e) {
        String op = "ExceptionConfig.MglExceptionHandle";
        OpResponse opResponse = OpResponse.success();
        opResponse.setCode(e.getCode());
        opResponse.setDesc(e.getDesc());
        log.warn(Log.op(op).msg("自定义异常").kv("异常码", e.getCode()).kv("异常描述信息", e.getDesc()).toString());
        return opResponse;
    }

    @ExceptionHandler(Exception.class)
    public OpResponse ExceptionHandle(Exception e) {
        String op = "ExceptionConfig.ExceptionHandle";
        OpResponse opResponse = OpResponse.success();
        opResponse.setCode(500);
        opResponse.setDesc(e.getMessage());
        log.warn(Log.op(op).msg("系统异常").kv("异常信息", e).toString());
        return opResponse;
    }
}
