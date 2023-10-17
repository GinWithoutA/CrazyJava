package com.ginwithouta.algorithm.class23;

/**
 * @Package : com.ginwithouta.algorithm.class23
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周五
 * @Desc : 求数组中第k小的数
 * @method : 方案1：利用快排，荷兰国旗问题，来求解
 *           方案2：快排的非递归版本 （已经是最优了）
 *           方案3：bfprt：在bfprt算法中，每次取的基准值都是以中位数来计算的，这就使得取的基准值尽量靠近数组的中心
 */
public class Code01_FindMinKth {
    public static int getMinKth(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return Integer.MIN_VALUE;
        }
        return process(arr, 0, arr.length - 1, k - 1);
    }
    public static int process(int[] arr, int left, int right, int index) {
        if (left == right) {
            return arr[left];
        }
        int pivot = arr[left + (int) (Math.random() * (right - left + 1))];
        int[] range = partition(arr, left, right, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process(arr, left, range[0] - 1, index);
        } else {
            return process(arr, range[1] + 1, right, index);
        }
    }
    public static int findMinKthMethod2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return Integer.MAX_VALUE;
        }
        int left = 0, right = nums.length - 1, pivot = 0, index = nums.length - k;
        while (left < right) {
            pivot = nums[left + (int) (Math.random() * (right - left + 1))];
            int[] range = partition(nums, left, right, pivot);
            if (index >= range[0] && index <= range[1]) {
                return nums[index];
            } else if (index < range[0]) {
                right = range[0] - 1;
            } else {
                left = range[1] + 1;
            }
        }
        return nums[left];
    }
    public static int[] partition(int[] nums, int left, int right, int pivot) {
        int more = right, less = left - 1, index = left;
        while (index <= more) {
            if (nums[index] < pivot) {
                swap(nums, ++less, index++);
            } else if (nums[index] > pivot) {
                swap(nums, index, more--);
            } else {
                index++;
            }
        }
        return new int[] {less + 1, more};
    }
    public static void swap(int[] nums, int left, int right) {
        if (left == right) {
            return ;
        }
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {4, 2, 1, 3, 5};
        System.out.println(findMinKthMethod2(arr, 2));
    }
}
