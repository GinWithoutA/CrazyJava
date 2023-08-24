package com.ginwithouta.algorithm.class03;

/**
 * @Package : com.ginwithouta.algorithm.class03
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周一
 * @Desc : 小和问题
 */
public class Code02_SmallSum {
    public static int smallSum(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return smallSum(arr, left, mid) + smallSum(arr, mid + 1, right) + merge(arr, left, mid, right);
    }
    public static int merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int p1 = left, p2 = mid + 1, sum = 0, index = 0;
        while (p1 <= mid && p2 <= right) {
            if (arr[p1] < arr[p2]) {
                sum += arr[p1] * (right - p2 + 1);
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
        return sum;
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
        int maxLength = 100, range = 100, testTime = 100000;
        int[] arr = {-24, -36, -58, 40, -51, -7, 23, 6};
        printArray(arr);
        System.out.println(smallSum(arr, 0, arr.length - 1));
    }
}
