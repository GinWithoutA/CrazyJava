package com.ginwithouta.algorithm.leetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周一
 * @Desc : 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 */
public class Problem_0046_Permutations {
    public List<List<Integer>> permute(int[] nums) {
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
            // 不交换的时候单独执行
            process(nums, index + 1, ans);
            // 不能直接判断不相等才进行变换，因为这样只能排除和当前index位置相等的值不来这个位置，不能排除有多个其他相同的值都来到index位置上
            HashSet<Integer> set = new HashSet<>();
            set.add(nums[index]);
            for (int i = index + 1; i < nums.length; i++) {
                if (!set.contains(nums[i])) {
                    swap(nums, index, i);
                    process(nums, index + 1, ans);
                    swap(nums, index, i);
                    set.add(nums[i]);
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
