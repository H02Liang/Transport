package com.helloworld.transport.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.helloworld.transport.entity.Passenger;
import com.helloworld.transport.entity.ResultBean;
import com.helloworld.transport.service.PassengerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class description
 *
 * @author LiangHang
 * @createTime 2019/10/26 22:03
 */
@RestController
@RequestMapping("/transport/passenger")
@Api(value = "PassengerController", tags = { "乘客信息处理类" })
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @ApiOperation(value = "savePassenger保存乘客信息", notes = "保存乘客信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "logParam", dataType = "Passenger")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ResultBean.class)
    })
    @PostMapping("/save")
    public ResultBean<String> savePassenger(@RequestBody String logParam) throws JsonProcessingException {
        Passenger passenger = new ObjectMapper().readValue(logParam, Passenger.class);
        passengerService.save(passenger);
        return new ResultBean<>(0000, "SUCCESS", null, "Save success!!", true, null);
    }

    @ApiOperation(value = "queryPassenger查询所有乘客信息", notes = "查询所有乘客信息")
    @GetMapping("/query")
    public ResultBean<List<Passenger>> queryPassenger(){
        List<Passenger> passengerList = passengerService.query();
        List<Map<String, Object>> collect = passengerList.stream().map(this::passengerTransform).collect(Collectors.toList());
        try {
            System.out.println(new JsonMapper().writeValueAsString(collect));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResultBean<List<Passenger>>().setData(passengerList);
    }

    Map<String, Object> passengerTransform(Passenger passenger){
        Map<String, Object> map = new HashMap<>();
        map.put("id", passenger.getId());
        map.put("name", passenger.getName());
        map.put("address", passenger.getAddress());
        return map;
    }

    @ApiOperation(value = "updateAge修改乘客信息", notes = "修改乘客信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ResultBean.class)
    })
    @PostMapping("/updateAgeById/{id}/{age}")
    public ResultBean<String> updateAge(@PathVariable String id, @PathVariable int age) {
        Passenger passenger = new Passenger();
        passengerService.update(passenger);
        return new ResultBean<>(0000, "SUCCESS", null, "Update success, age = " + age, true, null);
    }
}
