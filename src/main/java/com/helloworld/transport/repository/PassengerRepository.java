package com.helloworld.transport.repository;

import com.helloworld.transport.Entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface description
 *
 * @author LiangHang
 * @createTime 2019/10/26 22:15
 */
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}
