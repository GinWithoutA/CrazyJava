package com.ginwithouta.algorithm.class14;

/**
 * @Package : com.ginwithouta.algorithm.class14
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周四
 * @Desc : 象棋上马的跳跃问题（棋盘大小是10*9），从一个起始点，跳到目标点，规定只能跳K步
 */
public class Code02_HorseJump {
    public static int horseJump(int x, int y, int k) {
        return processViolentRecursion(0, 0, k, x, y);
    }

    /**
     * 暴力递归
     * @param a     起始位置
     * @param b     起始位置
     * @param rest  剩余跳跃步数
     * @param x     目标位置
     * @param y     目标位置
     * @return
     */
    public static int processViolentRecursion(int a, int b, int rest, int x, int y) {
        // 边界判断
        if (a < 0 || a > 9 || b < 0 || b > 8) {
            return 0;
        }
        // baseCase
        if(rest == 0) {
            return (a == x && b == y) ? 1 : 0;
        }
        // 八个方向，随便跳
        int ways = processViolentRecursion(a + 2, b + 1, rest - 1, x, y);
        ways += processViolentRecursion(a + 1, b + 2, rest - 1, x, y);
        ways += processViolentRecursion(a - 1, b + 2, rest - 1, x, y);
        ways += processViolentRecursion(a - 2, b + 1, rest - 1, x, y);
        ways += processViolentRecursion(a - 2, b - 1, rest - 1, x, y);
        ways += processViolentRecursion(a - 1, b - 2, rest - 1, x, y);
        ways += processViolentRecursion(a + 1, b - 2, rest - 1, x, y);
        ways += processViolentRecursion(a + 2, b - 1, rest - 1, x, y);
        return ways;
    }

    public static int horseJumpDP(int x, int y, int k) {
        int[][][] dp = new int[10][9][k + 1];
        // baseCase，rest==0的时候，只有到达了目标位置，才返回1
        dp[x][y][0] = 1;
        // 剩余的部分都依赖下一层的rest
        for (int rest = 1; rest <= k; rest++) {
            for (int a = 0; a < 10; a++) {
                for (int b = 0; b < 9; b++) {
                    int ways = peek(dp, a + 2, b + 1, rest - 1);
                    ways += peek(dp, a + 1, b + 2, rest - 1);
                    ways += peek(dp, a - 1, b + 2, rest - 1);
                    ways += peek(dp, a - 2, b + 1, rest - 1);
                    ways += peek(dp, a - 2, b - 1, rest - 1);
                    ways += peek(dp, a - 1, b - 2, rest - 1);
                    ways += peek(dp, a + 1, b - 2, rest - 1);
                    ways += peek(dp, a + 2, b - 1, rest - 1);
                    dp[a][b][rest] = ways;
                }
            }
        }
        return dp[x][y][k];
    }
    public static int peek(int[][][] dp, int a, int b, int rest) {
        // 边界判断
        if (a < 0 || a > 9 || b < 0 || b > 8) {
            return 0;
        }
        return dp[a][b][rest];
    }

    public static void main(String[] args) {
        System.out.println(horseJump(7, 7, 10));
    }
}
