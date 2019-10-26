package com.helloworld.transport.service;

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
     * @param ticket
     */
    void save(Ticket ticket);
}
