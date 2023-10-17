package com.ginwithouta.algorithm.class11;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @Package : com.ginwithouta.algorithm.class11
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc :
 */
public class Code02_PrintAllSubSequence {
    public static void main(String[] args) {
        ArrayList<String> ans = new ArrayList<>();
        processSubSequence(0, "hello", "", ans);
        System.out.println(ans);
    }

    public static void processSubSequence(int index, String str, String path, ArrayList<String> ans) {
        if (index == str.length()) {
            ans.add(path);
            return;
        }
        processSubSequence(index + 1, str, path, ans);
        processSubSequence(index + 1, str, path + str.charAt(index), ans);
    }

    public static void processSubSequenceNoRepeat(int index, String str, String path, HashSet<String> set) {
        if (index == str.length()) {
            set.add(path);
            return;
        }
        processSubSequenceNoRepeat(index + 1, str, path, set);
        processSubSequenceNoRepeat(index + 1, str, path + str.charAt(index), set);
    }
}
