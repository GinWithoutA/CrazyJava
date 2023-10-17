package com.ginwithouta.algorithm.class17;

/**
 * @Package : com.ginwithouta.algorithm.class17
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc : 给定一个正数数组arr，请把arr中所有的数分成两个集合，如果arr长度为偶数，两个集合包含数的个数要一样多
 *          如果arr长度为奇数，两个集合包含的数的个数必须只差一个，请尽量让两个集合的累加和接近，返回较小集合的
 *          累加和
 *          （字节原题）
 */
public class Code02_SpiltSumLimit {
    public static int spiltSumLimit(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        sum /= 2;
        if (arr.length % 2 == 0) {
            return process(arr, 0, sum, arr.length / 2);
        } else {
            int p1 =  process(arr, 0, sum, arr.length / 2);
            int p2 = process(arr, 0, sum, arr.length - arr.length / 2);
            return Math.min(p1, p2);
        }
    }

    /**
     *
     * @param arr       数组
     * @param index     当前下标
     * @param rest      剩余的大小
     * @param limit     剩余能取多少个数，必须取到这么多数
     * @return
     */
    public static int process(int[] arr, int index, int rest, int limit) {
        // baseCase
        // 由于多了个数的限制，需要告诉上游当前解是否可用
        if (index == arr.length) {
            return limit == 0 ? 0 : -1;
        }
        int p1 = process(arr, index + 1, rest, limit);
        int next = arr[index] <= rest ? process(arr, index + 1, rest - arr[index], limit - 1) : 0;
        int p2 = next == -1 ? next : next + arr[index];
        return Math.max(p1, p2);
    }
    public static int spiltSumLimitDp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        sum /= 2;
        int[][][] dp = new int[arr.length + 1][(arr.length + 1) / 2 + 1][sum+ 1];
        for (int limit = 1; limit <= (arr.length + 1) / 2; limit++) {
            dp[arr.length][limit][0] = -1;
        }
        for (int index = arr.length - 1; index >= 0; index--) {
            // limit == 0的时候直接返回0
            for (int limit = 1; limit <= (arr.length + 1) / 2; limit++) {
                for (int rest = 0; rest <= sum; rest++) {
                    int p1 =dp[index + 1][limit][rest];
                    int next = arr[index] <= rest ? dp[index + 1][limit - 1][rest - arr[index]] : 0;
                    int p2 = next == -1 ? next : next + arr[index];
                    dp[index][limit][rest] = Math.max(p1, p2);
                }
            }
        }
        if (arr.length % 2 == 0) {
            return dp[0][arr.length / 2][sum];
        } else {
            return Math.min(dp[0][arr.length / 2][sum], dp[0][arr.length - arr.length / 2][sum]);
        }
    }
}
