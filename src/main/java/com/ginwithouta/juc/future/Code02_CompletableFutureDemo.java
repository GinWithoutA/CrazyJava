package com.ginwithouta.juc.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

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
    public static void testPool() {
        long start = System.currentTimeMillis();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            try { TimeUnit.MILLISECONDS.sleep(800); } catch (InterruptedException e) { e.printStackTrace(); }
            return "task1 over";
        });
        threadPool.submit(futureTask1);
        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            try { TimeUnit.MILLISECONDS.sleep(800); } catch (InterruptedException e) { e.printStackTrace(); }
            return "task2 over";
        });
        threadPool.submit(futureTask2);
        try { TimeUnit.MILLISECONDS.sleep(800); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(System.currentTimeMillis() - start + "ms");
        threadPool.shutdown();
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testPool();
        // System.out.println("主线程不阻塞");
    }
}
