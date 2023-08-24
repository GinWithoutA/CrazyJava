package com.ginwithouta.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Package : com.ginwithouta.juc.future
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周一
 * @Desc :
 */
public class Code02_CompletableFutureDemo {
    public static void test() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });
        System.out.println(completableFuture.get());

    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test();
        System.out.println("主线程不阻塞");
    }
}
