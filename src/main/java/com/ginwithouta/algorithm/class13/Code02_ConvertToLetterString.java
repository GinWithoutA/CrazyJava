package com.ginwithouta.algorithm.class13;

/**
 * @Package : com.ginwithouta.algorithm.class13
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周三
 * @Desc : 给定一个数字字符串，1对应A，以此类推，问有多少种转法
 */
public class Code02_ConvertToLetterString {
    public static int convertToLetterString(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        return process(0, str);
    }
    public static int process(int index, String str) {
        // baseCase 当遍历到最后一个位置，表明前面的都改成功了，返回1
        if (index == str.length()) {
            return 1;
        }
        if ('0' == str.charAt(index)) {
            return 0;
        }
        int ways = process(index + 1, str);
        ways += index + 1 < str.length() && (str.charAt(index) - '0') * 10 + (str.charAt(index + 1) - '0') < 27 ? process(index + 2, str) : 0;
        return ways;
    }
    public static int converToLetterStringDP(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        int[] dp = new int[str.length() + 1];
        // baseCase
        dp[str.length()] = 1;
        // 每一位依赖右边一位的状态，所以从右往左遍历
        for (int index = str.length() - 1; index >= 0; index--) {
            if (str.charAt(index) != '0') {
                int ways = dp[index + 1];
                // 还是一样判断是否小于 str.length()，因为dp的边界是str.length()，实际上取不到这个值
                ways += index + 1 < str.length() && (str.charAt(index) - '0') * 10 + (str.charAt(index + 1) - '0') < 27 ? dp[index + 2] : 0;
                dp[index] = ways;
            }
        }
        return dp[0];
    }
}
