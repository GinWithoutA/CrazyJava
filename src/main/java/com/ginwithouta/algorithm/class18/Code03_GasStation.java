package com.ginwithouta.algorithm.class18;

import java.util.LinkedList;

/**
 * @Package : com.ginwithouta.algorithm.class18
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc : 加油站的良好出发点问题
 *          给定一个gas数组，数组中的每个数表示当前这个加油站拥有的油的数量，给定一个cost数组，数组中的每个数表示当前
 *          这个加油站到下一个加油站需要消耗的油的数量。假设有一辆卡车，油箱无限大，初始为0，它可以从任意一个加油站出
 *          发，问从哪一个加油站触发可以经过每个加油站
 */
public class Code03_GasStation {
    /**
     * 首先用一个数组表示gas - cost，然后计算所有的前缀和，从最左边开始，每次取gas.length长度的数，这里面的每一个数减去前面的前缀和就是当前从左边开始，循环一次所需要的油量
     * @param gas
     * @param cost
     * @return
     */
    public static int[] gasStation(int[] gas, int cost[]) {
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0) {
            return null;
        }
        // 构建一个help数组，数组的长度是原来的两倍，每个值都是前面的累加和
        int[] help = new int[gas.length * 2], res = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            help[i] = gas[i] - cost[i];
        }
        for (int i = 1; i < help.length; i++) {
            help[i] += help[i - 1];
        }
        // 最小窗口用于记录当前窗口中最薄弱的地方
        LinkedList<Integer> minWindow = new LinkedList<>();
        for (int right = 0; right < help.length; right++) {
            while (!minWindow.isEmpty() && help[minWindow.peekLast()] >= help[right]) {
                minWindow.pollLast();
            }
            minWindow.push(right);
            if (minWindow.peekFirst() == right - gas.length) {
                minWindow.pollFirst();
            }
            if (right - gas.length >= 0 && help[minWindow.peekFirst()] + help[right - gas.length] >= 0) {
                res[right - gas.length + 1] = 1;
            } else if (right - gas.length < 0) {
                res[right - gas.length + 1] = help[minWindow.peekFirst()] < 0 ? 0 : 1;
            }
        }
        return res;
    }

}
