package com.ginwithouta.algorithm.leetCode;

import java.nio.channels.MembershipKey;
import java.util.Stack;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周三
 * @Desc :
 */
public class Problem_0020_ValidParentheses {
    public static boolean isValid(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    char top = stack.pop(), cur = s.charAt(i);
                    if ((top == '(' && cur != ')') || (top == '[' && cur != ']') || (top == '{' && cur != '}')) {
                        return false;
                    }
                }
            }
        }
        return !stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("()"));
    }
}
