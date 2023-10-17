package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周五
 * @Desc :
 */
public class Problem_0029_DivideTwoIntegers {
    public int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    public int negNum(int a) {
        return this.add(~a, 1);
    }

    public int minus(int a, int b) {
        return this.add(a, this.negNum(b));
    }

    public int multi(int a, int b) {
        int res = (1 & b) == 1 ? this.add(a, 0) : 0;
        while (b != 0) {
            a <<= 1;
            // 无符号右移
            b >>>= 1;
            res = (1 & b) == 1 ? this.add(res, a) : res;
        }
        return res;
    }

    public int divide(int a, int b) {
        if (b == Integer.MIN_VALUE) {
            return a == Integer.MIN_VALUE ? 1 : 0;
        }
        if (a == Integer.MIN_VALUE) {
            if (b == this.negNum(1)) {
                return Integer.MAX_VALUE;
            }
            int res = this.div(add(a, 1), b);
            return this.add(res, this.div(this.minus(a, this.multi(res, b)), b));
        }
        return this.div(a, b);
    }

    public int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a, y = isNeg(b) ? negNum(b) : b, res = 0;
        for (int i = 31; i >= 0; i = this.minus(i, 1)) {
            if ((x >> i) >= y) {
                // 表示res的当前位置应该为1
                res |= (1 << i);
                x = this.minus(x, y << i);
            }
        }
        return this.isNeg(a) ^ this.isNeg(b) ? this.negNum(res) : res;
    }

    public boolean isNeg(int a) {
        return a < 0;
    }

    public static void main(String[] args) {
        Problem_0029_DivideTwoIntegers test = new Problem_0029_DivideTwoIntegers();
        // System.out.println(test.multi(Integer.MIN_VALUE + 1, 1));
        System.out.println(test.divide(Integer.MIN_VALUE, -3));
    }
}
