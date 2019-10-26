package com.helloworld.transport.service.impl;

import com.helloworld.transport.Entity.Passenger;
import com.helloworld.transport.repository.PassengerRepository;
import com.helloworld.transport.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Class description
 *
 * @author LiangHang
 * @createTime 2019/10/26 22:14
 */
@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public void save() {
        Passenger passenger = new Passenger();
        Date date = new Date();
        passenger.setName("张安光").setIdCardNum("440881199510192341").setSex(0).setAge(28).setVipRank(8)
                .setAddress("廉江永兴100栋5楼").setRemarks("性情豪爽、乐观积极").setCreateTime(date).setUpdateTime(date);
        passengerRepository.save(passenger);
    }

    @Override
    public void del(Passenger passenger) {
        passengerRepository.delete(passenger);
    }

    @Override
    public List<Passenger> query() {
        List<Passenger> passengerList = passengerRepository.findAll();
        return passengerList;
    }

    @Override
    public Passenger queryById(int id) {
        return passengerRepository.findById(id).get();
    }

    @Override
    public void update(Passenger passenger) {
        passengerRepository.save(passenger);
    }
}
