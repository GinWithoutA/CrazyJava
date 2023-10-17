package com.ginwithouta.algorithm.leetCode;

import java.util.IllegalFormatPrecisionException;
import java.util.PriorityQueue;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周四
 * @Desc :
 */
public class Problem_0188_BestTimeToBuyAndSellStockIV {
    public int maxProfitBefore(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        if (k >= prices.length / 2) {
            return allChance(prices);
        }
        int[][] dp = new int[prices.length][k + 1];
        for (int day = 1; day < dp.length; day++) {
            for (int count = 1; count <= k; count++) {
                // case1 当前day没有参与交易
                dp[day][count] = dp[day - 1][count];
                // case2 当前day参与了交易
                for (int pre = 0; pre < day; pre++) {
                    dp[day][count] = Math.max(dp[day][count], dp[pre][count - 1] + prices[day] - prices[pre]);
                }
            }
        }
        return dp[prices.length - 1][k];
    }
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        if (k >= prices.length / 2) {
            return allChance(prices);
        }
        int[][] dp = new int[prices.length][k + 1];
        int pre, best, ans = 0;
        for (int count = 1; count <= k; count++) {
            pre = dp[0][count];
            best = pre - prices[0];
            for (int day = 1; day < prices.length; day++) {
                pre = dp[day][count - 1];
                dp[day][count] = Math.max(dp[day - 1][count], best + prices[day]);
                best = Math.max(best, pre - prices[day]);
                ans = Math.max(dp[day][count], ans);
            }
        }
        return ans;
    }
    public int allChance(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        return ans;
    }
}
