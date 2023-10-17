package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode.class01
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周二
 * @Desc :
 */
public class Problem_0014_LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int min = Integer.MAX_VALUE;
        for (String str : strs) {
            int index = 0;
            while (index < strs[0].length() && index < str.length()) {
                if (strs[0].charAt(index) != str.charAt(index)) {
                    break;
                }
                index++;
            }
            min = Math.min(min, index);
        }
        if (min == 0) {
            return "";
        }
        return strs[0].substring(0, min);
    }
}
