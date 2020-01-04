package com.helloworld.transport.aop;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据访问先读取缓存
 *
 * @author LiangHang
 * @createTime 2019/11/10 1:22
 */
@Aspect
public class RepositoryToRedisAop {
    private final static Logger LOGGER = LoggerFactory.getLogger(RepositoryToRedisAop.class);
    /**
     *
     */
    @Around(value = "execution(* com.helloworld.transport.repository.*(*))")
    void doMethod() {
        LOGGER.info("——————————开始查询缓存——————————");
    }
}
