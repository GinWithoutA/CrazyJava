package com.ginwithouta.algorithm.class03;

/**
 * @Package : com.ginwithouta.algorithm.class03
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周一
 * @Desc : 【HARD】对于数组中的任意一个数，找到它右边有多少个数，使得他们的两倍比这个数本身还小，
 */
public class Code04_BiggerThanRightTwice {
    public static int mergeSort(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return mergeSort(arr, left, mid) + mergeSort(arr, mid + 1, right) + merge(arr, left, mid, right);
    }
    public static int merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int p1 = left, p2 = mid + 1, index = 0, count = 0, pointer = mid + 1;
        // 简单的说，由于左右两边都是有序的，和归并一样用两个指针判断。首先走右边的，如果当前的数的两倍比左边的数还小
        // 说明满足题目条件，右边的指针往后移动。当出现了当前右边的数不满足题目的条件的时候，则while循环会退出，此时
        // 由于已经有序，说明对于左边的数，当前在右边有pointer - mid - 1 (因为右边的数是从mid + 1开始的，排除掉pointer
        // 所指的那个数，相当于pointer - (m + 1))这么多的数都满足题目条件。
        for (int i = left; i <= mid; i++) {
            while (pointer <= right && arr[i] > (arr[pointer] * 2)) {
                pointer++;
            }
            count += pointer - mid - 1;
        }
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
