package com.ginwithouta.algorithm.class15;

/**
 * @Package : com.ginwithouta.algorithm.class15
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周五
 * @Desc : 给定一个数组，数组中的每个值表示当前到这个格子所需要的代价，给定起始点[0, 0]，求到达右下角的最小代价
 */
public class Code01_MinPathSum {
    /**
     *
     * @param weights 二维权重矩阵
     * @return
     */
    public static int minPahtSumDP(int[][] weights) {
        if (weights == null || weights.length == 0 || weights[0].length == 0) {
            return 0;
        }
        int[][] dp = new int [weights.length][weights[0].length];
        dp[0][0] = weights[0][0];
        for (int i = 1; i < weights[0].length; i++) {
            dp[0][i] = dp[0][i - 1] + weights[0][i];
        }
        for (int i = 1; i < weights.length; i++) {
            dp[i][0] = dp[i - 1][0] + weights[i][0];
        }
        for (int i = 1; i < weights.length; i++) {
            for (int j = 1; j < weights[0].length; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + weights[i][j];
            }
        }
        return dp[weights.length - 1][weights[0].length - 1];
    }

    /**
     * 优化版本，空间优化，不需要二维DP数组，只需要一个一维的DP数组就可以搞定所有内容
     * @param grids 权重网格
     * @return
     */
    public static int minPathSumDP(int[][] grids) {
        if (grids == null || grids.length == 0 || grids[0].length == 0) {
            return 0;
        }
        int[] dp = new int[grids[0].length];
        dp[0] = grids[0][0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1] + grids[0][i];
        }
        for (int i = 1; i < grids.length; i++) {
            // 每次的第一个元素都是上一行的第一个值加当前的grid值
            dp[0] += grids[i][0];
            for (int j = 1; j < dp.length; j++) {
                // 每个中间元素都是左边和上边取最小然后加上grid的值
                dp[j] = Math.min(dp[j - 1], dp[j]) + grids[i][j];
            }
        }
        return dp[dp.length - 1];
    }
}
