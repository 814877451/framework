package com.mgl.framework.exception;

import com.mgl.framework.po.OpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MglExceptionResolver extends AbstractHandlerExceptionResolver {

    @Value("${mgl.framework.exception.code:500}")
    private String defaultErrorCode;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        OpResponse opResponse = new OpResponse();


        return null;
    }

    private String getExceptionCode(HttpServletRequest request, Exception ex) {
        if (ex instanceof MglException) {
            int code = ((MglException)ex).getCode();
            return code == 0 ? this.defaultErrorCode : String.valueOf(code);
        }
        return null;
    }

    private String getExceptionMessage(HttpServletRequest request, Exception ex) {
        return null;
    }
}
