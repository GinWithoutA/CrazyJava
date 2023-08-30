package com.ginwithouta.algorithm.class05;

import java.util.Arrays;

/**
 * @Package : com.ginwithouta.algorithm.class05
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周四
 * @Desc : 计数排序，之前都是基于比较的排序（冒泡，快排，比较，插入，堆，归并，选择），这种是不基于比较的排序，顶天了就是O(N)
 */
public class Code02_CountingSort{
    public static void countingSort (int[] arr, int range) {
        int[] count = new int[range];
        for (int item : arr) {
            count[item]++;
        }
        int i = 0, j = 0;
        while (i < arr.length) {
            if (count[j] == 0) {
                j++;
                continue;
            }
            for (int index = 0; index < count[j]; index++, i++) {
                arr[i] = j;
            }
            j++;
        }
    }
    public static int[] generateRandomArray(int maxLength, int range) {
        int length = (int) (Math.random() * maxLength) + 1;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = generateRandomNumber(range);
        }
        return arr;
    }
    public static int generateRandomNumber(int range) {
        // [0, range)
        return (int) (Math.random() * range);
    }
    public static int[] copyArray(int[] arr) {
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }
    public static void main(String[] args) {
        int maxLength = 10, range = 100, times = 1000;
        for (int j = 0; j < times; j++) {
            int[] arr = generateRandomArray(maxLength, range), copy = copyArray(arr);
            countingSort(arr, range);
            Arrays.sort(copy);
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != copy[i]) {
                    System.out.println("出错啦！");
                    break;
                }
            }
        }
    }
}
