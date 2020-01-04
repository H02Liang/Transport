package com.helloworld.transport.service.impl;

import com.helloworld.transport.entity.Passenger;
import com.helloworld.transport.repository.PassengerDAO;
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
    private PassengerDAO passengerDAO;

    @Override
    public void save(Passenger passenger) {
        Date date = new Date();
        passenger.setCreateTime(date).setUpdateTime(date);
        passengerDAO.save(passenger);
    }

    @Override
    public void del(Passenger passenger) {
        passengerDAO.delete(passenger);
    }

    @Override
    public List<Passenger> query() {
        List<Passenger> passengerList = passengerDAO.findAll();
        return passengerList;
    }

    @Override
    public Passenger queryById(int id) {
        return passengerDAO.findById(id).get();
    }

    @Override
    public void update(Passenger passenger) {
        passengerDAO.save(passenger);
    }
}
