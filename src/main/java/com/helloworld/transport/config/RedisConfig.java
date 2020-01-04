package com.helloworld.transport.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;

/**
 * Class description
 * redis配置类
 *
 * @author LiangHang
 * @createTime 2019/11/7 23:08
 */
@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {

    @Value("${redis.nodes}")
    private String redisNodes;

    @Bean
    public JedisCluster jedisCluster() {
        HashSet<HostAndPort> nodes = new HashSet<>();
        String[] strNodes = redisNodes.split(",");
        for (String strNode : strNodes) {
            String[] split = strNode.split(":");
            String host = split[0];
            int port = Integer.parseInt(split[1]);
            HostAndPort hostAndPort = new HostAndPort(host, port);
            nodes.add(hostAndPort);
        }
        JedisCluster cluster = new JedisCluster(nodes);
        return cluster;
    }
}
