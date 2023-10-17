package com.ginwithouta.algorithm.class19;

import java.util.Stack;

/**
 * @Package : com.ginwithouta.algorithm.class19
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc : 给定一个数组arr，求该数组中所有的子数组sub，每个sub都有一个标准值sub的累加和乘以sub中的最小值，求这个标准值最大的时候是多少
 * @method : 计算以每一个位置的元素作为它自己的子数组的最小值的情况，看最大能扩充到哪里，求出当前的标准值，以此类推求出每个位置的标准值，然后返回最大的那一个
 */
public class Code02_AllTimesMinToMax {
    /**
     *
     * @param arr
     * @return
     */
    public static int allTimesMinToMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        // 计算一个累加和数组，优化求子数组的累加和的过程
        int[] preSum = new int[arr.length];
        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }
        // 以每个位置的值为sub的最小值，利用单调栈求左边右边离他最近的最小值在哪里，然后计算累加和
        Stack<Integer> stack = new Stack<>();
        for (int index = 0; index < arr.length; index++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[index]) {
                int cur = stack.pop();
                res = Math.max(res, (stack.isEmpty() ? preSum[index - 1] : preSum[index - 1] - preSum[stack.peek()])) * arr[cur];
            }
            stack.push(index);
        }
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            res = Math.max(res, (stack.isEmpty() ? preSum[arr.length - 1] : preSum[arr.length - 1] - preSum[stack.peek()]) * arr[cur]);
        }
        return res;
    }
}
