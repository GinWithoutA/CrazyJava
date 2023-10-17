package com.ginwithouta.algorithm.class14;

/**
 * @Package : com.ginwithouta.algorithm.class14
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周四
 * @Desc : 最长回文子序列
 */
public class Code01_LongestPalindromicSubsequence {
    public static int longestPalindromeSubseq(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        char[] str = s.toCharArray();
        return processViolentRecursion(str, 0, str.length - 1);
    }

    /**
     * 暴力递归，超时
     * @param str
     * @param left
     * @param right
     * @return
     */
    public static int processViolentRecursion(char[] str, int left, int right) {
        // baseCase
        if (left == right) {
            return 1;
        }
        // 子序列长度为2，判断左右一个字符是否相等，如果不相等，那么时最长回文就是1，相等时最长回文就是2
        // left不可能超过right
        if (left + 1 == right) {
            return str[left] == str[right] ? 2 : 1;
        }
        // 4种一般情况
        // 左右都不是回文序列的边
        int p1 = processViolentRecursion(str, left + 1, right - 1);
        // 左边是边，右边不是边
        int p2 = processViolentRecursion(str, left, right - 1);
        // 左边不是边，右边是边
        int p3 = processViolentRecursion(str, left + 1, right);
        // 左右都是边，此时只有左右都相等，才有可能有这种情况
        int p4 = str[left] == str[right] ? 2 + p1 : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    /**
     * 动态规划，变化的变量是Left和Right，用一个二维数组可以搞定
     * @param s
     * @return
     */
    public static int longestPalindromeSubseqDP(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        char[] str = s.toCharArray();
        // L...[0, N - 1], R...[0, N - 1]
        int[][] dp = new int[str.length][str.length];
        dp[dp.length - 1][dp.length - 1] = 1;
        // 根据前面的暴力递归，可以把对角线和对角线右边第一条对角线的值全部填完
        for (int i = 0; i < dp.length - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int left = dp.length - 2; left >= 0; left--) {
            for (int right = left + 2; right < dp.length; right++) {
                int p1 = dp[left + 1][right - 1];
                int p2 = dp[left][right - 1];
                int p3 = dp[left + 1][right];
                int p4 = str[left] == str[right] ? 2 + p1 : 0;
                dp[left][right] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dp[0][dp.length - 1];
    }

    /**
     * 优化后的动态规划，可以发现第一种情况依赖的是左下的格子，但是这个格子会被左边的格子比较，又会被下边的格子比较
     * 他们俩比较的时候只会返回最大值，因此这两个格子的情况一定大于等于左下这个格子的情况，因此可以将第一种可能性排除
     * @param s
     * @return
     */
    public static int longestPalindromeSubseqDPBetter(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        char[] str = s.toCharArray();
        // L...[0, N - 1], R...[0, N - 1]
        int[][] dp = new int[str.length][str.length];
        dp[dp.length - 1][dp.length - 1] = 1;
        // 根据前面的暴力递归，可以把对角线和对角线右边第一条对角线的值全部填完
        for (int i = 0; i < dp.length - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int left = dp.length - 2; left >= 0; left--) {
            for (int right = left + 2; right < dp.length; right++) {
                // 直接判断左边和下边的格子哪个大
                dp[left][right] = Math.max(dp[left + 1][right], dp[left][right - 1]);
                // 判断有没有第四种可能，如果有，需要将左下的格子加2再进行比较
                if (str[left] == str[right]) {
                    dp[left][right] = Math.max(dp[left][right], dp[left + 1][right - 1] + 2);
                }
            }
        }
        return dp[0][dp.length - 1];
    }
}
