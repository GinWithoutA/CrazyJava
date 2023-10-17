package com.ginwithouta.algorithm.class16;

/**
 * @Package : com.ginwithouta.algorithm.class16
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周六
 * @Desc : 拆分数字，规定前面的数字必须小于后面的数字，问有多少种拆分的方法，规定只能正数
 */
public class Code03_SpiltNumber {
    public static void main(String[] args) {
        System.out.println(spiltNumber(13));
        System.out.println(spiltNumberDP(13));
        System.out.println(spiltNumberDPBetter(13));
    }
    public static int spiltNumber(int number) {
        if (number <= 0) {
            return 0;
        }
        return process(1, number);
    }

    /**
     * 暴力递归
     * @param pre   上一个拆分出来的数字
     * @param rest  剩余需要拆分的数字
     * @return
     */
    public static int process(int pre, int rest) {
        // 这一句必须写在前面，不然后面的pre > rest的判断会把它覆盖掉，
        // 如果剩余0，表明这次的拆分是正确的，返回1
        if (rest == 0) {
            return 1;
        }
        // 如果拆出来的数字比剩下的数字大，就没有位置了，表明这次的拆分是错误的，返回
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int cur = pre; cur <= rest; cur++) {
            ways += process(cur, rest - cur);
        }
        return ways;
    }
    public static int spiltNumberDP(int number) {
        if (number <= 0) {
            return 0;
        }
        int [][] dp = new int[number + 1][number + 1];
        // 对角线表示pre == rest的情况，这种情况也是返回1
        for (int pre = 1; pre <= number; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = number - 1; pre > 0; pre--) {
            for (int rest = pre + 1; rest <= number; rest++) {
                for (int cur = pre; cur <= rest; cur++) {
                    dp[pre][rest] += dp[cur][rest - cur];
                }
            }
        }
        return dp[1][number];
    }
    public static int spiltNumberDPBetter(int number) {
        if (number <= 0) {
            return 0;
        }
        int [][] dp = new int[number + 1][number + 1];
        // 对角线表示pre == rest的情况，这种情况也是返回1
        for (int pre = 1; pre <= number; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        // 查找位置依赖关系，可以发现依赖关系是[pre + 1][rest]的位置的所有可以拆分的数量，加上我自己的第一个可以拆分的数量[pre][rest - pre]
        for (int pre = number - 1; pre > 0; pre--) {
            for (int rest = pre + 1; rest <= number; rest++) {
                dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
            }
        }
        return dp[1][number];
    }
}
