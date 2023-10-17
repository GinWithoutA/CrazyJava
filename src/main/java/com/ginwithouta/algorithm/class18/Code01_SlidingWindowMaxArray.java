package com.ginwithouta.algorithm.class18;

import java.util.LinkedList;

/**
 * @Package : com.ginwithouta.algorithm
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc : 给定一个数组arr，给定一个窗口大小，要求返回每次窗口右滑，当前窗口内的最大值
 */
public class Code01_SlidingWindowMaxArray {
    public static int[] slidingWindowMaxArray(int[] array, int W) {
        if (array == null || array.length == 0 || W <= 0) {
            return null;
        }
        int[] res = new int[array.length - W + 1];
        LinkedList<Integer> doubleQueue = new LinkedList<>();
        for (int right = 0, index = 0; right < array.length; right++) {
            // 如果当前的双端队列不是空，并且最后的元素比当前新进入窗口的值小，就弹出
            while (!doubleQueue.isEmpty() && array[doubleQueue.peekLast()] <= array[right]) {
                doubleQueue.pollLast();
            }
            doubleQueue.push(right);
            // 判断窗口是否移动
            // 双端队列里面存的下标只可能是递增的
            if (doubleQueue.peekFirst() == right - W) {
                doubleQueue.pollFirst();
            }
            // window形成完成，准备输出
            if (right >= W - 1) {
                res[index++] = array[doubleQueue.peekFirst()];
            }
        }
        return res;
    }
}
