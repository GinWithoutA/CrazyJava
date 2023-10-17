package com.ginwithouta.redis.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ginwithouta.redis.service.InventoryServcie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package : com.ginwithouta.redis.service.impl
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc :
 */
@Service
@Slf4j
public class InventoryServiceImpl implements InventoryServcie {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Value("${server.port}")
    private String port;
    private Lock lock = new ReentrantLock();
    @Override
    public String sale() {
        String retMessage = null;
        // Version 1.0
        /* lock.lock();
        try {
            String result = redisTemplate.opsForValue().get("inventory001");
            Integer inventory = result == null ? 0 : Integer.parseInt(result);
            if (inventory > 0) {
                redisTemplate.opsForValue().set("inventory001", String.valueOf(--inventory));
                retMessage = "成功卖出一个商品，库存剩余：" + inventory;
            } else {
                retMessage = "商品以售罄";
            }
            log.info(retMessage + "\t" + "服务端口号：{}", port);
        } finally {
            lock.unlock();
        } */

        // Version 2.1
        String key = "redisLock";
        String uuidValue = IdUtil.simpleUUID() + ":" + Thread.currentThread().getId();
        while (Boolean.FALSE.equals(redisTemplate.opsForValue().setIfAbsent(key, uuidValue))) {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
        try {
            String result = redisTemplate.opsForValue().get("inventory001");
            Integer inventory = result == null ? 0 : Integer.parseInt(result);
            if (inventory > 0) {
                redisTemplate.opsForValue().set("inventory001", String.valueOf(--inventory));
                retMessage = "成功卖出一个商品，库存剩余：" + inventory;
            } else {
                retMessage = "商品以售罄";
            }
            log.info(retMessage + "\t" + "服务端口号：{}", port);
        } finally {
            redisTemplate.delete(key);
        }
        return retMessage + "\t" + "服务端口号：" + port;
    }
}
