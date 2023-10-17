package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周一
 * @Desc :
 */
public class Problem_0050_PowXN {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1D;
        }
        // 如果次方数是Integer.MIN_VALUE
        // 当x > 1的时候，是一个很大的数，最后由于是负数次方，所以会变成0
        // 当x < 1的时候，是一个很小的数，最后由于是负数次方，所以会变成很大，溢出，溢出也返回0
        // 所以只需要考虑1和-1的时候
        if (n == Integer.MIN_VALUE && (x == 1D || x == -1D)) {
            return 1D;
        }
        double ans = 1D, t = x;
        int absN = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
        while (absN != 0) {
            if ((absN & 1) == 1) {
                ans *= t;
            }
            t *= t;
            absN >>= 1;
        }
        return n == Integer.MIN_VALUE ? (1D / ans) / x : (n < 0 ? 1D / ans : ans);
    }
}
