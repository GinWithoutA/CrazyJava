package com.ginwithouta.algorithm.class16;

/**
 * @Package : com.ginwithouta.algorithm.class16
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周六
 * @Desc : arr是面值数组，其中的值都是正数且没有重复，在给定一个正数aim。每个值都是一种面值，且认为
 *          张数无限，返回组成aim的最少货币数
 */
public class Code02_MinCoinsNoLimits {
    public static int minValue(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    /**
     * 暴力递归
     * @param arr    货币数组
     * @param index  当前下标
     * @param rest   剩余的钱
     * @return
     */
    public static int process(int[] arr, int index, int rest) {
        // baseCase
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            int next = process(arr, index + 1, rest - zhang * arr[index]);
            ans = Math.min(ans, zhang + next);
        }
        return ans;
    }

    /**
     * 有枚举行为的动态规划
     * @param arr   数组
     * @param aim   目标
     * @return
     */
    public static int minValueDP(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 0;
        for (int rest = 1; rest <= aim; rest++) {
            dp[arr.length][rest] = Integer.MAX_VALUE;
        }
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    int next = process(arr, index + 1, rest - zhang * arr[index]);
                    ans = Math.min(next, zhang + next);
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][aim];
    }

    /**
     * 斜率优化的DP
     * @param arr   数组
     * @param aim   目标
     * @return
     */
    public static int minValueDPBetter(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 0;
        for (int rest = 1; rest <= aim; rest++) {
            dp[arr.length][rest] = Integer.MAX_VALUE;
        }
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0 && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]]);
                }
            }
        }
        return dp[0][aim];
    }
}
