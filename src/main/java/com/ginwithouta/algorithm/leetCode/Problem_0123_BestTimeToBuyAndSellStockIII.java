package com.ginwithouta.algorithm.leetCode;

import javax.print.attribute.standard.PrinterInfo;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周四
 * @Desc :
 */
public class Problem_0123_BestTimeToBuyAndSellStockIII {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 3) {
            return 0;
        }
        int doneOnce = 0;
        // 一开始从第二天开始算，那么前面的最好情况就是第一天买入再卖出，然后再减去第一天的买入，所以是-prices[0]
        int doneOnceMinusBuyMax = -prices[0];
        int ans = 0, min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            ans = Math.max(ans, doneOnceMinusBuyMax + prices[i]);
            min = Math.min(min, prices[i]);
            doneOnce = Math.max(doneOnce, prices[i] - min);
            // 可以当天卖出再当天买入
            doneOnceMinusBuyMax = Math.max(doneOnceMinusBuyMax, doneOnce - prices[i]);
        }
        return ans;
    }
}
