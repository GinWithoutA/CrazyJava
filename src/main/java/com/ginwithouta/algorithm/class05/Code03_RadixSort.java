package com.ginwithouta.algorithm.class05;

import java.util.Arrays;

/**
 * @Package : com.ginwithouta.algorithm.class05
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周四
 * @Desc : 基数排序，一般针对的是非负的能被十进制表示的数
 */
public class Code03_RadixSort {
    private static final int radix = 10;
    public static void radixSort (int[] arr) {
        if (arr == null || arr.length < 2) {
            return ;
        }
        radixSort(arr, 0, arr.length - 1, maxBits(arr));
    }
    public static int getDigit(int number, int count) {
        int index = 0, result = 0;
        while ((index++) < count) {
            result = number % radix;
            number /= radix;
        }
        return result;
    }
    public static void radixSort(int[] arr, int start, int end, int digit) {
        int i = 0, j = 0;
        // 弄一个辅助数组，数组的长度就是arr的长度
        int[] help = new int[end - start + 1];
        // 进桶的次数
        for (int d = 1; d <= digit; d++) {
            // 准备一个大桶
            int[] count = new int[radix];
            // 获取当前位数上的值，对应位数上的count++
            for (i = start; i <= end; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            // 构建当前的前缀和数组
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            // 从右往左遍历，当前数字对应位数上的值所在的count的位置的值，就是当前元素需要进入的位置
            // 注意，count记录的是当前这一位上的值，小于等于下标值的个数有多少个，如果是a个，那么放在数组中就是[0, a - 1]
            for (i = end; i >= start; i--) {
                j = getDigit(arr[i], d);
                help[(count[j]--) - 1] = arr[i];
            }
            for (i = start; i <= end; i++) {
                arr[i] = help[i];
            }
        }
    }

    /**
     * 返回最大的位数
     * @param arr
     * @return
     */
    public static int maxBits(int[] arr) {
        int maxValue = Integer.MIN_VALUE, bit = 0;
        for (int item : arr) {
            maxValue = Math.max(item, maxValue);
        }
        while (maxValue != 0) {
            bit++;
            maxValue /= radix;
        }
        return bit;
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
            radixSort(arr);
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
