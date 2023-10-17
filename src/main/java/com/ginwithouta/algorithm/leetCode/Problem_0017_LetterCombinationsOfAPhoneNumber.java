package com.ginwithouta.algorithm.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package : com.ginwithouta.algorithm.leetCode.class01
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周三
 * @Desc :
 */
public class Problem_0017_LetterCombinationsOfAPhoneNumber {
    private static final char[][] LETTERS_ = {
            {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
            {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}
    };
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return null;
        }
        char[] path = new char[digits.length()];
        process(ans, path, 0, digits);
        return ans;
    }
    public static void process(List<String> ans, char[] path, int index, String digits) {
        if (index == digits.length()) {
            ans.add(String.valueOf(path));
            return ;
        }
        for (char cur : LETTERS_[digits.charAt(index) - '2']) {
            path[index] = cur;
            process(ans, path, index + 1, digits);
        }
    }
}
