package com.helloworld.transport.controller;

import com.helloworld.transport.Entity.Ticket;
import com.helloworld.transport.Entity.Passenger;
import com.helloworld.transport.service.PassengerService;
import com.helloworld.transport.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class description
 *
 * @author LiangHang
 * @createTime 2019/10/26 12:44
 */
@RestController
@RequestMapping("/transport/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PassengerService passengerService;

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping("/save")
    public String saveTicket() {
        // 获取乘客信息
        Passenger passenger = passengerService.queryById(6);
        ticketService.save(passenger);
        return "Save success!!";
    }
}