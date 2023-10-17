package com.ginwithouta.redis.service.impl;

import com.ginwithouta.redis.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Package : com.ginwithouta.redis.service.impl
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc :
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    public static final String ORDER_KEY = "ord:";
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void addOrder() {
        int keyId = ThreadLocalRandom.current().nextInt(1000) + 1;
        String serialNo = UUID.randomUUID().toString();
        String key = ORDER_KEY + keyId;
        String value = "京东订单" + serialNo;
        redisTemplate.opsForValue().set(key, value);
        log.info("****key:{}", key);
        log.info("****value:{}", value);
    }
    @Override
    public String getOrderById(Integer keyId) {
        return (String) redisTemplate.opsForValue().get(ORDER_KEY + keyId);
    }
}
