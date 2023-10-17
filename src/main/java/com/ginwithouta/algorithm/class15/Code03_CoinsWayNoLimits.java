package com.ginwithouta.algorithm.class15;

/**
 * @Package : com.ginwithouta.algorithm.class15
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周五
 * @Desc : 给定一个货币数组，每个值代表这个货币的价值，在给定一个值aim，求用数组中的货币，有多少种方法可以得到aim（每个货币可以无限取值）
 */
public class Code03_CoinsWayNoLimits {
    public static int coinsWaysNoLimits(int[] coins, int aim) {
        if (coins == null || coins.length == 0 || aim <= 0) {
            return 0;
        }
        return process(coins, 0, aim);
    }
    public static int process(int[] coins, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        // 根本不用担心如果rest == 0了怎么办，因为它第一次进来首先都是使用0张往后传，会一直传到index == coins.length的时候，然后返回1
        for (int zhang = 0; zhang * coins[index] <= rest; zhang++) {
            ways += process(coins, index + 1, rest - (zhang * coins[index]));
        }
        return ways;
    }
    public static int coinsWaysNoLimitsDP(int[] coins, int aim) {
        if (coins == null || coins.length == 0 || aim <= 0) {
            return 0;
        }
        int[][] dp = new int[coins.length + 1][aim + 1];
        dp[coins.length][0] = 1;
        for (int index = coins.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang * coins[index] <= rest; zhang++) {
                    dp[index][rest] += dp[index + 1][rest - (zhang * coins[index])];                }
            }
        }
        return dp[coins.length][0];
    }

    /**
     * DP优化版本，根据绝对空间结构，可以发现，第三个for循环依赖的是当前rest的index的位置，以及当前index的rest-coins[index]的位置，
     * 因为每次for循环，我们都会去[index - 1][rest - coins[index]]拿值，而这个值又被[index][rest - coins[index]]依赖，因此可以化简
     * @param coins
     * @param aim
     * @return
     */
    public static int coinsWaysNoLimitsDPBetter(int[] coins, int aim) {
        if (coins == null || coins.length == 0 || aim <= 0) {
            return 0;
        }
        int[][] dp = new int[coins.length + 1][aim + 1];
        dp[coins.length][0] = 1;
        for (int index = coins.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index - 1][rest] + (rest - coins[index] >= 0 ? dp[index][rest - coins[index]] : 0);
            }
        }
        return dp[coins.length][0];
    }
}
