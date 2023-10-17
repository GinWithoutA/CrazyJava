package com.ginwithouta.algorithm.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周三
 * @Desc : 括号匹配，这里只需要判断是否还有剩余的左括号就行，不用像上一道题多个括号是否一一匹配
 */
public class Problem_0022_GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        char[] path = new char[n << 1];
        process(ans, path, 0, n, 0);
        return ans;
    }
    public void process(List<String> ans, char[] path, int leftPair, int leftNum, int index) {
        if (index == path.length) {
            ans.add(String.valueOf(path));
        }
        if (leftNum > 0) {
            path[index] = '(';
            process(ans, path, leftPair + 1, leftNum - 1, index + 1);
        }
        if (leftPair > 0) {
            path[index] = ')';
            process(ans, path, leftPair - 1, leftNum, index + 1);
        }
    }
}
