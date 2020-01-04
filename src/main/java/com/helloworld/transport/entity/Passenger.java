package com.helloworld.transport.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Class description 乘客类，记录乘客信息
 *
 * @author LiangHang
 * @createTime 2019年10月26日01:40:23
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "t_passenger")
@ApiModel(description = "乘客信息类")
public class Passenger extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -2261198871320755689L;
    /** 乘客编号 */
    @Id
    @ApiModelProperty(value = "乘客编号", example = "000001", position = 0)
    private String id;
    /** 乘客姓名 */
    @ApiModelProperty(value = "乘客姓名", example = "张三", position = 1)
    private String name;
    /** 身份证号码 */
    @Column(name = "id_card_num")
    @ApiModelProperty(value = "身份证号码", example = "440881199602121542", position = 2)
    private String idCardNum;
    /** 性别 */
    @ApiModelProperty(value = "性别", example = "0", position = 3)
    private int sex;
    /** 年龄 */
    @ApiModelProperty(value = "年龄", example = "23", position = 0)
    private int age;
    /** 会员等级 */
    @Column(name = "member_type")
    @ApiModelProperty(value = "会员等级", example = "5", position = 0)
    private int memberType;
    /** 常用住址 */
    @ApiModelProperty(value = "常用住址", example = "广东省廉江市吉水镇", position = 0)
    private String address;
}
