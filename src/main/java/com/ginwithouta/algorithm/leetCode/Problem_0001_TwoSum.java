package com.ginwithouta.algorithm.leetCode;

import java.util.HashMap;

/**
 * @Package : com.ginwithouta.algorithm.leetCode.class01
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周一
 * @Desc : LeetCode 01 两和问题
 */
public class Problem_0001_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[] {-1, -1};
    }
}
