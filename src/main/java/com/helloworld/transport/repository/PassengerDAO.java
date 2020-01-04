package com.helloworld.transport.repository;

import com.helloworld.transport.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface description
 *
 * @author LiangHang
 * @createTime 2019/10/26 22:15
 */
public interface PassengerDAO extends JpaRepository<Passenger, Integer> {
    /**
     * 查询所有乘客信息
     * @return 乘客信息列表
     */
    @Override
    List<Passenger> findAll();
}
