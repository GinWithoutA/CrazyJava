package com.ginwithouta.algorithm.class04;

/**
 * @Package : com.ginwithouta.algorithm.class04
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周三
 * @Desc :
 */
public class Code01_Heap {
    private void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
    /**
     * 插入数据时大根堆的调整过程
     * @param arr   array数组
     * @param index 当前位置
     */
    private void heapInsert(int[] arr, int index) {
        // 包含两个终止条件，当index变为0的时候，也会退出
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 大根堆的下沉调整
     * @param arr       arr
     * @param index     index
     * @param heapSize  heapSize
     */
    private void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left] > arr[left + 1] ? left : left + 1;
            largest = arr[index] < arr[largest] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }
}
