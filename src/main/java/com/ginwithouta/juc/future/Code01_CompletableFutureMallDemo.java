package com.ginwithouta.juc.future;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Package : com.ginwithouta.juc.future
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周一
 * @Desc : 通过CompletableFuture进行电商网站的比价真实案例
 *
 */
public class Code01_CompletableFutureMallDemo {
    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("taoBao"),
            new NetMall("pdd"),
            new NetMall("dnagdang"),
            new NetMall("amazon")
    );

    /**
     * 一家一家搜
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPrice(List<NetMall> list, String productName) {
        return list.stream()
                .map(netMall -> String.format(productName + " in %s's price is %.2f", netMall.getNetMallName(), netMall.calculatePrice(productName)))
                .collect(Collectors.toList());
    }

    /**
     * 利用CompletableFuture类进行查找
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName) {
        return list.stream()
                .map(netMall -> CompletableFuture.supplyAsync(() -> String.format(productName + " in %s's price is %.2f", netMall.getNetMallName(), netMall.calculatePrice(productName))))
                .toList()
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> list1 = getPrice(list, "mysql");
        for (String element: list1) {
            System.out.println(element);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("----costTime: " + (endTime - startTime) + "ms");
        long startTime2 = System.currentTimeMillis();
        List<String> list2 = getPriceByCompletableFuture(list, "mysql");
        for (String element: list2) {
            System.out.println(element);
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("----costTime: " + (endTime2 - startTime2) + "ms");

    }
}
@Data
@AllArgsConstructor
class NetMall {
    private String netMallName;
    public double calculatePrice(String productName) {
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}
