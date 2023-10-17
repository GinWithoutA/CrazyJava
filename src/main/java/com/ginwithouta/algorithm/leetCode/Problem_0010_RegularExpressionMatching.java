package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode.class01
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周二
 * @Desc :
 */
public class Problem_0010_RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null || !isValid(s, p)) {
            return false;
        }
        return process(s, p, 0, 0);
    }

    /**
     * 返回当前位置的匹配串能否匹配出从当前位置开始的所有后续字符
     * 这里的baseCase有两个，一个是strIndex到末尾了，那么需要判断匹配串是否能够匹配。另一种是patIndex到末尾了，需要判断当前strIndex在哪个位置
     * 潜台词：请不要让process独自面对'*'
     * @param str       字符串
     * @param pattern   匹配串
     * @param strIndex  字符串当前位置
     * @param patIndex  匹配串当前位置
     * @return
     */
    public boolean process(String str, String pattern, int strIndex, int patIndex) {
        /*
         * BaseCase1:
         *      1. 如果patIndex也到了末尾，那么就是空字符串匹配空字符串，返回true
         *      2. 如果patIndex不在末尾，判断patIndex + 1是不是超过了pattern的边界
         *              a. 如果是，则说明当前字符串已经没有多余的字符再进行匹配了，返回false
         *              b. 如果不是，判断下一个字符是否是'*'，如果是跳过当前两个字符，之后进行匹配，如果不是，直接返回false
         */
        if (strIndex == str.length()) {
            if (patIndex == pattern.length()) {
                return true;
            } else {
                if (patIndex + 1 < pattern.length() && pattern.charAt(patIndex + 1) == '*') {
                    return process(str, pattern, strIndex, patIndex + 2);
                }
                return false;
            }
        }
        /*
         * 如果patIndex没有到末尾，那么此时由于strIndex没有到末尾，没有字符可以进行匹配了，直接返回false
         */
        if (patIndex == pattern.length()) {
            return false;
        }
        /*
         * 一般位置，strIndex和patIndex都不在末尾，考虑两种情况：
         *      1） 当前patIndex的下一个字符不是'*'，判断当前字符是否能够匹配，如果不能返回false，如果能返回后续匹配的结果
         *      2） 当前patIndex的下一个字符是'*'
         *          a. 如果当前字符和pattern的当前字符不能匹配，跳两个字符然后向下匹配
         *          b. 如果当前字符和pattern的当前字符能匹配，那么我们需要让这个'*'以及前面的字符从变成空字符开始，到一个字符，到两个字符，一直往后。
         */
        if (patIndex + 1 >= pattern.length() || pattern.charAt(patIndex + 1) != '*') {
            return (str.charAt(strIndex) == pattern.charAt(patIndex) || pattern.charAt(patIndex) == '.') && process(str, pattern, strIndex + 1, patIndex + 1);
        }
        if (pattern.charAt(patIndex) != '.' && str.charAt(strIndex) != pattern.charAt(patIndex)) {
            return process(str, pattern, strIndex, patIndex + 2);
        }
        if (process(str, pattern, strIndex, patIndex + 2)) {
            return true;
        }
        while (strIndex < str.length() && (str.charAt(strIndex) == pattern.charAt(patIndex) || pattern.charAt(patIndex) == '.')) {
            if (process(str, pattern, strIndex + 1, patIndex + 2)) {
                return true;
            }
            strIndex++;
        }
        return false;
    }

    /**
     * 字符串合格性校验，str不能有'.'和'*'，pattern中不能有连续的'*'并且开头不能是'*'
     * @param str
     * @return
     */
    public boolean isValid(String str, String pattern) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.' || str.charAt(i) == '*') {
                return false;
            }
        }
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '*' && (i == 0 || pattern.charAt(i - 1) == '*')) {
                return false;
            }
        }
        return true;
    }

    public boolean isMatch2(String s, String p) {
        if (s == null || p == null || !isValid(s, p)) {
            return false;
        }
        int[][] dp = new int[s.length() + 1][p.length() + 1];
        // 傻缓存，-1表示还没有考察过，0表示考察过，但是失败了，1表示考察过并且成功了
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= p.length(); j++) {
                dp[i][j] = -1;
            }
        }
        return process2(s, p, 0, 0, dp);
    }

    /**
     * 傻缓存法
     * @param str       字符串
     * @param pattern   匹配串
     * @param strIndex  字符串当前位置
     * @param patIndex  匹配串当前位置
     * @return
     */
    public boolean process2(String str, String pattern, int strIndex, int patIndex, int[][] dp) {
        if (dp[strIndex][patIndex] != -1) {
            return dp[strIndex][patIndex] == 1;
        }
        if (strIndex == str.length()) {
            if (patIndex == pattern.length()) {
                dp[strIndex][patIndex] = 1;
                return true;
            } else {
                if (patIndex + 1 < pattern.length() && pattern.charAt(patIndex + 1) == '*') {
                    dp[strIndex][patIndex] = process2(str, pattern, strIndex, patIndex + 2, dp) ? 1 : 0;
                } else {
                    dp[strIndex][patIndex] = 0;
                }
                return dp[strIndex][patIndex] == 1;
            }
        }
        if (patIndex == pattern.length()) {
            dp[strIndex][patIndex] = 0;
            return false;
        }
        if (patIndex + 1 >= pattern.length() || pattern.charAt(patIndex + 1) != '*') {
            dp[strIndex][patIndex] = (str.charAt(strIndex) == pattern.charAt(patIndex) || pattern.charAt(patIndex) == '.') && process2(str, pattern, strIndex + 1, patIndex + 1, dp) ? 1 : 0;
            return dp[strIndex][patIndex] == 1;
        }
        if (pattern.charAt(patIndex) != '.' && str.charAt(strIndex) != pattern.charAt(patIndex)) {
            dp[strIndex][patIndex] = process2(str, pattern, strIndex, patIndex + 2, dp) ? 1 : 0;
            return dp[strIndex][patIndex] == 1;
        }
        if (process2(str, pattern, strIndex, patIndex + 2, dp)) {
            dp[strIndex][patIndex] = 1;
            return true;
        }
        while (strIndex < str.length() && (str.charAt(strIndex) == pattern.charAt(patIndex) || pattern.charAt(patIndex) == '.')) {
            if (process2(str, pattern, strIndex + 1, patIndex + 2, dp)) {
                dp[strIndex][patIndex] = 1;
                return true;
            }
            strIndex++;
        }
        dp[strIndex][patIndex] = 0;
        return false;
    }
}
