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

    @ApiModelProperty(value = "响应码", required = true)
    private int code;

    @ApiModelProperty(value = "响应提示信息", required = false)
    private String message;

    @ApiModelProperty(value = "响应返回类", required = false)
    private T content;

    @ApiModelProperty(value = "响应返回token", required = false)
    private String token;
}
