package com.ginwithouta.redis.service.impl;

import ch.qos.logback.core.recovery.ResilientFileOutputStream;
import cn.hutool.core.util.IdUtil;
import com.ginwithouta.redis.lock.LockFactory;
import com.ginwithouta.redis.lock.RedisLock;
import com.ginwithouta.redis.service.InventoryServcie;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
    @Autowired
    private LockFactory lockFactory;
    @Autowired
    private Redisson redisson;
    @Override
    public String sale() {
        String retMessage = null;
        Lock lock = lockFactory.getDistributedLock("REDIS");
        lock.lock();
        try {
            String result = redisTemplate.opsForValue().get("inventory001");
            int inventory = result == null ? 0 : Integer.parseInt(result);
            if (inventory > 0) {
                redisTemplate.opsForValue().set("inventory001", String.valueOf(--inventory));
                retMessage = "成功卖出一个商品，库存剩余：" + inventory;
            } else {
                retMessage = "商品以售罄";
            }
            log.info(retMessage + "\t" + "服务端口号：{}", port);
            try { TimeUnit.SECONDS.sleep(120); } catch (InterruptedException e) {e.printStackTrace();}
            // test();
        } finally {
            lock.unlock();
        }
        return retMessage + "\t" + "服务端口号：" + port;
    }
    @Override
    public String saleByRedisson() {
        String retMessage = null;
        RLock redisLock = redisson.getLock("redisLock");
        redisLock.lock();
        try {
            String result = redisTemplate.opsForValue().get("inventory001");
            int inventory = result == null ? 0 : Integer.parseInt(result);
            if (inventory > 0) {
                redisTemplate.opsForValue().set("inventory001", String.valueOf(--inventory));
                retMessage = "成功卖出一个商品，库存剩余：" + inventory;
            } else {
                retMessage = "商品以售罄";
            }
            log.info(retMessage + "\t" + "服务端口号：{}", port);
            // test();
        } finally {
            if (redisLock.isLocked() && redisLock.isHeldByCurrentThread()) {
                redisLock.unlock();
            }
        }
        return retMessage + "\t" + "服务端口号：" + port;
    }
    private void test() {
        Lock lock = lockFactory.getDistributedLock("REDIS");
        lock.lock();
        try {
            log.info("可重入锁测试成功");
        } finally {
            lock.unlock();
        }
    }
}
