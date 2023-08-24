package com.ginwithouta.juc.lock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @Package : com.ginwithouta.juc.lock
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周四
 * @Desc :
 */
public class Code05_ListUnsafe {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
