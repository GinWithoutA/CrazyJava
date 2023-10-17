package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周四
 * @Desc :
 */
public class Problem_0122_BestTimeToBuyAndSellStockII {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += prices[i] > prices[i - 1] ? prices[i] - prices[i - 1] : 0;
        }
        return ans;
    }
}
