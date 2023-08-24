package com.ginwithouta.algorithm.class01;

import java.util.Arrays;

/**
 * @Package : com.ginwithouta.class01
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周五
 * @Desc :
 */
public class Code03_InsertionSort {
    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
    public static void insertionSort(int[] arr) {
        if (arr.length == 0 || arr.length == 1) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                for (int j = i; j > 0; j--) {
                    if (arr[j] < arr[j - 1]) {
                        swap(arr, j, j - 1);
                    }
                }
            }
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

    public static void main(String[] args) {
        int testTime = 500000;
        int maxLength = 100;
        int maxValue = 100;
        boolean successed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxLength, maxValue);
            int[] arr2 = copyArray(arr1);
            insertionSort(arr1);
            comparator(arr2);
            for (int j = 0; j < arr1.length; ++j) {
                if (arr1[j] != arr2[j]) {
                    successed = false;
                    break;
                }
            }
            if (!successed) {
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
    }
}
