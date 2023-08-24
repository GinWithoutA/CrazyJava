package com.ginwithouta.algorithm.class03;

import java.util.Arrays;

/**
 * @Package : com.ginwithouta.algorithm.class03
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周一
 * @Desc : 归并排序（递归实现和非递归实现 ）
 */
public class Code01_MergeSort {
    public static void main(String[] args) {
        int maxLength = 10;
        int range = 100;
        int testTime = 10000;
        int[] arr1 = generateRandomArray(maxLength, range);
        int[] arr2 = copyArray(arr1);
        mergeSort(arr1, 0, arr1.length - 1);
        comparator(arr2);
        for (int j = 0; j < arr1.length; j++) {
            if (arr1[j] != arr2[j]) {
                System.out.println("出错啦");
                break;
            }
        }
    }
    public static void mergeSortExtend(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int step = 1;
        while (step < arr.length) {
            int left = 0;
            while (left < arr.length) {
                int mid = left + step - 1;
                if (mid >= arr.length) {
                    break;
                }
                int right = Math.min(mid + step, arr.length - 1);
                merge(arr, left, mid, right);
                left = right + 1;
            }
            // 如果步长已经大于 N / 2，那么下次一会会超出，为了防止越界，添加这个判断
            if (step > (arr.length / 2)) {
                break;
            }
            step <<= 1;
        }
    }
    public static void mergeSort(int[] arr, int left, int right) {
        if (left == right) {
            return ;            // 表示当前只有一个数
        }
        int mid = left + ((right - left) >> 1);
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
    public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int index = 0, p1 = left, p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            if (arr[p1] <= arr[p2]) {
                temp[index++] = arr[p1++];
            } else {
                temp[index++] = arr[p2++];
            }
        }
        while (p1 <= mid) {
            temp[index++] = arr[p1++];
        }
        while (p2 <= right) {
            temp[index++] = arr[p2++];
        }
        for (index = 0; index < temp.length; index++) {
            arr[left + index] = temp[index];
        }
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
    public static int[] copyArray(int[] arr) {
        return arr.clone();
    }
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println(" ");
    }


}
