package com.ginwithouta.algorithm.leetCode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Package : com.ginwithouta.algorithm.leetCode.class01
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周二
 * @Desc :
 */
public class Problem_0015_ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = nums.length - 1; i > 1; i--) {
            if (i == nums.length - 1 || nums[i] != nums[i + 1]) {
                List<List<Integer>> lists = twoSum(nums, i - 1, -nums[i]);
                for (List<Integer> list : lists) {
                    list.add(nums[i]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
    public List<List<Integer>> twoSum(int[] nums, int end, int target) {
        int left = 0, right = end;
        List<List<Integer>> res = new ArrayList<>();
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                left++;
            } else if (nums[left] + nums[right] > target) {
                right--;
            } else {
                if (left == 0 || nums[left - 1] != nums[left]) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                }
                left++;
            }
        }
        return res;
    }
}
