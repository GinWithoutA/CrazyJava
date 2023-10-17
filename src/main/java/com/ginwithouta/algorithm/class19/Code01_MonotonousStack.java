package com.ginwithouta.algorithm.class19;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Package : com.ginwithouta.algorithm.class19
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc : 单调栈，例如返回一个数组中，每个位置左边离他最近的最小的元素，右边理他最近的最小的元素
 */
public class Code01_MonotonousStack {
    /**
     * 用单调栈获取数组中每个位置左边和右边离他最近比他小的元素下标
     * @param arr   数组
     * @return      一个二维数组，数组的行下标对应arr的元素下标，[index][0]是左边离他最小的元素的下标，[index][1]是右边最近离他最小的元素的下标
     */
    public static int[][] getNearLessNoRepeat(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int index = 0; index < arr.length; index++) {
            // 只要当前栈不为空，就判断栈顶元素是否小于当前要入栈的元素，如果不小于，弹出栈顶元素，并生成相应的信息
            // 如果为空，或者满足小于当前要插入元素的值，直接入栈
            while (!stack.isEmpty() && arr[stack.peek()] > arr[index]) {
                int cur = stack.pop();
                res[cur][0] = stack.isEmpty() ? -1 : stack.peek();
                res[cur][1] = index;
            }
            stack.push(index);
        }
        // 如果遍历完数组，栈不为空，弹出栈中所有元素，并生成对应的信息
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            res[cur][0] = stack.isEmpty() ? -1 : stack.peek();
            res[cur][1] = -1;
        }
        return res;
    }

    /**
     * 有重复值的情况
     * @param arr
     * @return
     */
    public static int[][] getNearLess(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Stack<List<Integer>> stack = new Stack<>();
        int[][] res = new int[arr.length][2];
        for (int index = 0; index < arr.length; index++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[index]) {
                List<Integer> top = stack.pop();
                top.forEach(item -> {
                    res[item][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                    res[item][1] = -1;
                });
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[index]) {
                stack.peek().add(index);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(index);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> top = stack.pop();
            top.forEach(item -> {
                res[item][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                res[item][1] = -1;
            });
        }
        return res;
    }
}
