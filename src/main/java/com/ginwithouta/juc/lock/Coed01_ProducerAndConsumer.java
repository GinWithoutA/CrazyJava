package com.ginwithouta.juc.lock;

/**
 * @Package : com.ginwithouta.juc.lock
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周四
 * @Desc :
 */
public class Coed01_ProducerAndConsumer {
    public static void main(String[] args) {
        Product product = new Product();
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
class Product {
    private int number = 0;
    public synchronized void increment() throws InterruptedException {
        // 由于wait会释放锁，下次再进来的时候就不会去判断具体的值是否满足指标
        if (number != 0) {
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + "生产，当前剩余数量：" + (++number));
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        if (number == 0) {
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + "消费，当前剩余数量：" + (--number));
        this.notifyAll();
    }
}
