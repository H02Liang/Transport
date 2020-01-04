package com.helloworld.transport.service;

import com.helloworld.transport.entity.Passenger;
import com.helloworld.transport.entity.Ticket;

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

    /**
     * 按id查询票据
     * @param id
     * @return 票据
     */
    Ticket queryById(int id);
}
