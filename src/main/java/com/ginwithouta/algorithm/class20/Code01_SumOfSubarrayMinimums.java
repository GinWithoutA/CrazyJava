package com.ginwithouta.algorithm.class20;

/**
 * @Package : com.ginwithouta.algorithm.class20
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周二
 * @Desc : 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组，即子数组的最小值之和
 * @method : 基本的思路就是以每个位置的元素为最小值，看能有多少个子数组，然后累加就是答案
 */
public class Code01_SumOfSubarrayMinimums {
    /**
     * 子数组的最小值之和
     * @param arr
     * @return
     */
    public static int sumOfSubarrayMinimums(int[] arr) {
        int mod = 1000000007;
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] stack = new int[arr.length];
        long sum = 0;
        int top = -1;
        for (int index = 0; index < arr.length; index++) {
            while (top != -1 && arr[stack[top]] >= arr[index]) {
                int cur = stack[top--];
                sum += ((long) (top == -1 ? cur + 1 : cur - stack[top]) * (index - cur)) * (long) arr[cur];
                sum %= mod;
            }
            stack[++top] = index;
        }
        while (top != -1) {
            int cur = stack[top--];
            sum += (long) (top == -1 ? cur + 1 : cur - stack[top]) * (arr.length - cur) * (long) arr[cur];
            sum %= mod;
        }
        return (int) sum;
    }
}
