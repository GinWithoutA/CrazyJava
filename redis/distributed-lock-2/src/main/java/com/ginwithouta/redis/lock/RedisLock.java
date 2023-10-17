package com.ginwithouta.redis.lock;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.beans.Expression;
import java.util.Arrays;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Package : com.ginwithouta.redis.lock
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周五
 * @Desc : 自研的Redis分布式锁
 */
public class RedisLock implements Lock {
    private StringRedisTemplate redisTemplate;
    private String lockName;
    private String uuidValue;
    private long expireTime;

    public RedisLock(StringRedisTemplate redisTemplate, String lockName, String uuid) {
        this.redisTemplate = redisTemplate;
        this.lockName = lockName;
        this.uuidValue = uuid + ":" + Thread.currentThread().getId();
        this.expireTime = 50L;
    }

    @Override
    public void lock() {
        tryLock();
    }

    /**
     * 暂时用不到
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        try { return tryLock(-1L, TimeUnit.SECONDS); } catch (InterruptedException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        if (time == -1L) {
            String script = "if redis.call('exists', KEYS[1]) == 0 or redis.call('HEXISTS', KEYS[1], ARGV[1]) == 1 then redis.call('HINCRBY', KEYS[1], ARGV[1], 1) redis.call('expire', KEYS[1], ARGV[2]) return 1 else return 0 end";
            while (Boolean.FALSE.equals(redisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), Collections.singletonList(lockName), uuidValue, String.valueOf(expireTime)))) {
                try { TimeUnit.MILLISECONDS.sleep(60); } catch (InterruptedException e) { e.printStackTrace(); }
            }
            resetExpire();
            return true;
        }
        return false;
    }

    private void resetExpire() {
        String script = "if redis.call('HEXISTS', KEYS[1], ARGV[1]) == 1 then return redis.call('expire', KEYS[1], ARGV[2]) else return 0 end";
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (Boolean.TRUE.equals(redisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), Collections.singletonList(lockName), uuidValue, String.valueOf(expireTime)))) {
                    resetExpire();
                }
            }
            // 底层是ms，这里先乘1000变成秒，然后每10s检查一次
        }, (this.expireTime * 1000 ) / 3);
    }

    @Override
    public void unlock() {
        String script = "if redis.call('HEXISTS', KEYS[1], ARGV[1]) == 0 then return nil elseif redis.call('hincrby', KEYS[1], ARGV[1], -1) == 0 then return redis.call('del', KEYS[1]) else return 0 end";
        Long flag = redisTemplate.execute(new DefaultRedisScript<>(script, Long.class), Collections.singletonList(lockName), uuidValue);
        if (flag == null) {
            throw new RuntimeException("this lock doesn't exists");
        }
    }

    /**
     * 暂时用不到
     * @return
     */
    @Override
    public Condition newCondition() {
        return null;
    }
}
