package com.ginwithouta.redis.lock;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;

/**
 * @Package : com.ginwithouta.redis.lock
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周五
 * @Desc :
 */
@Component
public class LockFactory {
    @Autowired
    private StringRedisTemplate redisTemplate;
    private String lockName;
    private String uuid;
    public LockFactory() {
        this.uuid = IdUtil.simpleUUID();
    }
    public Lock getDistributedLock(String lockType) {
        if (lockType == null) {
            return null;
        }
        if ("REDIS".equalsIgnoreCase(lockType)) {
            this.lockName = "redisLock";
            return new RedisLock(redisTemplate, lockName, uuid);
        } else if ("ZOOKEEPER".equalsIgnoreCase(lockType)) {
            this.lockName = "zookeeperLock";
            // TODO:
        } else if ("MYSQL".equalsIgnoreCase(lockType)) {
            this.lockName = "mysqlLock";
            // TODO:
        }
        return null;
    }
}
