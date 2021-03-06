package com.helloworld.transport.service.impl;

import com.helloworld.transport.Entity.Passenger;
import com.helloworld.transport.Entity.Ticket;
import com.helloworld.transport.repository.TicketRepository;
import com.helloworld.transport.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class description
 *
 * @author LiangHang
 * @createTime 2019/10/26 11:57
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    /**
     * 生成票据
     *
     * @param passenger
     */
    @Override
    public void save(Passenger passenger) {
        Ticket ticket = new Ticket();
        Date date = new Date();
        Date deptTime = null;
        try {
            deptTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-10-26 16:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ticket.setPassengerName(passenger.getName())
                .setPassengerICNum(passenger.getIdCardNum()).setDepart("廉江永兴").setArrive("最近的小巷子")
                .setDeptTime(deptTime)
                .setPrice(600).setRemarks("玉林。。。").setCreateTime(date).setUpdateTime(date);
        ticketRepository.save(ticket);
    }
}
