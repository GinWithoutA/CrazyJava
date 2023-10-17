package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode.class01
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周一
 * @Desc :
 */
public class Problem_0004_MedianOfTwoSortedArrays {
    /**
     * 求指定范围内相同长度的两个数组的整体上中位数
     * @param nums1     数组1
     * @param left1     数组1的左边界
     * @param right1    数组1的右边界
     * @param nums2     数组2
     * @param left2     数组2的左边界
     * @param right2    数组2的右边界
     * @return
     */
    public static int getUpMedian(int[] nums1, int left1, int right1, int[] nums2, int left2, int right2) {
        int mid1 = 0, mid2 = 0;
        while (left1 < right1) {
            mid1 = (left1 + right1) / 2;
            mid2 = (left2 + right2) / 2;
            // 如果两个数组的上中位数相等，那么整个合并的数组的上中位数就是这两个中位数的任意一个
            if (nums1[mid1] == nums2[mid2]) {
                return nums1[mid1];
            }
            // 奇数情况，假设5个数
            if (((right1 - left1 + 1) & 1) == 1) {
                if (nums1[mid1] > nums2[mid2]) {
                    if (nums1[mid1 - 1] <= nums2[mid2]) {
                        return nums2[mid2];
                    }
                    right1 = mid1 - 1;
                    left2 = mid2 + 1;
                } else {
                    if (nums1[mid1] >= nums2[mid2 - 1]) {
                        return nums1[mid1];
                    }
                    right2 = mid2 - 1;
                    left1 = mid1 + 1;
                }
            } else {
                // 偶数情况
                if (nums1[mid1] > nums2[mid2]) {
                    right1 = mid1;
                    left2 = mid2 + 1;
                } else {
                    left1 = mid1 + 1;
                    right2 = mid2;
                }
            }
        }
        return Math.min(nums1[left1], nums2[left2]);
    }

    /**
     * 返回数组中kTH小的数字
     * @param nums1
     * @param nums2
     * @param kTh
     * @return
     */
    public static int findKthNum(int[] nums1, int[] nums2, int kTh) {
        int[] longs = nums1.length > nums2.length ? nums1 : nums2;
        int[] shorts = longs == nums1 ? nums2 : nums1;
        if (kTh <= shorts.length) {
            return getUpMedian(shorts, 0, kTh - 1, longs, 0, kTh - 1);
        } else if (kTh > longs.length) {
            int shortLeft = kTh - longs.length - 1, longLeft = kTh - shorts.length - 1;
            if (shorts[shortLeft] >= longs[longs.length - 1]) {
                return shorts[shortLeft];
            }
            if (longs[longLeft] >= shorts[shorts.length - 1]) {
                return longs[longLeft];
            }
            return getUpMedian(shorts, shortLeft + 1, shorts.length - 1, longs, longLeft + 1, longs.length - 1);
        } else {
            int longLeft = kTh - shorts.length - 1, longRight = kTh - 1;
            if (longs[longLeft] >= shorts[shorts.length - 1]) {
                return longs[longLeft];
            }
            return getUpMedian(shorts, 0, shorts.length - 1, longs, longLeft + 1, longRight);
        }
    }

    /**
     * 主函数调用
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        boolean even = (size & 1) == 0;
        if (nums1.length == 0 && nums2.length == 0) {
            return 0;
        } else if (nums1.length != 0 && nums2.length != 0) {
            if (even) {
                return (double) (findKthNum(nums1, nums2, size / 2) + findKthNum(nums1, nums2, size / 2 + 1)) / 2;
            } else {
                return findKthNum(nums1, nums2, size / 2 + 1);
            }
        } else if (nums1.length == 0) {
            if (even) {
                return (double) (nums2[(nums2.length - 1) / 2] + nums2[nums2.length / 2]) / 2;
            } else {
                return nums2[nums2.length / 2];
            }
        } else {
            if (even) {
                return (double) (nums1[(nums1.length - 1) / 2] + nums1[nums1.length / 2]) / 2;
            } else {
                return nums1[nums1.length / 2];
            }
        }
    }

}
