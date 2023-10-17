package com.ginwithouta.algorithm.class18;

import java.util.LinkedList;

/**
 * @Package : com.ginwithouta.algorithm.class17
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc : 给定一个数组arr，正数num。某个arr中的子数组sub如果想达标，必须满足sub中最大-sub中最小小于等于num，返回arr中达标子数组的数量
 */
public class Code02_AllLessSumSubArray {
    /**
     *
     * @param arr   数组
     * @param sum   达标值，必须小于等于sum才算达标
     * @return
     */
    public static int allLessSumSubArr(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum <= 0) {
            return 0;
        }
        // 用两个结构，最小窗口和最大窗口来计算当前子数组的最大值减最小值是否满足小于等于sum
        LinkedList<Integer> minWindow = new LinkedList<>();
        LinkedList<Integer> maxWindow = new LinkedList<>();
        int res = 0;
        for (int left = 0, right = 0; left < arr.length; left++) {
            // 窗口右边界不断扩张
            while (right < arr.length) {
                // 构建最小最大窗口
                while (!minWindow.isEmpty() && arr[minWindow.getLast()] >= arr[right]) {
                    minWindow.pollLast();
                }
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[right]) {
                    maxWindow.pollLast();
                }
                minWindow.push(right);
                maxWindow.push(right);
                if (maxWindow.peekFirst() - minWindow.peekFirst() <= sum) {
                    right++;
                    break;
                } else {
                    res += right - left;
                }
            }
            // 左边界扩张前判断是否要过期
            if (minWindow.peekFirst() == left) {
                minWindow.pollFirst();
            }
            if (maxWindow.peekFirst() == left) {
                maxWindow.pollFirst();
            }
        }
        return res;
    }
}
