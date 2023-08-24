package com.ginwithouta.algorithm.class03;

import org.w3c.dom.ranges.Range;

import java.util.Arrays;
import java.util.Random;

/**
 * @Package : com.ginwithouta.algorithm.class03
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周二
 * @Desc : 快速排序
 * @method : 先定义一个初始位置，使其左边所有的数都小于等于它，然后右边的所有数都大于它，往下递归，对左右两个部分再做快排
 */
public class Code06_QuickSort {
    public static int[] generateRandomArray(int maxLength, int range) {
        // Math.random() 返回[0,1)所有小数
        // Math.random() * N 返回 [0, N) 所有数
        // (int) (Math.random() * N) 返回 [0, N - 1] 所有整数
        int[] arr = new int[(int) ((maxLength + 1) * Math.random())];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = generateRandomNumber(range);
        }
        return arr;
    }
    public static int generateRandomNumber(int range) {
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }
    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
    public static void main(String[] args) {
        int maxLength = 10, range = 100;
        int[] arr = generateRandomArray(maxLength, range);
        System.out.println(Arrays.toString(arr));
        quickSort1(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 1.0版本的快排，每次之搞定一个数
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort1(int[] arr, int left, int right) {
        if (arr == null || arr.length == 0) {
            return ;
        }
        sort1(arr, left, right);
    }
    public static void sort1(int[] arr, int left, int right) {
        // 边界条件，由于传的是 mid + 1 或者 mid - 1，有可能出现left > right的情况，这种情况肯定是不对的，直接返回
        // 出现等于的情况就是只有一个数，根本不需要排序
        if (left >= right) {
            return ;
        }
        int mid = partition(arr, left, right);
        sort1(arr, left, mid - 1);
        sort1(arr, mid + 1, right);
    }
    /**
     * 每次都会返回一个数，这个数的左边都小于等于它，右边都大于它
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int partition(int[] arr, int left, int right) {
        if (left > right) {
            return -1;
        }
        int le = left - 1, index = left;
        while (index < right) {
            if (arr[index] <= arr[right]) {
                swap(arr, index, ++le);
            }
            index++;
        }
        swap(arr, ++le, right);
        return le;
    }

    /**
     * 2.0版本的快排，每次搞定一批数，这批数全部相等（如果有的话，没有就和1.0一样），左边的所有都小于这批数，右边的所有都大于这批数
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort2(int[] arr, int left, int right) {
        if (arr == null || arr.length == 0 || arr.length == 1) {
            return ;
        }
        sort2(arr, left, right);
    }
    public static void sort2(int[] arr, int left, int right) {
        if (left >= right) {
            return ;
        }
        int[] mid = netherlandsFlagPartition(arr, left, right);
        sort2(arr, left, mid[0] - 1);
        sort2(arr, mid[1] + 1, right);
    }
    public static int[] netherlandsFlagPartition(int[] arr, int left, int right) {
        if (left > right) {
            return new int[] {-1, -1};
        }
        if (left == right) {
            return new int[] {left, right};
        }
        // 定义三个变量，小于区边界，大于区边界，当前值
        int less = left - 1, more = right, index = left;
        // 当当前值到了大于区的边界，就应该停止了，此时就已经完成了除了最后一个数，其他的数都满足左边小，中间等于，右边大
        while (index < more) {
            if (arr[index] < arr[right]) {
                swap(arr, index++, ++less);
            } else if (arr[index] == arr[right]) {
                index++;
            } else {
                swap(arr, index, --more);
            }
        }
        // 其他的数都归位，将最右边的数也进行归位即可，将其与当前的 more 进行交换，原本more所在的位置保存的就是大于right的数，
        // 交换之后就变成more右边的数才是大于right的数
        swap(arr, more, right);
        return new int[] {less + 1, more};
    }

    /**
     * 快排3.0，增加随机性，随机一个位置开始进行排序
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort3(int[] arr, int left, int right) {
        if (arr == null || arr.length == 0 || arr.length == 1) {
            return ;
        }
        sort2(arr, left, right);
    }
    public static void sort3(int[] arr, int left, int right) {
        if (left >= right) {
            return ;
        }
        // 随机一个位置，将其与最右边位置的数进行交换，由于随机性的产生，最后整体复杂度会收敛与O(N * log N)
        swap(arr, left + (int) (Math.random() * (right - left + 1)), right);
        int[] mid = netherlandsFlagPartition(arr, left, right);
        sort2(arr, left, mid[0] - 1);
        sort2(arr, mid[1] + 1, right);
    }
}
