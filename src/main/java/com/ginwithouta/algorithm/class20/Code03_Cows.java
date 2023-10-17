package com.ginwithouta.algorithm.class20;

/**
 * @Package : com.ginwithouta.algorithm.class20
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周二
 * @Desc : 第一年农场有一只成熟的母牛，往后的每年：
 *          （1）每一只成熟的母牛都会生一只母牛
 *          （2）每一只新出生的母牛都会在出生的第三年成熟
 *          （3）每一只母牛永远都不会死
 *          求第N年有多少头母牛
 */
public class Code03_Cows {
    public static int cows(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n ==3) {
            return n;
        }
        // 对于斐波那契数列来说，它的base矩阵如下
        long[][] base = {{1, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        long[][] power = matrixPower(base, n - 3);
        return (int) ((3 * power[0][0] + 2 * power[1][0] + power[2][0]) % 1000000007);
    }

    /**
     * 计算矩阵的次方
     * @param matrix
     * @param times
     * @return
     */
    public static long[][] matrixPower(long[][] matrix, int times) {
        long[][] res = new long[matrix.length][matrix[0].length];
        // 初始化一个单位矩阵
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        long[][] m = matrix;
        // 利用times的二进制数来计算需要多少个次方
        while (times != 0) {
            if ((times & 1) != 0) {
                res = multiMatrix(res, m);
            }
            // 更新m让他提前变成下一位对应的次方数
            m = multiMatrix(m, m);
            times = times >> 1;
        }
        return res;
    }

    public static long[][] multiMatrix(long[][] left, long[][] right) {
        long[][] res = new long[left.length][right[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                for (int k = 0; k < right.length; k++) {
                    res[i][j] += left[i][k] * right[k][j];
                    res[i][j] %= 1000000007;
                }
            }
        }
        return res;
    }
}
