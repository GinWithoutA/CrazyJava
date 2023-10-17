package com.ginwithouta.algorithm.class08;

/**
 * @Package : com.ginwithouta.algorithm.class08
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周四
 * @Desc : 找到最近的公共祖先节点
 */
public class Code08_LowestAncestor {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    public static class Info {
        public boolean findA;
        public boolean findB;
        public Node ans;
        public Info(boolean findA, boolean findB, Node ans) {
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }
    public static Info process(Node node, Node a, Node b) {
        if (node == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(node.left, a, b), rightInfo = process(node.right, a, b);
        boolean findA = node == a || leftInfo.findA || rightInfo.findA;
        boolean findB = node == b || leftInfo.findB || rightInfo.findB;
        Node ans = null;
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else if (findA && findB) {
            ans = node;
        }
        return new Info(findA, findB, ans);
    }

}
