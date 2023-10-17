package com.ginwithouta.algorithm.class16;

/**
 * @Package : com.ginwithouta.algorithm.class16
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周六
 * @Desc : 怪兽有N滴血，等着英雄来砍自己，英雄每一次打击都会让怪兽流失[0, M]的血量，
 *         到底流失多少是不确定的，每次在[0, M]上等概率获取一个值，
 *         求K次打击后，打死怪兽的概率
 */
public class Code01_KillMonster {
    /**
     *
     * @param hp        怪兽的血量
     * @param atk       攻击力
     * @param times     攻击的次数
     * @return          击败的概率
     */
    public static double killMonsterProbability(int hp, int atk, int times) {
        if (hp <= 0 || atk <= 0 || times <= 0) {
            return 0;
        }
        // 能攻击的所有情况，atk的范围是[0, atk]，总的大小是atk + 1
        long all = (long) Math.pow((atk + 1), times);
        long success = process(hp, atk, times);
        return (double) success / all;
    }
    /**
     * 暴力递归求解
     * @param hp        剩余的HP
     * @param atk       总的攻击力范围
     * @param times     剩余的攻击次数
     * @return          击败的次数
     */
    public static long process(int hp, int atk, int times) {
        // baseCase
        if (times == 0) {
            return hp <= 0L ? 1L : 0L;
        }
        // 有可能还有攻击次数，但是hp已经小于等于0了，直接返回，省得再递归
        // 还有剩余次数，仍然要用完，因此返回的不是1，而是所有的能攻击的次数
        if (hp <= 0) {
            return (long) Math.pow(atk + 1, times);
        }
        // 递归找所有可能的情况
        long ways = 0L;
        for (int cur = 0; cur <= atk; cur++) {
            ways += process(hp - cur, atk, times - 1);
        }
        return ways;
    }

    /**
     * DP方法
     * @param hp        怪兽的血量
     * @param atk       攻击力
     * @param times     攻击的次数
     * @return          击败的概率
     */
    public static double killMonsterProbabilityDP(int hp, int atk, int times) {
        if (hp <= 0 || atk <= 0 || times <= 0) {
            return 0;
        }
        long all = (long) Math.pow(atk + 1, times);
        int[][] dp = new int[times + 1][hp + 1];
        // baseCase
        dp[0][0] = 1;
        for (int time = 1; time <= times; time++) {
            // dp[time][0]表示当前次数还剩time次，剩余的hp为0，此时可能获得的总的获胜次数就应该是Math.pow(atk + 1, time)
            dp[time][0] = (int) Math.pow(atk + 1, time);
            for (int rest = 1; rest <= hp; rest++) {
                for (int cur = 0; cur <= atk; cur++) {
                    if (rest - cur < 0) {
                        // 当前的cur的值表示在剩余time次数，rest的生命值的情况下，在time用完的情况下，能获得多少赢的次数
                        // 如果在当前的攻击力下，导致怪兽的点数小于0了，那么当前的值就应该是time - 1后所有的可能攻击次数
                        // 这里的time需要减1，含义是我是用了一个cur的攻击力，才导致怪兽的剩余HP变成了小于0的状况
                        dp[time][rest] += (int) Math.pow(atk + 1, time - 1);
                    } else {
                        dp[time][rest] += dp[time - 1][rest - cur];
                    }
                }
            }
        }
        return (double) all / dp[times][hp];
    }

    /**
     * DP方法改进方法，可以发现，每个格子依赖的是[time - 1][rest] + [time - 1][rest - 1] + ...  + [time - 1][rest - atk]的值
     * 而同一行前面一个格子[time][rest - 1]的值是通过 [time - 1][rest - 1] + [time - 1][rest - 2] + ...  + [time - 1][rest - atk - 1]计算出来的
     * 因此，我们可以将第三层for循环根除，依赖关系就是[time][rest] = [time - 1][rest] + [time][rest - 1] - [time - 1][rest - atk - 1]
     * 要注意的是，rest - atk - 1的值可能不在表格中，也就是说这种情况是hp已经小于0的情况，因此要通过Math.pow(atk + 1, time - 1)计算总的次数
     * @param hp        怪兽的血量
     * @param atk       攻击力
     * @param times     攻击的次数
     * @return          击败的概率
     */
    public static double killMonsterProbabilityDPBetter(int hp, int atk, int times) {
        if (hp <= 0 || atk <= 0 || times <= 0) {
            return 0;
        }
        long all = (long) Math.pow(atk + 1, times);
        int[][] dp = new int[times + 1][hp + 1];
        // baseCase
        dp[0][0] = 1;
        for (int time = 1; time <= times; time++) {
            dp[time][0] = (int) Math.pow(atk + 1, time - 1);
            for (int rest = 1; rest <= hp; rest++) {
                dp[time][rest] = dp[time - 1][rest] + dp[time][rest - 1];
                if (rest - atk - 1 < 0) {
                    // 前一个人，用了一个time，和一个cur，使得rest - atk - 1 < 0，此时，所有剩余能获得点数的机会总共是Math.pow(atk + 1, time)，
                    // 所以我们这里还要减去一个Math.pow(atk + 1, time)
                    dp[time][rest] -= (int) Math.pow(atk + 1, time - 1);
                } else {
                    dp[time][rest] -= dp[time - 1][rest - atk - 1];
                }
            }
        }
        return (double) all / dp[times][hp];
    }

}
