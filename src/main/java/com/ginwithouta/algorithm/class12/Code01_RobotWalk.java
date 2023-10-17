package com.ginwithouta.algorithm.class12;

/**
 * @Package : com.ginwithouta.algorithm.class12
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周二
 * @Desc : 机器人走路。给定一个长度为N的路，给定一个目标位置Aim，给定一个初始位置，每次必须走K步，求总共有多少种走的办法
 */
public class Code01_RobotWalk {
    public static void main(String[] args) {
        System.out.println(robotWalkMethod1(5, 4, 2, 6));
        System.out.println(robotWalkMethod2(5, 4, 2, 6));
        System.out.println(robotWalkMethod3(5, 4, 2, 6));
    }

    /**
     * @param N   总长度
     * @param aim 目标位置
     * @param ini 初始位置
     * @param K   指定的步数
     * @return 总的移动方式
     */
    public static int robotWalkMethod1(int N, int aim, int ini, int K) {
        return processMethod1(ini, K, N, aim);
    }

    /**
     * @param cur  当前位置
     * @param rest 剩余能走多少步
     * @param N    总的长度
     * @param aim  目标位置
     * @return 总的移动方式的数量
     */
    public static int processMethod1(int cur, int rest, int N, int aim) {
        if (rest == 0) {
            // 没有剩余的步数可以走了，那么就判断当前是否在aim上，在的话就说明找到了一种方式，不在返回0
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            // 当前在最左边，只能往右边走
            return processMethod1(2, rest - 1, N, aim);
        }
        if (cur == N) {
            // 当前在最右边，只能往左边走
            return processMethod1(N - 1, rest - 1, N, aim);
        }
        // 当前在中间，能往两边走
        return processMethod1(cur - 1, rest - 1, N, aim) + processMethod1(cur + 1, rest - 1, N, aim);
    }

    /**
     * 添加缓存
     *
     * @param N   总长度
     * @param aim 目标位置
     * @param ini 初始位置
     * @param K   指定的步数
     * @return 总的移动方式
     */
    public static int robotWalkMethod2(int N, int aim, int ini, int K) {
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }
        return processMethod2(ini, K, N, aim, dp);
    }

    /**
     * @param cur  当前位置
     * @param rest 剩余能走多少步
     * @param N    总的长度
     * @param aim  目标位置
     * @param dp   用来缓存当前位置以及剩余步数所对应结果的缓存
     * @return 总的移动方式的数量
     */
    public static int processMethod2(int cur, int rest, int N, int aim, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int ans = 0;
        if (rest == 0) {
            // 没有剩余的步数可以走了，那么就判断当前是否在aim上，在的话就说明找到了一种方式，不在返回0
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            // 当前在最左边，只能往右边走
            ans = processMethod1(2, rest - 1, N, aim);
        } else if (cur == N) {
            // 当前在最右边，只能往左边走
            ans = processMethod1(N - 1, rest - 1, N, aim);
        } else {
            // 当前在中间，能往两边走
            ans = processMethod1(cur - 1, rest - 1, N, aim) + processMethod1(cur + 1, rest - 1, N, aim);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    /**
     * 最终版本 动态规划
     * 通过表格可以发现，第一列是rest==0的时候，是我们的BaseCase，在这一列中，只有dp[ami][0] = 1，也就是说
     * 只有机器人走到了这个步骤，才会返回一种走的方式。当rest != 0的时候，此时和前面的递归判断条件相同。当
     * 机器人的cur == 1的时候，说明在最左边，此时依赖dp[2][rest - 1]的值，也就是表格中dp[cur][rest]的左下角
     * 的值。当cur == N的时候，说明在最右边，此时依赖dp[N - 1][rest - 1]的值，也就是表格中dp[cur][rest]的左
     * 上角。当cur在中间的时候，依赖dp[N - 1][rest - 1]以及dp[N + 1][rest - 1]的值，也就是表格中dp[cur][rest]
     * 的左上角和右上角。通过这个规律，我们直接将dp表建立出来，不用递归。
     *
     * @param N   总的长度
     * @param aim 目标位置
     * @param ini 初始位置
     * @param K   走的步数
     */
    public static int robotWalkMethod3(int N, int aim, int ini, int K) {
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            // cur == 1 的时候，此时依赖左下角
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur + 1][rest - 1] + dp[cur - 1][rest - 1];
            }
            // cur == N的时候，此时依赖左上角
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[ini][K];
    }
}
