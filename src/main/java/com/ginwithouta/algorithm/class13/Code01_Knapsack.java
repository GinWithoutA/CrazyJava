package com.ginwithouta.algorithm.class13;

/**
 * @Package : com.ginwithouta.algorithm.class13
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周三
 * @Desc : 背包问题
 */
public class Code01_Knapsack {
    public static int Knapsack(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length <= 0 || bag < 0) {
            return -1;
        }
        return process(w, v, 0, bag);
    }

    /**
     * 背包问题求解过程，暴力递归
     * @param weight    重量数组
     * @param value     价值数组
     * @param index     当前下标
     * @param rest       剩余背包容量
     * @return          返回最大的价值
     */
    public static int process(int[] weight, int[] value, int index, int rest) {
        // 有可能无法装满bag，会一直到最后一个货物
        if (index >= weight.length) {
            return 0;
        }
        // bag == 0的时候是不能停的，因为有可能当前货物的weight是0，但是它有价值
        if (rest < 0) {
            return -1;
        }
        int p1 = process(weight, value, index + 1, rest);
        int p2 = 0, next = process(weight, value, index + 1, rest - weight[index]);
        if (next != -1) {
            p2 = value[index] + next;
        }
        return Math.max(p1, p2);
    }

    /**
     * 背包问题，DP方法
     * @param w
     * @param v
     * @param bag
     * @return
     */
    public static int KnapsackDp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length <= 0 || bag < 0) {
            return -1;
        }
        // 横坐标：rest  [0, bag]
        // 纵坐标：index [0, N]
        int [][] dp = new int[w.length + 1][bag + 1];
        // 最后一行都是0，从倒数第二行开始
        for (int index = w.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0, next = rest - w[index] < 0 ? -1 : rest - w[index];
                if (next != -1) {
                    p2 = v[index] + next;
                }
                dp[index][rest] =  Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }
}
