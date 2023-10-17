package com.ginwithouta.algorithm.class13;

/**
 * @Package : com.ginwithouta.algorithm.class13
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周三
 * @Desc : 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 */
public class Code04_LongestCommonSubsequence {
    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }
        char[] char1 = text1.toCharArray(), char2 = text2.toCharArray();
        return process1(char1, char2, text1.length() - 1, text2.length() - 1);
    }

    /**
     *
     * @param text1
     * @param text2
     * @param i         text1的当前字符位置
     * @param j         text2的当前字符位置
     * @return
     */
    public static int process1(char[] text1, char[] text2, int i, int j) {
        if (i == 0 && j == 0) {
            return 0;
        }
        // 如果当前已经到了text1的最左边位置
        if (i == 0) {
            if (text1[i] == text2[j]) {
                return 1;
            } else {
                return process1(text1, text2, i, j - 1);
            }
        } else if (j == 0) {
        // 如果当前已经到了text2的最左边位置
            if (text1[i] == text2[j]) {
                return 1;
            } else {
                return process1(text1, text2, i - 1, j);
            }
        } else {
            // 如果当前的i和j都不是0
            // 不考虑i，只考虑j是不是尾巴
            int p1 = process1(text1, text2, i, j - 1);
            int p2 = process1(text1, text2, i - 1, j);
            int p3 = process1(text1, text2, i - 1, j - 1);
            return Math.max(Math.max(p1, p2), p3);
        }
    }

    /**
     * 最长公共子序列的DP方法，text1和text2的长度可以当作DP的依据，目标是dp[N - 1][M - 1]
     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequenceDP(String text1, String text2) {
        if (text1 == null || text2 == null || text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }
        int[][] dp = new int[text1.length()][text2.length()];
        char[] char1 = text1.toCharArray(), char2 = text2.toCharArray();
        for (int j = 1; j < dp[0].length; j++) {
            if (char1[0] == char2[j]) {
                dp[0][j] = 1;
            } else {
               dp[0][j] = dp[0][j - 1];
            }
        }
        for (int i = 1; i < dp.length; i++) {
            if (char1[i] == char2[0]) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int i = 1; i < char1.length; i++) {
            for (int j = 1; j < char2.length; j++) {
                int p1 = dp[i][j - 1];
                int p2 = dp[i - 1][j];
                int p3 = dp[i - 1][j - 1];
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[char1.length - 1][char2.length - 1];
    }
}
