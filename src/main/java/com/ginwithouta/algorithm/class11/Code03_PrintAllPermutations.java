package com.ginwithouta.algorithm.class11;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Package : com.ginwithouta.algorithm.class11
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc :
 */
public class Code03_PrintAllPermutations {
    public static void main(String[] args) {
        char[] str = "hello".toCharArray();
        ArrayList<String> ans = new ArrayList<>();
        process(str, 0, ans);
        System.out.println(ans);
        boolean[] visited = new boolean[256];
    }

    public static void swap(int index1, int index2, char[] str) {
        char temp = str[index1];
        str[index1] = str[index2];
        str[index2] = temp;
    }

    public static void process(char[] str, int index, ArrayList<String> ans) {
        if (index == str.length) {
            ans.add(Arrays.toString(str));
            return;
        }
        // i是用来交换的，不是用来传递下一个递归的
        for (int i = index; i < str.length; i++) {
            swap(index, i, str);
            process(str, index + 1, ans);
            swap(index, i, str);
        }
    }

    public static void processNoRepeat(char[] str, int index, ArrayList<String> ans, boolean[] visited) {
        if (index == str.length) {
            ans.add(Arrays.toString(str));
            return;
        }
        // 交换头部的时候，只有当前要交换的位置没有坐过头部才交换
        for (int i = index; i < str.length; i++) {
            if (!visited[str[i]]) {
                visited[str[i]] = true;
                swap(index, i, str);
                process(str, index + 1, ans);
                swap(index, i, str);
            }
        }
    }
}
