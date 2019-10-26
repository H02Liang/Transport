package com.helloworld.transport.service;

import com.helloworld.transport.Entity.Passenger;
import com.helloworld.transport.Entity.Ticket;

/**
 * Class description
 *
 * @author LiangHang
 * @createTime 2019/10/26 11:53
 */
public interface TicketService {
    /**
     * 生成票据
     *
     * @param passenger
     */
    void save(Passenger passenger);
}
