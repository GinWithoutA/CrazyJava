package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode.class01
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周一
 * @Desc :
 */
public class Problem_0003LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int pre = -1, cur, len = 0;
        for (int i = 0; i < s.length(); i++) {
            pre = Math.max(pre, map[s.charAt(i)]);
            cur = i - pre;
            len = Math.max(len, cur);
            map[s.charAt(i)] = i;
        }
        return len;
    }
}
