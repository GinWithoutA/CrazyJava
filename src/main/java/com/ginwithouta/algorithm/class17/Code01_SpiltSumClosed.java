package com.ginwithouta.algorithm.class17;

/**
 * @Package : com.ginwithouta.algorithm.class17
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc : 给定一个数组arr，将其分成两个数组，要求这两个数组的累加和要相差最小，求小的累加和是多少
 */
public class Code01_SpiltSumClosed {
    public static void main(String[] args) {
        int[] arr = new int[] {10, 8, -4, 10};
        System.out.println(spiltSum(arr));
        System.out.println(spiltSumDP(arr));
    }
    /**
     * 暴力递归
     * @param arr
     * @return
     */
    public static int spiltSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return process(arr, 0, sum / 2);
    }

    /**
     * 求最接近数组总和一般的累加和
     * @param arr
     * @param index
     * @param rest
     * @return
     */
    public static int process(int[] arr, int index, int rest) {
        // baseCase
        // 不用考虑rest == 0的情况，因为p1会一直调用不选择当前index的情况，直到index == arr.length
        if (index == arr.length) {
            return 0;
        }
        int p1 = process(arr, index + 1, rest);
        int p2 = rest - Math.abs(arr[index]) >= 0 ? arr[index] + process(arr, index + 1, rest - Math.abs(arr[index])) : 0;
        return Math.max(p1, p2);
    }

    /**
     *
     * @param arr
     * @return
     */
    public static int spiltSumDP(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        int halfSum = sum / 2;
        int[][] dp = new int[arr.length + 1][halfSum + 1];
        // baseCase
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= halfSum; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (Math.abs(arr[index]) <= rest) {
                    dp[index][rest] = Math.max(dp[index][rest], arr[index] + dp[index + 1][rest - Math.abs(arr[index])]);
                }
            }
        }
        return dp[0][halfSum];
    }
}
