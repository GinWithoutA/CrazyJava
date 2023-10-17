package com.ginwithouta.algorithm.leetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周一
 * @Desc : 这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
 *
 * 请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。
 */
public class Problem_1306_JumpGameIII {
    public boolean canReach(int[] arr, int start) {
        boolean[] flag = new boolean[arr.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int cur;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            flag[cur] = true;
            if (cur - arr[cur] >= 0 && !flag[cur - arr[cur]]) {
                queue.add(cur - arr[cur]);
            }
            if (cur + arr[cur] < arr.length && !flag[cur + arr[cur]]) {
                queue.add(cur + arr[cur]);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0 && !flag[i]) {
                return false;
            }
        }
        return true;
    }
}
