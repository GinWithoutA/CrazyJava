package com.ginwithouta.algorithm.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周一
 * @Desc : 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 */
public class Problem_0047_PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        process(nums, 0, ans);
        return ans;
    }
    public void process(int[] nums, int index, List<List<Integer>> ans) {
        if (index == nums.length) {
            List<Integer> cur = new ArrayList<>();
            for (int num : nums) {
                cur.add(num);
            }
            ans.add(cur);
        } else {
            for (int i = index; i < nums.length; i++) {
                if (i != index && nums[index] != nums[i]) {
                    swap(nums, index, i);
                    process(nums, index + 1, ans);
                    swap(nums, index, i);
                }
            }
        }
    }
    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
