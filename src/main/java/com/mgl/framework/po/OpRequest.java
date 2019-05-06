package com.mgl.framework.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *@Description: 请求封装类
 *
 *@Author: magla
 *@Date: 2019/4/3_16:47
 *@since: v1
 */
@Data
@ApiModel("请求封装类")
public class OpRequest<P> implements Serializable {

    @ApiModelProperty(value = "签名", required = false)
    private String sign;

    @ApiModelProperty(value = "请求类", required = false)
    private P params;
}
