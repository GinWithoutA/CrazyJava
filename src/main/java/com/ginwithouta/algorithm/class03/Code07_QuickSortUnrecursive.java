package com.ginwithouta.algorithm.class03;

import java.util.Stack;

/**
 * @Package : com.ginwithouta.algorithm.class03
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周二
 * @Desc : 快速排序的非递归版本，直接使用3.0,需要使用一个额外数组
 */
public class Code07_QuickSortUnrecursive {
    public static class Op {
        public int left;
        public int right;
        public Op(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
    public static int[] generateRandomArray(int maxLength, int range) {
        int[] arr = new int[(int) (Math.random() * maxLength) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = generateRandomNumber(range);
        }
        return arr;
    }
    public static int generateRandomNumber (int range) {
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }
    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
    public static int[] netherlandsFlagPartition(int[] arr, int left, int right) {
        if (left > right) {
            return new int[] {-1, -1};
        }
        if (left == right) {
            return new int[] {left, right};
        }
        int less = left - 1, more = right, index = left;
        while (index < more) {
            if (arr[index] < arr[right]) {
                swap(arr, index++, ++less);
            } else if (arr[index] > arr[right]) {
                swap(arr, index, --more);
            } else {
                index++;
            }
        }
        swap(arr, more, right);
        return new int[] {less + 1, more};
    }
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return ;
        }
        swap(arr, (int) (Math.random() * arr.length), arr.length - 1);
        int[] area = netherlandsFlagPartition(arr, 0, arr.length - 1);
        int lessArea = area[0], moreArea = area[1];
        // 构建一个栈，用于存放初始的左右两个子区域
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(0, lessArea - 1));
        stack.push(new Op(moreArea + 1, arr.length - 1));
        // 只要栈里面还有元素，就说明还有子区域没有排序完成
        while (!stack.isEmpty()) {
            Op top = stack.pop();
            // 原本我们是判断是否 >=，如果是则不用做任何事情，这里判断如果小于才做事情
            if (top.left < top.right) {
                swap(arr, (int) (Math.random() * (top.right - top.left + 1)), top.right);
                area = netherlandsFlagPartition(arr, top.left, top.right);
                lessArea = area[0];
                moreArea = area[1];
                stack.push(new Op(top.left, lessArea - 1));
                stack.push(new Op(moreArea + 1, top.right));
            }
        }
    }
}
