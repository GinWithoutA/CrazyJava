package com.ginwithouta.algorithm.class01;

import java.nio.file.FileAlreadyExistsException;
import java.util.Arrays;

/**
 * @Package : com.ginwithouta.class01
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周五
 * @Desc : 找到局部最小值
 */
public class Code07_BSNearAwesome {
    public static int bsNearAwesome(int[] arr) {
        if (arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr[0] < arr[1]) {
            return arr[0];
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr[arr.length - 1];
        }
        int high = arr.length - 1, low = 0, mid, index = -1;
        while (low < high) {
            mid = low + ((high - low) >> 1);
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return arr[mid];
            } else if (arr[mid] > arr[mid - 1]) {
                high = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                low = mid + 1;
            }
        }
        if (low == 0 || low == arr.length - 1) {
            return -1;
        } else if (arr[low] < arr[low - 1] && arr[low] < arr[low + 1]) {
            return arr[low];
        } else {
            return -1;
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
    public static void main(String[] args) {

    }
}
