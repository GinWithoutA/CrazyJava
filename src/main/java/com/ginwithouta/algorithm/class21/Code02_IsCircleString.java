package com.ginwithouta.algorithm.class21;

/**
 * @Package : com.ginwithouta.algorithm.class21
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周四
 * @Desc : 判断一个字符串是否是另一个字符串的旋转字符串
 *
 */
public class Code02_IsCircleString {
    public static boolean isCircleString(String str, String pattern) {
        if (str == null || pattern == null || str.isEmpty() || pattern.isEmpty() || str.length() != pattern.length()) {
            return false;
        }
        String temp = str.concat(str);
        int[] next = getNext(pattern);
        int index1 = 0, index2 = 0;
        while (index1 < temp.length() && index2 < pattern.length()) {
            if (temp.charAt(index1) == pattern.charAt(index2)) {
                index1++;
                index2++;
            } else if (next[index2] == -1) {
                // index == 0的时候
                index1++;
            } else {
                index2 = next[index2];
            }
        }
        return index2 == pattern.length();
    }
    public static int[] getNext(String str) {
        int[] next = new int[str.length()];
        next[0] = -1;
        next[1] = 0;
        int cur = 0;
        for (int i = 2; i < str.length();) {
            if (str.charAt(i - 1) == str.charAt(cur)) {
                next[i++] = ++cur;
            } else if (next[cur] > 0) {
                cur = next[cur];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str = "abc";
        System.out.println(str.concat(str));
    }
}
