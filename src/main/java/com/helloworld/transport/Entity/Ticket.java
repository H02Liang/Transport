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
public class Ticket extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 4366796157463766926L;
    /**
     * 票据编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /**
     * 始发站
     */
    private String depart;
    /**
     * 到达站
     */
    private String arrive;
    /**
     * 始发时间
     */
    @Column(name = "dept_time")
    private Date deptTime;
    /**
     * 到达时间
     */
    @Column(name = "arr_time")
    private Date arrTime;
    /**
     * 乘客身份证号码
     */
    @Column(name = "passenger_id_card_num")
    private String passengerICNum;
    /**
     * 乘客姓名
     */
    @Column(name = "passenger_name")
    private String passengerName;
    /**
     * 票价
     */
    private double price;
}
