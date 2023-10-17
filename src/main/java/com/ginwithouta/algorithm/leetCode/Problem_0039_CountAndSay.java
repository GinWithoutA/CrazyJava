package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周五
 * @Desc :
 */
public class Problem_0039_CountAndSay {
    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String preStr = countAndSay(n - 1);
        StringBuilder ans = new StringBuilder();
        if (preStr.length() == 1) {
            ans.append(1).append(preStr.charAt(0));
            return ans.toString();
        }
        int count = 1;
        for (int i = 1; i < preStr.length(); i++) {
            if (preStr.charAt(i - 1) == preStr.charAt(i)) {
                if (i == preStr.length() - 1) {
                    ans.append(count + 1).append(preStr.charAt(i));
                } else {
                    count++;
                }
            } else {
                ans.append(count).append(preStr.charAt(i - 1));
                count = 1;
                if (i == preStr.length() - 1) {
                    ans.append(count).append(preStr.charAt(i));
                }
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(4));
    }
}
