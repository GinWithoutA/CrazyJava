package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周日
 * @Desc :
 */
public class Problem_0041_FirstMissingPositive {
    public static int firstMissingPositive(int[] nums) {
        int left = 0, right = nums.length;
        while (left < right) {
            // 当前i位置存放的是i + 1
            if (nums[left] == left + 1) {
                left++;
            } else if (nums[left] <= left || nums[left] > right || nums[nums[left] - 1] == nums[left]) {
                // 如果当前i位置存放的数小于等于i，由于我们存放的应该是i + 1，所以不合格，也是不要的
                // 如果当前i位置存放的数大于right，依然是不要的
                // 如果当前i位置存放的数是在数列中的，并且当前的值 - 1存放的就是需要的值，那么当前的值也是不要的
                swap(nums, left, --right);
            } else {
                // nums[left] != nums[nums[left] - 1]
                swap(nums, left, nums[left] - 1);
            }
        }
        return left + 1;
    }
    public static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {3, 4, -1, 1};
        System.out.println(firstMissingPositive(nums));
    }

}
