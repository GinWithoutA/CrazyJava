package com.ginwithouta.algorithm.class17;

/**
 * @Package : com.ginwithouta.algorithm.class17
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc : 给定一个N*N的棋盘，需要在上面摆上N个皇后，要求任何两个皇后不同行，不同列，也不在同一条斜线上，求有多少种摆法
 */
public class Code03_NQueens {
    public static void main(String[] args) {
        System.out.println(nQueens(4));
    }
    public static int nQueens(int N) {
        if (N <= 0 || N == 2 || N == 3) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        int[] records = new int[N];
        return process(records, 0);
    }
    public static int process(int[] records, int index) {
        // baseCase
        if (index == records.length) {
            return 1;
        }
        int ways = 0;
        for (int col = 0; col < records.length; col++) {
            if (isValid(records, index, col)) {
                records[index] = col;
                ways += process(records, index + 1);
            }
        }
        return ways;
    }

    /**
     * 判断当前位置是否可以放皇后，如果和前面的共列或者共斜线，false
     * @param records       之前的放置信息
     * @param row           当前要放的皇后在哪一行
     * @param column        当前要放的皇后在哪一列
     * @return
     */
    public static boolean isValid(int[] records, int row, int column) {
        for (int cur = 0; cur < row; cur++) {
            if (column == records[cur] || Math.abs(row - cur) == Math.abs(column - records[cur])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 改进后的方法
     * @param limit         总的限制
     * @param leftLimit     左下角不能放的位置
     * @param rightLimit    右下角不能放的位置
     * @param colLimit      列不能放的位置
     * @return              总的能放的方案数量
     */
    public static int process(int limit, int leftLimit, int rightLimit, int colLimit) {
        if (limit == colLimit) {
            return 1;
        }
        // 找到所有可能放皇后的位置，只有1的位置能放
        int possibilityPose = limit & (~(leftLimit | rightLimit | colLimit));
        int mostRightOne = 0;
        int res = 0;
        while (possibilityPose != 0) {
            mostRightOne = possibilityPose & (~possibilityPose + 1);
            possibilityPose -= mostRightOne;
            res += process(limit, (leftLimit | mostRightOne) << 1, (rightLimit | mostRightOne) >>> 1, colLimit | mostRightOne);
        }
        return res;
    }
}
