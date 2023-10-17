package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周五
 * @Desc :
 */
public class Problem_0034_FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int left = this.binarySearch(nums, target, true), right = this.binarySearch(nums, target, false) - 1;
        if (left <= right && right < nums.length && nums[left] == target) {
            return new int[] {left, right};
        }
        return new int[] {-1, -1};
    }
    public int binarySearch(int[] nums, int target, boolean flag) {
        int left = 0, right = nums.length - 1, mid, ans = nums.length;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] > target || (flag && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}
