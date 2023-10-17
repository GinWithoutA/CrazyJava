package com.ginwithouta.algorithm.class21;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Package : com.ginwithouta.algorithm.class21
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周四
 * @Desc : 判断一个树是否是一棵更大的树的子树
 */
public class Code03_IsContainSubTree {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Node {
        public Node left;
        public Node right;
        public int value;
    }
    public static ArrayList<String> preOreder(Node root) {
        if (root == null) {
            return null;
        }
        ArrayList<String> res = new ArrayList<>();
        res.add(String.valueOf(root.getValue()));
        ArrayList<String> left = preOreder(root.left);
        ArrayList<String> right = preOreder(root.right);
        if (left == null) {
            res.add("#");
        } else {
            res.addAll(left);
        }
        if (right == null) {
            res.add("#");
        } else {
            res.addAll(right);
        }
        return res;
    }
    public static boolean isContainSubTree(Node root, Node pattern) {
        if (root == null || pattern == null) {
            return false;
        }
        ArrayList<String> rootPattern = preOreder(root), patternPattern = preOreder(pattern);
        int[] next = getNext(patternPattern);
        int index1 = 0, index2 = 0;
        while (index1 < rootPattern.size() && index2 < patternPattern.size()) {
            if (rootPattern.get(index1).equals(patternPattern.get(index2))) {
                index1++;
                index2++;
            } else if (next[index2] > -1) {
                index2 = next[index2];
            } else {
                index1++;
            }
        }
        return index2 == patternPattern.size();
    }
    public static int[] getNext(ArrayList<String> pattern) {
        int[] next = new int[pattern.size()];
        next[0] = -1;
        next[1] = 0;
        int index = 2, cur = 0;
        while (index < pattern.size()) {
            if (Objects.equals(pattern.get(index - 1), pattern.get(cur))) {
                next[index++] = ++cur;
            } else if (next[cur] > 0) {
                cur = next[cur];
            } else {
                next[index++] = 0;
            }
        }
        return next;
    }
}
