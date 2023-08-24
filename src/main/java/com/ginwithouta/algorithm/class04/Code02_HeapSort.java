package com.ginwithouta.algorithm.class04;

/**
 * @Package : com.ginwithouta.algorithm.class04
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周三
 * @Desc :  堆排序
 */
public class Code02_HeapSort {
    public static void heapSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }
    public static void heapSort2(int[] arr) {
        for (int i = (arr.length - 1) / 2; i >= 0 ; i--) {
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }
    public static void heapInsert(int[] arr, int index) {
        while (index < arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left] > arr[left + 1] ? left : left + 1;
            largest = arr[index] > arr[largest] ? index: largest;
            if (largest == index) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }
    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
