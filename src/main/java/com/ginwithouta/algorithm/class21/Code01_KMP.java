package com.ginwithouta.algorithm.class21;

/**
 * @Package : com.ginwithouta.algorithm.class21
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周三
 * @Desc : KMP算法
 */
public class Code01_KMP {
    public static int kMP(String s1, String s2) {
        if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty() || s1.length() < s2.length()) {
            return -1;
        }
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray();
        int[] next = getNext(c2);
        int index1 = 0, index2 = 0;
        while (index1 < c1.length && index2 < c2.length) {
            if (c1[index1] == c2[index2]) {
                index1++;
                index2++;
            } else {
                index2 = next[index2];
            }
        }
        return index2 == c2.length ? index1 - index2 : -1;
    }

    /**
     * 求最长前缀和后缀序列
     * @param c2
     * @return
     */
    public static int[] getNext(char[] c2) {
        int[] next = new int[c2.length];
        next[0] = -1;
        int cur = 0, index = 2;
        while (index < c2.length) {
            if (c2[index - 1] == c2[cur]) {
                next[index++] = ++cur;
            } else if (next[cur] > 0) {
                cur = next[cur];
            } else {
                next[index++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {

    }
}
