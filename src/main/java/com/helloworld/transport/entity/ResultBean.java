package com.helloworld.transport.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Class description
 *
 * @author LiangHang
 * @createTime 2019/11/19 20:19
 */
@ApiModel(description = "通用响应返回对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ResultBean<T> {
    /**
     * 返回结果代码
     */
    @ApiModelProperty(value = "结果代码", position = 0)
    private int code;
    /**
     * 具体的错误信息
     */
    @ApiModelProperty(value = "错误信息", position = 1)
    private String message;
    /**
     * Exception类
     */
    @ApiModelProperty(value = "异常类", position = 2)
    private String exception;
    /**
     * 返回结果数据
     */
    @ApiModelProperty(value = "结果数据", position = 3)
    private T data;

    /**
     * 是否成功,0表示成功，其他都是失败
     */
    @ApiModelProperty(value = "是否成功", position = 4, example = "true")
    private boolean success = true;

    /**
     * 具体的异常类 <br>
     * JSON序列化时，将该字段忽略
     */
    @JSONField(serialize = false)
    @JsonIgnore
    @ApiModelProperty(value = "具体的异常类", position = 5)
    private Exception realException;
}
