package com.ginwithouta.algorithm.class01;

import java.util.Arrays;

/**
 * @Package : com.ginwithouta.class01
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周五
 * @Desc : 二分查找
 */
public class Code04_BSExist {
    public static boolean bsExist(int[] arr, int target) {
        if (arr.length == 0 || arr.length == 1) {
            return false;
        }
        int high = arr.length - 1, low = 0, mid;
        while (low < high) {
            mid = low + ((high - low) >> 1);
            if (target == arr[mid]) {
                return true;
            } else if (target > arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (target == arr[low]);
    }
    public static int[] generateRandomArray(int maxLength, int maxValue) {
        // Math.random() 返回[0,1)所有小数
        // Math.random() * N 返回 [0, N) 所有数
        // (int) (Math.random() * N) 返回 [0, N - 1] 所有整数
        int[] arr = new int[(int) ((maxLength + 1) * Math.random())];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        Arrays.sort(arr);
        return arr;
    }
    public static void main(String[] args) {

    }
}
