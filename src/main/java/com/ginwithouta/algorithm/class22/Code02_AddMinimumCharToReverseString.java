package com.ginwithouta.algorithm.class22;

/**
 * @Package : com.ginwithouta.algorithm.class22
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周四
 * @Desc : 给定一个字符串，要求只能在后面添加字符，问需要添加多少个字符，能够使得一整个字符串变成一个回文字符串
 * @method : Manacher的改进，包含最后一个字符的最长字串剩余的串全部逆序添加到后面，就是我们要求的需要添加多少个字符
 */
public class Code02_AddMinimumCharToReverseString {
    public static int minimumChar(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        String temp = help(str);
        int R = -1, C = -1, res = 0;
        int[] radius = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++) {
            radius[i] = R > i ? Math.min(radius[2 * C - i], R - i) : 1;
            while (i + radius[i] < temp.length() && i - radius[i] > -1) {
                if (temp.charAt(i + radius[i]) == temp.charAt(i - radius[i])) {
                    radius[i]++;
                    if (i + radius[i] == temp.length()) {
                        res = (i - radius[i]) / 2;
                    } else {
                        break;
                    }
                }
            }
            if (i + radius[i] > R) {
                R = i + radius[i];
                C = i;
            }
        }
        return res;
    }
    public static String help(String str) {
        StringBuilder builder = new StringBuilder();
        builder.append('#');
        for (int i = 0; i < str.length(); i++) {
            builder.append(str.charAt(i)).append('#');
        }
        return builder.toString();
    }
}
