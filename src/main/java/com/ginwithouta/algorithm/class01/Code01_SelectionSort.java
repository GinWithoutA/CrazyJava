package com.ginwithouta.algorithm.class01;

import java.util.Arrays;

/**
 * @Package : com.ginwithouta.class01
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周五
 * @Desc :
 */
public class Code01_SelectionSort {
    public static void swap(int[] arr, int left, int right) {
        /*
         * 异或运算满足交换律以及分配律
         */
        arr[left] = arr[left] ^ arr[right];
        arr[right] = arr[left] ^ arr[right];
        arr[left] = arr[left] ^ arr[right];
    }
    public static void selectionSort(int[] arr) {
        int minIndex;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                swap(arr, i, minIndex);
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
            System.out.println(i + " ");
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
            selectionSort(arr1);
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
