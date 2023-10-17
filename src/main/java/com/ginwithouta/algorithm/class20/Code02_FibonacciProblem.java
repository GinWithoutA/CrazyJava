package com.ginwithouta.algorithm.class20;


/**
 * @Package : com.ginwithouta.algorithm.class20
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周二
 * @Desc : 斐波那契数列问题，它的严格递推公式是F(n - 1) + F(n - 2)，要求返回第N项的值
 * @method : 对于这种有严格递推公式的递归问题，都可以转换成矩阵的n次方问题
 */
public class Code02_FibonacciProblem {
    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        // 对于斐波那契数列来说，它的base矩阵如下
        long[][] base = {{1, 1}, {1, 0}};
        long[][] power = matrixPower(base, n - 2);
        System.out.println(power);
        return (int) ((power[0][0] + power[1][0]) % 1000000007);
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

    public static void main(String[] args) {
        System.out.println(fibonacci(5));
    }
}
