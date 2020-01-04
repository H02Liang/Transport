package com.helloworld.transport.controller;

import com.alibaba.fastjson.JSON;
import com.helloworld.transport.entity.Passenger;
import com.helloworld.transport.entity.ResultBean;
import com.helloworld.transport.entity.Ticket;
import com.helloworld.transport.service.PassengerService;
import com.helloworld.transport.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class description
 *
 * @author LiangHang
 * @createTime 2019/10/26 12:44
 */
@RestController
@RequestMapping("/transport/ticket")
@Api(value = "TicketController", tags = { "票据信息处理类" })
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PassengerService passengerService;


    @ApiOperation(value = "saveTicket保存票据信息", notes = "保存票据信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "logParam", dataType = "Ticket")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ResultBean.class)
    })
    @PostMapping("/save")
    public String saveTicket(@RequestBody String logParam) {
        // 获取乘客信息
        Passenger passenger = passengerService.queryById(6);
        ticketService.save(passenger);
        return "Save success!!";
    }


    @ApiOperation(value = "queryByPassengerId按乘客id查询票据", notes = "按乘客id查询票据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", responseContainer = "List", response = Ticket.class)
    })
    @PostMapping("/query/{id}")
    public String queryByPassengerId(@PathVariable(value = "id") String id) {
        Ticket ticket = ticketService.queryById(5);
        return JSON.toJSONString(ticket);
    }
}