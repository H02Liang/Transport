package com.helloworld.transport.repository;

import com.helloworld.transport.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class description
 *
 * @author LiangHang
 * @createTime 2019/10/26 11:54
 */
@Repository
public interface TicketDAO extends JpaRepository<Ticket, Integer> {

}
