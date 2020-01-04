package com.helloworld.transport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helloworld.transport.entity.Passenger;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class description
 * Redis测试
 *
 * @author LiangHang
 * @createTime 2019/11/6 23:22
 */
public class TestRedisClient {

    final String KEY = "PASSENGER_INFO";

    @Test
    public void testJedis(){
        Jedis jedis = new Jedis("192.168.147.128", 6379);
        
        jedis.set("Hello", "Jedis");
        System.out.println("通过Jedis获取Redis中的数据：" + jedis.get("Hello"));

        jedis.lpush("list", "1", "2", "3", "4");
        List<String> list = jedis.lrange("list", 0, 3);
        for (String s : list) {
            System.out.print(s + "\t");
        }
    }

    @Test
    public void testJedisWriteObject2Json() {
        Jedis jedis = new Jedis("192.168.147.128", 6380);
        Map<String, String> master = jedis.hgetAll("master");
        System.out.println(master);
        Passenger passenger = new Passenger();
        passenger.setName("梁航").setAge(27).setAddress("广东省廉江市吉水镇").setSex(1)
                .setIdCardNum("440881199202101710").setRemarks("好好工作，早日成家立业")
                .setCreateTime(new Date()).setUpdateTime(new Date());
        ObjectMapper objectMapper = new ObjectMapper();
        String passengerInfo = null;
        try {
            passengerInfo = objectMapper.writeValueAsString(passenger);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String passenger_info = jedis.setex(KEY, 3600 * 72, passengerInfo);
        System.out.println("Passenger: " + passenger_info);
    }

    @Test
    public void testJedisReadJson2Object() {
        Jedis jedis = new Jedis("192.168.147.128", 6379);
        ObjectMapper objectMapper = new ObjectMapper();
        Passenger passenger = null;
        try {
            passenger = objectMapper.readValue(jedis.get(KEY), Passenger.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(passenger);
    }

    @Test
    public void testShards() {
        ArrayList<JedisShardInfo> shards = new ArrayList<>();
        JedisShardInfo info1 = new JedisShardInfo("192.168.147.128", 6379);
        JedisShardInfo info2 = new JedisShardInfo("192.168.147.128", 6380);
        JedisShardInfo info3 = new JedisShardInfo("192.168.147.128", 6381);
        shards.add(info1);
        shards.add(info2);
        shards.add(info3);
        ShardedJedis shardedJedis = new ShardedJedis(shards);
//        String setex = shardedJedis.setex("name", 300, "LiangHang");
//        System.out.println(setex);
        System.out.println(shardedJedis.get("name"));
    }

    @Test
    public void testSentinel() {
        Set<String> sentinels = new HashSet<>();
        sentinels.add("192.168.147.128:26379");
        JedisSentinelPool sentinelPool
                = new JedisSentinelPool("mymaster", sentinels);
        Jedis jedis = sentinelPool.getResource();
        System.out.println(jedis.set("master", "LiangHang"));
        jedis.close();
    }

    @Test
    public void testRedisCluster() {
        Set<HostAndPort> sets = new HashSet<>();
        sets.add(new HostAndPort("192.168.147.128", 7000));
        sets.add(new HostAndPort("192.168.147.128", 7001));
        sets.add(new HostAndPort("192.168.147.128", 7002));
        sets.add(new HostAndPort("192.168.147.128", 7003));
        sets.add(new HostAndPort("192.168.147.128", 7004));
        sets.add(new HostAndPort("192.168.147.128", 7005));
        JedisCluster jedisCluster = new JedisCluster(sets);
//        System.out.println(jedisCluster.setnx("name", "LiangHang专有集群"));
        System.out.println(jedisCluster.get("name"));
    }
}
