package com.helloworld.transport.service;

import com.helloworld.transport.Entity.Passenger;

import java.util.List;

/**
 * Class description
 *
 * @author LiangHang
 * @createTime 2019/10/26 22:10
 */
public interface PassengerService {
    /**
     * 添加乘客信息
     */
    void save();

    /**
     * 删除乘客信息
     * @param passenger
     */
    void del(Passenger passenger);

    /**
     * 查询乘客信息
     * @return 乘客列表
     */
    List<Passenger> query();

    /**
     * 按id查询乘客信息
     * @param id
     * @return
     */
    Passenger queryById(int id);

    /**
     * 更新乘客信息
     */
    void update(Passenger passenger);
}
