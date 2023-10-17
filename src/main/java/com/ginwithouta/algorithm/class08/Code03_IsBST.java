package com.ginwithouta.algorithm.class08;

import java.nio.file.FileAlreadyExistsException;

/**
 * @Package : com.ginwithouta.algorithm.class08
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周三
 * @Desc : 判断一颗树是否是搜索二叉树
 */
public class Code03_IsBST {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node (int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    public static class Info {
        public int max;
        public int min;
        public boolean isBST;
        public Info(int max, int min, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }
    }
    public static boolean isBST(Node root) {
        return process(root).isBST;
    }
    public static Info process(Node node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int max = node.value;
        // 以当前节点为树的根，找到整颗树中的最大值
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
        }
        int min = node.value;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
        }
        boolean flag = (leftInfo == null || leftInfo.isBST) && (rightInfo == null || rightInfo.isBST);
        if (leftInfo != null && leftInfo.max > node.value) {
            flag = false;
        }
        if (rightInfo != null && rightInfo.min < node.value) {
            flag = false;
        }
        return new Info(max, min, flag);
    }
}
