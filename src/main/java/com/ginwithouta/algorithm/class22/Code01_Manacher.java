package com.ginwithouta.algorithm.class22;

/**
 * @Package : com.ginwithouta.algorithm.class22
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周四
 * @Desc : Manacher算法，用于求最长回文字串，对于Manacher算法，我们需要几个前置变量：
 *          1. 当前位置的字符能够扩充到的最右侧区域的边界R，
 *          2. 当前的最右边界R是由哪一个位置的字符C更新出来的
 *          3. 每个位置的字符能够阔出来的回文串半径
 *          4. 每个位置的字符能够阔出来的回文串直径
 *         具体的操作可以判断两种情况
 *          1. 如果当前的R没有包裹住当前的位置i：暴力求解，没有优化方式
 *          2. 如果当前的R包裹住了当前的位置i，那么考虑i相对于R所带来的C的位置的对称位置i'为中心
 *              1. 如果阔出来的回文串的左边界大于R的对称位置L：i能阔的长度和i'一样（被整个LR包裹住，因此二者对称，不可能超出i'为中心的回文串范围，如果超出，则i'也应该是这么长）
 *              2. 如果阔出来的回文串的左边界小于R的对称位置L：i能阔的长度就是R-i（二者对称，如果相等，则R就应该在更右边的位置，而不是当前更小的位置）
 *              3. 如果阔出来的回文串的左边界等于R的对称位置L：i能阔的长度需要从R开始判断是否能阔（有可能更长，不好说）
 */
public class Code01_Manacher {
    public static int manacher(String s) {
        if (s == null || s.isEmpty()) {
            return -1;
        }
        // 处理过后的字符串
        String temp = helper(s);
        // 回文半径数组
        int[] radius = new int[temp.length()];
        // R用于存放当前第一个不匹配的字符的位置，C表示当前的R是由哪一个位置的字符所产生的
        int R = -1, C = -1, res = Integer.MIN_VALUE;
        for (int i = 0; i < temp.length(); i++) {
            // i位置的回文半径长度
            // 判断R是否包裹住i，如果没有，直接附一个初始值1，表明回文长度至少是1
            // 如果被R包裹住，也付一个初始长度，看是对称位置的半径小还是R-i的半径小
            radius[i] = R > i ? Math.min(radius[2 * C - i], R - i) : 1;
            // 不管怎样，直接判断左右是否可以扩容，省的写额外的if判断（即判断当前属于哪一种情况）
            while (i + radius[i] < temp.length() && i - radius[i] > -1) {
                if (temp.charAt(i + radius[i]) == temp.charAt(i - radius[i])) {
                    radius[i]++;
                } else {
                    // 不相等，直接暂停比较
                    break;
                }
            }
            // 判断当前的R是否被更新了，如果更新了，更新R的值以及C的值
            if (i + radius[i]> R) {
                R = i + radius[i];
                C = i;
            }
            res = Math.max(res, radius[i] - 1);
        }
        return res;
    }
    public static String helper(String s) {
        StringBuilder builder = new StringBuilder();
        builder.append('#');
        for (int i = 0; i < s.length(); i++) {
            builder.append(s.charAt(i)).append('#');
        }
        return builder.toString();
    }

    public static String violent(String s) {
        String str = helper(s);
        int left, right, len = 0, cur, curLeft = 0, curRight = 0;
        for (int i = 0; i < str.length(); i++) {
            left = i - 1;
            right = i + 1;
            cur = 1;
            while (left >= 0 && right < str.length()) {
                if (str.charAt(left) == str.charAt(right)) {
                    cur += 2;
                    left--;
                    right++;
                } else if (cur > len) {
                    len = cur;
                    curLeft = (left >> 1) + 1;
                    curRight = (right >> 1) - 1;
                    break;
                }
            }
        }
        return s.substring(curLeft, curRight + 1);
    }

    public static void main(String[] args) {
        // String s = "ababa";
        // System.out.println(manacher(s));
        System.out.println("a".substring(0, 1));
    }
}
