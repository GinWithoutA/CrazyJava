package com.ginwithouta.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package : com.ginwithouta.juc.lock
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周四
 * @Desc :
 */
public class Code02_ProducerAndConsumerJUC {
    public static void main(String[] args) {
        ProductJUC product = new ProductJUC();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    product.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "ThreadProduct1").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    product.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "ThreadProduct2").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    product.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "ThreadConsumer1").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    product.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "ThreadConsumer2").start();
    }
}
class ProductJUC {
    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "生产，当前剩余数量：" + (++number));
            // 不能使用signal，因为存在两个生产者线程，有可能唤醒的是生产者线程，然后就会跳到死循环，出不来
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "消费，当前剩余数量：" + (--number));
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}