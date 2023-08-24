package com.ginwithouta.algorithm.class03;

import java.util.ArrayList;

/**
 * @Package : com.ginwithouta.algorithm.class03
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周一
 * @Desc : 求数组中的逆序对，即求数组中所有左边数大于右边数的小集合的个数
 */
public class Code03_ReversePair {
    public static int mergeSort(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return mergeSort(arr, left, mid) + mergeSort(arr, mid + 1, right) + merge(arr, left, mid, right);
    }
    public static int merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int p1 = mid, p2 = right, index = temp.length - 1, count = 0;
        while (p1 >= left && p2 > mid) {
            if (arr[p2] >= arr[p1]) {
                temp[index--] = arr[p2--];
            } else {
                count += p2 - mid;
                temp[index--] = arr[p1--];
            }
        }
        while (p1 >= left) {
            temp[index--] = arr[p1--];
        }
        while (p2 > mid) {
            temp[index--] = arr[p2--];
        }
        for (index = 0; index < temp.length; index++) {
            arr[left + index] = temp[index];
        }
        return count;
    }
    public static int[] generateRandomArray(int maxLength, int maxValue) {
        // Math.random() 返回[0,1)所有小数
        // Math.random() * N 返回 [0, N) 所有数
        // (int) (Math.random() * N) 返回 [0, N - 1] 所有整数
        int[] arr = new int[(int) ((maxLength + 1) * Math.random())];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println(" ");
    }

    public static void main(String[] args) {
        int maxLength = 10, range = 100, testTime = 100000;
        int[] arr = generateRandomArray(maxLength, range);
        printArray(arr);
        int count = mergeSort(arr, 0, arr.length - 1);
        System.out.println(count);
    }
}
