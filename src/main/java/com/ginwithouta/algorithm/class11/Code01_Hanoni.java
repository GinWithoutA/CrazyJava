package com.ginwithouta.algorithm.class11;

/**
 * @Package : com.ginwithouta.algorithm.class11
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc :
 */
public class Code01_Hanoni {
    public static void main(String[] args) {
        int n = (int) (Math.random() * 100) + 1;
        process(3, "left", "right", "middle");
    }

    public static void process(int n, String from, String to, String help) {
        if (n == 0) {
            // 没有盘子，直接返回
            return;
        }
        process(n - 1, from, help, to);
        System.out.println(n + "从" + from + "移动到" + to);
        process(n - 1, help, to, from);
    }
}
