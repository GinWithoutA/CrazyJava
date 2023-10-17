package com.ginwithouta.algorithm.class29;

/**
 * @Package : com.ginwithouta.algorithm.class29
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周日
 * @Desc : 打怪兽花最少的钱的问题
 */
public class Code04_MoneyProblem {
    public static int minMoney(int[] d, int[] p) {
        if (d == null || d.length == 0 || p == null || p.length == 0) {
            return 0;
        }
        return processAbility(d, p, 0, 0);
    }

    /**
     * 将能力作为主要的关注点
     * @param d         能力
     * @param p         钱
     * @param ability   当前拥有的能力
     * @param index     当前位置
     * @return          最少的钱
     */
    public static int processAbility(int[] d, int[] p, int ability, int index) {
        if (index == d.length) {
            return 0;
        }
        int p1 = ability >= d[index] ? processAbility(d, p, ability, index + 1) : Integer.MAX_VALUE;
        int p2 = p[index] + processAbility(d, p, ability + d[index], index + 1);
        return Math.min(p1, p2);
    }

    /**
     * 将钱作为主要的关注点，我们考虑如果要花指定的钱，能够达到多大的能力值
     * @param d         能力数组
     * @param p         钱数组
     * @param index     当前位置
     * @param money     剩余的钱
     * @return          最大能力
     */
    public static long processMoney(int[] d, int[] p, int index, int money) {
        if (index == -1) {
            return money == 0 ? 0 : -1;
        }
        long p1 = -1;
        long preMaxAbility = processMoney(d, p, index - 1, money);
        if (preMaxAbility != -1 && preMaxAbility >= d[index]) {
            p1 = preMaxAbility;
        }
        long p2 = -1;
        preMaxAbility = processMoney(d, p, index - 1, money - p[index]);
        if (preMaxAbility != -1) {
            p2 = preMaxAbility + d[index];
        }
        return Math.max(p1, p2);
    }

}
