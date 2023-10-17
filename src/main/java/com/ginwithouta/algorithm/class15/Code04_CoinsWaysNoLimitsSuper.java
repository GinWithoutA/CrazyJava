package com.ginwithouta.algorithm.class15;

import com.ginwithouta.algorithm.class10.Code05_TopologicalOrderDFS2;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package : com.ginwithouta.algorithm.class15
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周五
 * @Desc : 给定一个货币数组，每个值代表这个货币的价值，在给定一个值aim，求用数组中的货币，有多少种方法可以得到aim（每个值相同的货币认为没有任何区别）
 */
public class Code04_CoinsWaysNoLimitsSuper {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Info {
        private int[] values;
        private int[] zhangs;
    }
    public static Info getInfo(int[] coins) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int coin : coins) {
            if (!counts.containsKey(coin)) {
                counts.put(coin, 1);
            } else {
                counts.put(coin, counts.get(coin) + 1);
            }
        }
        int[] values = new int[counts.size()], zhangs = new int[counts.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            values[index] = entry.getKey();
            zhangs[index++] = entry.getValue();
        }
        return new Info(values, zhangs);
    }
    /**
     * 首先计算每个面值有多少张
     * @param coins
     * @param aim
     * @return
     */
    public static int coinsNoWaysNoLimitsSuper(int[] coins, int aim) {
        if (coins == null || coins.length == 0 || aim <= 0) {
            return 0;
        }
        Info info = getInfo(coins);
        return process(info,0, aim);
    }

    /**
     * 暴力递归
     * @param info
     * @param index
     * @param rest
     * @return
     */
    public static int process(Info info, int index, int rest) {
        if (index == info.getValues().length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; rest - zhang * info.getValues()[index] <= rest && zhang <= info.getZhangs()[index]; zhang++) {
            ways += process(info, index + 1, rest - zhang * info.getValues()[index]);
        }
        return ways;
    }
    /**
     * DP
     * @param coins
     * @param aim
     * @return
     */
    public static int coinsNoWaysNoLimitsSuperDP(int[] coins, int aim) {
        if (coins == null || coins.length == 0 || aim <= 0) {
            return 0;
        }
        Info info = getInfo(coins);
        int[][] dp = new int[info.getValues().length + 1][aim + 1];
        dp[info.getValues().length][0] = 1;
        for (int index = info.getValues().length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                for (int zhang = 0; rest - zhang * info.getValues()[index] <= rest && zhang <= info.getZhangs()[index]; zhang++) {
                    dp[index][rest] += dp[index + 1][rest - zhang * info.getValues()[index]];
                }
            }
        }
        return dp[0][aim];
    }
    /**
     * DP 改进方式，找依赖关系
     * @param coins
     * @param aim
     * @return
     */
    public static int coinsNoWaysNoLimitsSuperDPBetter(int[] coins, int aim) {
        if (coins == null || coins.length == 0 || aim <= 0) {
            return 0;
        }
        Info info = getInfo(coins);
        int[][] dp = new int[info.getValues().length + 1][aim + 1];
        dp[info.getValues().length][0] = 1;
        for (int index = info.getValues().length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest] + (rest - info.getValues()[index] >= 0 ? dp[index][rest - info.getValues()[index]] : 0)
                        - (rest - (info.getZhangs()[index] + 1) * info.getValues()[index] >= 0 ? dp[index + 1][(info.getZhangs()[index] + 1) * info.getValues()[index]] : 0);
            }
        }
        return dp[0][aim];
    }
}
