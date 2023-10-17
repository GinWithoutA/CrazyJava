package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周一
 * @Desc : 100 = 3 + 69258 / 714
 *         100 = 82 + 3546 / 197
 *         等号左边的部分，可以写成 p1 + p2 / p3 的形式，要求 p1 p2 p3把 1~9 所有的数字使用完，并且不重复
 *         p2 / p3 必须是整数，如果满足，我们称 p1 + p2 / p3 为一个有效的带分数形式
 *         输入N，返回有多少种有效的带分数形式
 */
public class Problem_9999_AddDevideNum {
    public static final int[] BITNUM = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};

    /**
     * 交换一个数中两个位置的值，标号是从右往左标
     * @param num
     * @param left
     * @param right
     * @return
     */
    public static int swap(int num, int left, int right) {
        int bitLeft = num / BITNUM[left] % 10;
        int bitRight = num / BITNUM[right] % 10;
        return num - (bitLeft - bitRight) * BITNUM[left] + (bitLeft - bitRight) * BITNUM[right];
    }

    public static void process(int num, int index) {
        if (index == -1) {
            // 到这里，跑完了一次组合，当前的 num 就是当前的一次组合

        } else {
            // 找到所有的 1~9 的组合可能
            for (int cur = index; cur >= 0; cur--) {
                int next = swap(num, index, cur);
                process(next, index - 1);
            }
        }
    }

}
