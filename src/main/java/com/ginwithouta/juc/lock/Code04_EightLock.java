package com.ginwithouta.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @Package : com.ginwithouta.juc.lock
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周四
 * @Desc : 八锁问题
 *          问题1：什么也不加，就睡1秒，先发短信，再打电话
 *          问题2：在sendMsg中添加睡1秒，还是先发短信，再打电话
 *          问题3：两个线程间不睡眠，增加一个hello方法，第二个线程调用hello，先打印hello，再发送短信
 *          问题4：增加一个phone资源对象，先打电话，再发短信
 *          问题5：增加两个静态方法，进行调用。先发短信，再打电话
 *          问题6：两个对象，两个静态方法，先发短信，再打电话
 *          问题7：一个普通同步方法（打电话），一个静态同步方法（发短信），先发短信，再打电话
 *          问题8：两个资源对象，一个普通同步方法（打电话），一个静态同步方法（发短信），先打电话，再发短信
 */
public class Code04_EightLock {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendSms();
        }, "短信").start();
        // 问题1
        // try {
        //     TimeUnit.SECONDS.sleep(1);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        // new Thread(() -> {
        //     phone.call();
        // }, "电话").start();
        new Thread(() -> {
            phone.hello();
        }, "hello").start();


    }
}
class Phone {
    public synchronized void sendSms() {
        // 问题2
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call() {
        System.out.println("打电话");
    }
    // 问题3
    public void hello() {
        System.out.println("hello");
    }
}
