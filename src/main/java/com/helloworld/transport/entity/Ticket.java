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
import java.util.Date;

/**
 * Class description 车票类，记录车票信息
 *
 * @author LiangHang
 * @createTime 2019/10/26 11:28
 */
@Data
@Accessors(chain = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_ticket")
@ApiModel(description = "票据信息类")
public class Ticket extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 4366796157463766926L;
    /** 票据编号 */
    @Id
    @ApiModelProperty(value = "票据编号", example = "000001")
    private String id;
    /** 票据类型：全票QP、半票BP */
    @ApiModelProperty(value = "票据类型", example = "QP")
    private String type;
    /** 始发站 */
    @ApiModelProperty(value = "始发站", example = "广州")
    private String depart;
    /** 到达站 */
    @ApiModelProperty(value = "到达站", example = "廉江")
    private String arrive;
    /** 始发时间 */
    @Column(name = "dept_time")
    @ApiModelProperty(value = "始发时间")
    private Date deptTime;
    /** 到达时间 */
    @Column(name = "arr_time")
    @ApiModelProperty(value = "到达时间")
    private Date arrTime;
    /** 乘客编号 */
    @Column(name = "passenger_id")
    @ApiModelProperty(value = "乘客编号", example = "000001")
    private String passengerId;
    /** 票价 */
    @ApiModelProperty(value = "票价", example = "120.00")
    private double price;
    /** 付款时间 */
    @Column(name = "pay_time")
    @ApiModelProperty(value = "付款时间")
    private Date payTime;
    /** 付款人编号 */
    @Column(name = "payer_id")
    @ApiModelProperty(value = "付款人编号", example = "000001")
    private String payerId;
}