package com.helloworld.transport.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author LiangHang
 */
@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@MappedSuperclass
@ApiModel(description = "基础信息类")
public class BaseEntity {
    /**
     * 信息备注
     */
    @ApiModelProperty(value = "信息备注", example = "HelloWorld!!!")
    private String remarks;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 更新时间
     */
    @Column(name = "update_Time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
