package com.helloworld.transport.service.impl;

import com.helloworld.transport.Entity.Ticket;
import com.helloworld.transport.repository.TicketRepository;
import com.helloworld.transport.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param ticket
     */
    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }
}
