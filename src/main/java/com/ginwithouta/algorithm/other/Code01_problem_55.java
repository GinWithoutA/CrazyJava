package com.ginwithouta.algorithm.other;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周四
 * @Desc :
 */
public class Code01_problem_55 {
    public static boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        return process(nums, nums.length - 1);
    }
    public static boolean process(int[] nums, int index) {
        if (index >= nums.length - 1) {
            return true;
        }
        boolean result = false;
        for (int jump = 1; jump <= nums[index]; jump++) {
            result = process(nums, index + jump);
            if (result) {
                break;
            }
        }
        return result;
    }
    public static boolean dp(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = Math.max(dp[i - 1], nums[i] + i);
            }
            if (i != nums.length - 1 && dp[i] + i >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] nums = new int[] {3, 2, 1, 0, 4};
        System.out.println(canJump(nums));
    }
}
