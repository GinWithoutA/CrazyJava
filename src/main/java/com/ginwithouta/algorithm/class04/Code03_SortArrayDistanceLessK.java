package com.ginwithouta.algorithm.class04;

/**
 * @Package : com.ginwithouta.algorithm.class04
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周三
 * @Desc : 有一个数组，当前数组中的所有元素都是无序的，但是他们有一个规律，就是排好序以后数组中所有数的位置和他们
 *          原来的位置相差的距离不会超过k，设计一个比较好的算法来排序
 *
 */
public class Code03_SortArrayDistanceLessK {
    public static void moveLessThanK(int[] arr, int k) {
        int left = 0, right = k - 1;
        while (right < arr.length) {
            heapSort(arr, left++, right++, k);
        }
    }
    public static void heapSort(int[] arr, int left, int right, int k) {
        for (int i = right; i >= left; i--) {
            heapify(arr, i, k);
        }
    }
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int smallest = left + 1 < heapSize && arr[left] < arr[left + 1] ? left : left + 1;
            smallest = arr[index] < arr[smallest] ? index : smallest;
            if (smallest == index) {
                break;
            }
            swap(arr, index, smallest);
            index = smallest;
            left = (index - 1) / 2;
        }
    }
    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
