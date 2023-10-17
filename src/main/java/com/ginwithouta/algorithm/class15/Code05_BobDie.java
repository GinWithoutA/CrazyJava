package com.ginwithouta.algorithm.class15;

import java.awt.print.Printable;

/**
 * @Package : com.ginwithouta.algorithm.class15
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周五
 * @Desc : 给定5个参数，N，M，row，col，K，其中N和M表示一个N行M列的棋盘，row和col表示bob空降的位置，K表示Bob行走的步数。Bob只要离开了这个区域，就会死亡，求Bob的存活率
 */
public class Code05_BobDie {
    public static double livePosibility(int N, int M, int row, int col, int K) {
        if (row <= 0 || row >= N || col <= 0 || col >= M || K <= 0) {
            return 0;
        }
        return liveTimes(N, M, K, row, col) / Math.pow(4, K);
    }

    /**
     * 暴力递归
     * @param N
     * @param M
     * @param rest
     * @param row
     * @param col
     * @return
     */
    public static int liveTimes(int N, int M, int rest, int row, int col) {
        if (row == N || row < 0 || col == M || col < 0) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        int lives = liveTimes(N, M, rest - 1, row + 1, col);
        lives += liveTimes(N, M, rest - 1, row - 1, col);
        lives += liveTimes(N, M, rest - 1, row, col + 1);
        lives += liveTimes(N, M, rest - 1, row, col - 1);
        return lives;
    }

    public static double livePosibilityDP(int N, int M, int row, int col, int K) {
        if (row <= 0 || row >= N || col <= 0 || col >= M || K <= 0) {
            return 0;
        }
        int[][][] dp = new int[N][M][K + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= K; rest++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    dp[i][j][rest] += i + 1 != N ? dp[i + 1][j][rest - 1] : 0;
                    dp[i][j][rest] += i - 1 >= 0 ? dp[i - 1][j][rest - 1] : 0;
                    dp[i][j][rest] += j + 1 < M ? dp[i][j + 1][rest - 1] : 0;
                    dp[i][j][rest] += j - 1 >= 0 ? dp[i][j - 1][rest - 1] : 0;
                }
            }
        }
        return dp[row][col][K];
    }

}
