package com.ginwithouta.algorithm.class15;

/**
 * @Package : com.ginwithouta.algorithm.class15
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周五
 * @Desc : 给定一个货币数组，每个值代表这个货币的价值，在给定一个值aim，求用数组中的货币，有多少种方法可以得到aim（每个货币不能重复，值相同的货币可以认为是不同的货币）
 */
public class Code02_CoinsWayEveryPaperDifferent {
    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }
    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        return process(arr, index + 1, rest) + process(arr, index + 1, rest - arr[index]);
    }

    /**
     * DP方法
     * @param arr
     * @param aim
     * @return
     */
    public static int coinsWayDP(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        // baseCase
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest] + rest - arr[index] < 0 ? 0 : dp[index + 1][rest - arr[index]];
            }
        }
        return dp[0][aim];
    }

}
