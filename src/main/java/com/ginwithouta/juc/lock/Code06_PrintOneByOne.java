package com.ginwithouta.juc.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Package : com.ginwithouta.juc.lock
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周六
 * @Desc : 两个线程交替输出问题
 */
public class Code06_PrintOneByOne {
    public static final Object o = new Object();
    public static Thread t1 = null, t2 = null;
    public static CountDownLatch latch = new CountDownLatch(1);
    public static ReentrantLock lock = new ReentrantLock();

    /**
     * 最简单的玩法
     * @param chars
     * @param numbers
     */
    public static void method1(char[] chars, int[] numbers) {
        t1 = new Thread(() -> {
            for (char c : chars) {
                System.out.print(c + " ");
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");
        t2 = new Thread(() -> {
            for (int num : numbers) {
                // 如果线程t2在还没有park之前就被t1要求unpark，那么就是会将
                LockSupport.park();
                System.out.print(num + " ");
                LockSupport.unpark(t1);
            }
        }, "t2");
        t1.start();
        t2.start();
    }

    /**
     * 最经典的写法
     * @param chars
     * @param numbers
     */
    public static void method2(char[] chars, int[] numbers) {
        new Thread(() -> {
            synchronized (o) {
                for (char c : chars) {
                    try {
                        System.out.print(c + " ");
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                o.notify();
            }
        }, "t1").start();
        new Thread(() -> {
            synchronized (o) {
                for (int number : numbers) {
                    try {
                        System.out.print(number + " ");
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                o.notify();
            }
        }, "t2").start();
    }

    public static void method3(char[] chars, int[] numbers) {
        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (o) {
                for (char c : chars) {
                    System.out.print(c + " ");
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                o.notify();
            }
        }, "t1").start();
        new Thread(() -> {
            latch.countDown();
            synchronized (o) {
                for (int num : numbers) {
                    System.out.print(num + " ");
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                o.notify();
            }
        }, "t2").start();
    }
    public static void method4(char[] chars, int[] numbers) {
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                for (char c : chars) {
                    System.out.print(c + " ");
                    c2.signal();
                    c1.await();
                }
                c2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();
        new Thread(() -> {
            latch.countDown();
            lock.lock();
            try {
                for (int num : numbers) {
                    System.out.print(num + " ");
                    c1.signal();
                    c2.await();
                }
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }


    public static void main(String[] args) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTU".toCharArray();
        int[] numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21};
        // method1(chars, numbers);
         method2(chars, numbers);
        // method3(chars, numbers);

    }

}
