package com.helloworld.transport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helloworld.transport.entity.Passenger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class description
 * 采用comparator对List进行排序的测试
 *
 * @author LiangHang
 * @createTime 2019/11/17 22:31
 */
public class SortListTest {
    public static void main(String[] args) throws JsonProcessingException {
        List<Passenger> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Passenger().setAge(new Random().nextInt(100)));
        }
        System.out.println();
        long start = System.currentTimeMillis();
        Collections.sort(list,
                (Passenger o1, Passenger o2) -> o2.getAge() - o1.getAge());
        long total = System.currentTimeMillis() - start;
        System.out.println(total);
        for (Passenger passenger : list) {
            System.out.println(new ObjectMapper().writeValueAsString(passenger));
        }
    }
}
