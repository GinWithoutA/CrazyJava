package com.ginwithouta.algorithm.class19;

import java.util.Stack;

/**
 * @Package : com.ginwithouta.algorithm.class19
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc : 给定一个数组，数组中的元素是直方图的高度，求直方图的最大面积 （LeetCode原题）
 */
public class Code03_LargestRectangleHistogram {
    /**
     * 利用单调栈，直接求以当前值为高度能延伸出多长的面积
     * @param arr
     * @return
     */
    public static int largestRectangleHistogram(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] stack = new int[arr.length];
        int res = Integer.MIN_VALUE, top = -1;
        for (int index = 0; index < arr.length; index++) {
            while (top != -1 && arr[stack[top]] >= arr[index]) {
                int cur = stack[top--];
                res = Math.max(res, (top == -1 ? index : index - stack[top] - 1) * arr[cur]);
            }
            stack[++top] = index;
        }
        while (top != -1) {
            int cur = stack[top--];
            res = Math.max(res, (top == -1 ? arr.length : arr.length - stack[top] - 1) * arr[cur]);
        }
        return res;
    }
}
