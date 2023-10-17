package com.ginwithouta.algorithm.class11;

import java.util.HashMap;
import java.util.Stack;

/**
 * @Package : com.ginwithouta.algorithm.class11
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc : 递归的方式实现一个栈的逆序
 */
public class Code04_ReverseStack {
    public static void main(String[] args) {
        // Stack<Integer> stack = new Stack<>();
        // for (int i = 0; i < (int) (Math.random() * 10) + 1; i++) {
        //     stack.push((int) (Math.random() * 100) - (int) (Math.random() * 100));
        // }
        // System.out.println(stack);
        // reverseStack(stack);
        int[] nums = new int[] {2, 7, 11, 5};
        System.out.println(twoSum(nums, 9));
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(target - nums[i])) {
                map.put(target, i);
            } else {
                return new int[] {map.get(target - nums[i]), i};
            }
        }
        return null;
    }
    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int last = removeLast(stack);
        reverseStack(stack);
        stack.push(last);

    }
    public static Integer removeLast(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = removeLast(stack);
            stack.push(result);
            return last;
        }
    }
}
