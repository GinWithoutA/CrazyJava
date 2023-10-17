package com.ginwithouta.algorithm.leetCode;

import javax.swing.text.AbstractDocument;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周日
 * @Desc :
 */
public class Problem_0044_WildcardMatching {
    public static boolean isMatch(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        return process(s, p, 0, 0);
    }

    /**
     * 尝试版本，暴力递归
     * @param str
     * @param pat
     * @param strIndex
     * @param patIndex
     * @return
     */
    public static boolean process(String str, String pat, int strIndex, int patIndex) {
        // 已经遍历完整个字符串了，只有当匹配串也遍历完了，或者匹配串剩余最后一个字符，并且该字符是*
        if (strIndex == str.length()) {
            if (patIndex == pat.length()) {
                return true;
            } else {
                return pat.charAt(patIndex) == '*' && process(str, pat, strIndex, patIndex + 1);
            }
        }
        // 已经遍历完整个匹配串了，但是字符串还没遍历完
        if (patIndex == pat.length()) {
            return false;
        }
        // 还没遍历完字符串，也还没遍历完匹配串
        if (pat.charAt(patIndex) == str.charAt(strIndex) || pat.charAt(patIndex) == '?') {
            return process(str, pat, strIndex + 1, patIndex + 1);
        }
        if (pat.charAt(patIndex) == '*') {
            // 当前匹配串的字符是*，并且后面还有其他的字符，那就一个一个试，看是否能够返回
            for (int i = strIndex; i <= str.length(); i++) {
                if (process(str, pat, i, patIndex + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isMatchDP(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // 最后一列，也就是p.length()的时候，只有strIndex == s.length()的时候才是true，其他的全是false
        dp[s.length()][p.length()] = true;
        for (int i = p.length() - 1; i >= 0; i--) {
            dp[s.length()][i] = p.charAt(i) == '*' && dp[s.length()][i + 1];
        }
        for (int strIndex = s.length() - 1; strIndex >= 0; strIndex--) {
            for (int patIndex = p.length() - 1; patIndex >= 0; patIndex--) {
                // 还没遍历完字符串，也还没遍历完匹配串
                if (p.charAt(patIndex) == s.charAt(strIndex) || p.charAt(patIndex) == '?') {
                    dp[strIndex][patIndex] = dp[strIndex + 1][patIndex + 1];
                    continue;
                }
                if (p.charAt(patIndex) == '*') {
                    // 当前匹配串的字符是*，并且后面还有其他的字符，那就一个一个试，看是否能够返回
                    for (int i = strIndex; i <= s.length(); i++) {
                        if (dp[i][patIndex + 1]) {
                            dp[strIndex][patIndex] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 斜率优化
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatchBest(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // 最后一列，也就是p.length()的时候，只有strIndex == s.length()的时候才是true，其他的全是false
        dp[s.length()][p.length()] = true;
        for (int i = p.length() - 1; i >= 0; i--) {
            dp[s.length()][i] = p.charAt(i) == '*' && dp[s.length()][i + 1];
        }
        for (int strIndex = s.length() - 1; strIndex >= 0; strIndex--) {
            for (int patIndex = p.length() - 1; patIndex >= 0; patIndex--) {
                // 还没遍历完字符串，也还没遍历完匹配串
                if (p.charAt(patIndex) == s.charAt(strIndex) || p.charAt(patIndex) == '?') {
                    dp[strIndex][patIndex] = dp[strIndex + 1][patIndex + 1];
                    continue;
                }
                if (p.charAt(patIndex) == '*') {
                    // 每个格子依赖当前下面的格子和右边的格子，只要有一个是true就行
                    dp[strIndex][patIndex] = dp[strIndex][patIndex + 1] || dp[strIndex + 1][patIndex];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba", "a*******b"));
    }
}
