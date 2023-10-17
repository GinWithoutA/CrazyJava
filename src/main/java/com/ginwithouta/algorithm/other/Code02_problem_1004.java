package com.ginwithouta.algorithm.other;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周五
 * @Desc :
 */
public class Code02_problem_1004 {
    public int longestOnes(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0, right = 0, max = Integer.MIN_VALUE;
        while (right < nums.length) {
            if (nums[right] == 0) {
                k--;
            }
            right++;
            while (k < 0) {
                if (nums[left] == 0) {
                    k++;
                }
                left++;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }
}
