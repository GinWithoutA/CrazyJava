package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周日
 * @Desc : 接雨水问题，同样，考虑每一个位置能接多少水，加起来就是答案
 */
public class Problem_0042_TrappingRainWater {
    public int trap(int[] height) {
        int[] rightMax = new int[height.length];
        int leftMax = Integer.MIN_VALUE, max = Integer.MIN_VALUE, ans = 0;
        for (int i = height.length - 2; i > 0; i--) {
            max = Math.max(max, height[i + 1]);
            rightMax[i] = max;
        }
        for (int i = 1; i < height.length - 1; i++) {
            leftMax = Math.max(leftMax, height[i - 1]);
            ans += Math.max(0, Math.min(leftMax, rightMax[i]) - height[i]);
        }
        return ans;
    }

    /**
     * 双指针版本，每次结算真实的最大的一边的值中最小的那个，因为小的才是最终决定边界的值
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int left = 1, right = height.length - 2, leftMax = height[0], rightMax = height[height.length - 1];
        int ans = 0;
        while (left <= right) {
            if (leftMax <= rightMax) {
                ans += Math.max(0, leftMax - height[left]);
                leftMax = Math.max(leftMax, height[left++]);
            } else {
                ans += Math.max(0, rightMax - height[right]);
                rightMax = Math.max(rightMax, height[right--]);
            }
        }
        return ans;
    }
}
