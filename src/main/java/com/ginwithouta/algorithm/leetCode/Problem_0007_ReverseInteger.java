package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode.class01
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周二
 * @Desc :
 */
public class Problem_0007_ReverseInteger {
    public static int reverse(int x) {
        boolean neg = ((x >> 31) & 1) == 1;
        x = neg ? x : -x;
        int result = 0, m = Integer.MIN_VALUE / 10, o = Integer.MIN_VALUE % 10;
        while (x != 0) {
            // 如果当前值比系统最小/10还要小，那么一会乘10之后肯定会溢出
            // 如果当前值等于系统最小/10，但是要加的数比系统最小%10还要小，那么一会结果肯定溢出
            if (result < m || (result == m && x % 10 < o)) {
                return 0;
            }
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return neg ? result : Math.abs(result);
    }
    public static void main(String[] args) {
        System.out.println(-8 % 10);
    }
}
