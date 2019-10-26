package com.helloworld.transport.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Passenger extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -2261198871320755689L;
    /**
     * 乘客编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /**
     * 乘客姓名
     */
    private String name;
    /**
     * 身份证号码
     */
    @Column(name = "id_card_num")
    private String idCardNum;
    /**
     * 性别
     */
    private int sex;
    /**
     * 年龄
     */
    private int age;
    /**
     * 会员等级
     */
    @Column(name = "vip_rank")
    private int vipRank;
    /**
     * 常用住址
     */
    private String address;
}
