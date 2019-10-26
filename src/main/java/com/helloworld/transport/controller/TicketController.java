package com.helloworld.transport.controller;

import com.helloworld.transport.Entity.Ticket;
import com.helloworld.transport.Entity.Passenger;
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
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping("/save")
    public String saveTicket() {
        Ticket ticket = new Ticket();
        Date date = new Date();
        Passenger passenger = new Passenger();
        passenger.setName("张安光").setIdCardNum("440881199510192341").setSex(0).setAge(28).setVipRank(8)
                .setAddress("廉江永兴100栋5楼").setRemarks("性情豪爽、乐观积极").setCreateTime(date).setUpdateTime(date);
        try {
            ticket.setPassengerName(passenger.getName())
                    .setPassengerICNum(passenger.getIdCardNum()).setDepart("廉江永兴").setArrive("最近的小巷子")
                    .setDeptTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-10-26 16:00:00"))
                    .setPrice(600).setRemarks("玉林。。。").setCreateTime(date).setUpdateTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ticketService.save(ticket);
        return "Save success!!\n" + ticket;
    }

}
