package com.mgl.framework.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *@Description: 响应封装类
 *
 *@Author: magla
 *@Date: 2019/4/3_16:24
 *@since: v1
 */
@Data
@ApiModel("响应封装类")
public class OpResponse<T> implements Serializable {

    public static final int SUCCESS = 0;

    @ApiModelProperty(value = "响应码", required = true)
    private int code;

    @ApiModelProperty(value = "响应提示信息", required = false)
    private String desc;

    @ApiModelProperty(value = "响应返回类", required = false)
    private T content;

    @ApiModelProperty(value = "响应返回token", required = false)
    private String token;

    public static <T> OpResponse<T> success() {
        OpResponse<T> resp = new OpResponse<T>();
        resp.code = SUCCESS;
        return resp;
    }
}
