package com.helloworld.transport.controller;

import com.helloworld.transport.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

/**
 * Class description
 *
 * @author LiangHang
 * @createTime 2019/10/26 22:03
 */
@RestController
@RequestMapping("/transport/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping("/save")
    public String savePassenger(){
        passengerService.save();
        return "Save success!!";
    }
}
