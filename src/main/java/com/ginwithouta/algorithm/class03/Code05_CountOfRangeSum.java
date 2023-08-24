package com.ginwithouta.algorithm.class03;

import java.util.Arrays;

/**
 * @Package : com.ginwithouta.algorithm.class03
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周一
 * @Desc : 在一个给定的数组中，判断存在多少个子数组，他们的和满足在区间[lower, upper]上
 * @method 这道题可以用前缀和进行替换来求解。首先，题目可以理解为以数组中的每一个位置为结尾，求有多少个子数组的和的区间在[lower, upper]上。
 *          然后，我们可以计算每个元素的前缀和，得到一个新的前缀和数组sum，原题目中求多少子数组的和是在[lower, upper]上的，就可以变成求在
 *          sum数组中，以每个位置为结尾，从0开始，有多少个位置的前缀和是在[总的前缀和-upper, 总的前缀和-lower]，这一点可以用归并来完成，
 *          也就是求以右边为主，左边有多少个元素的前缀和在刚才说的区间中
 */
public class Code05_CountOfRangeSum {
    public static int countOfRangeSum(int[] arr, int lower, int upper) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 构建一个前缀和数组，其中的每个元素都是从0开始到i的所有元素的和构成
        //
        long[] sum = new long[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = arr[i] + sum[i - 1];
        }
        System.out.println(Arrays.toString(sum));
        int ans = count(sum, 0, sum.length - 1, lower, upper);
        System.out.println(Arrays.toString(sum));
        return ans;
    }
    public static int count(long[] sum, int left, int right, int lower, int upper) {
        // 表示当前只有一个元素，由于我们传入的是前缀和数组，假设当前位置是i，就表示当前是原数组中[0, i]的前缀和
        // 对于sum这个数组，此时这个位置的元素单独构成一个，没有左组合并的问题，则直接判断它是否处于所需要的区间中
        if (left == right) {
            if (sum[left] >= lower && sum[left] <= upper) {
                return 1;
            } else {
                return 0;
            }
        }
        // 用归并，当进行数组合并的时候，就可以判断存在多少个子数组
        int mid = left + ((right - left) >> 1);
        return count(sum, left, mid, lower, upper) + count(sum, mid + 1, right, lower, upper) + merge(sum, left, mid, right, lower, upper);
    }
    public static int merge(long[] sum, int left, int mid, int right, int lower, int upper) {
        // 在这个merge中，我们的目标是针对右组中的每个数，找到左组中所有前缀和是在区间[当前右组前缀和 - upper, 当前右组前缀和 - lower]的个数
        int ans = 0;
        // 给定一个窗口，由于左右都是排好序的，对于右组来说，比当前元素右边的元素，区间只会上升，这一点会反馈给左组元素，使得这个窗口会一直向右移动
        int windowLeft = left, windowRight = left;
        for (int i = mid + 1; i <= right; i++) {
            // 计算新的区间
            long min = sum[i] - upper;
            long max = sum[i] - lower;
            while (windowRight <= mid && sum[windowRight] <= max) {
                windowRight++;
            }
            while (windowLeft <= mid && sum[windowLeft] < min) {
                windowLeft++;
            }
            // 有可能会出现left超过right的情况
            ans += windowRight - windowLeft;
        }
        long[] temp = new long[right - left + 1];
        int p1 = left, p2 = mid + 1, index = 0;
        while (p1 <= mid && p2 <= right) {
            if (sum[p1] <= sum[p2]) {
                temp[index++] = sum[p1++];
            } else {
                temp[index++] = sum[p2++];
            }
        }
        while (p1 <= mid) {
            temp[index++] = sum[p1++];
        }
        while (p2 <= right) {
            temp[index++] = sum[p2++];
        }
        for (index = 0; index < temp.length; index++) {
            sum[left + index] = temp[index];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {-2147483647, 0, -2147483647, 2147483647};
        int a = -2147483647;
        long b = -2147483647;
        System.out.println(a);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(countOfRangeSum(nums, -564, 3864));

    }
}
