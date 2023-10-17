package com.ginwithouta.algorithm.class08;

/**
 * @Package : com.ginwithouta.algorithm.class08
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周三
 * @Desc :
 */
public class Code05_IsFull {
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
        public int height;
        public int nodeCount;
        public Info(int height, int nodeCount) {
            this.height = height;
            this.nodeCount = nodeCount;
        }
    }
    public static boolean isFull(Node root) {
        Info info = process(root);
        return Math.pow(2, info.height) - 1 == info.nodeCount;
    }
    public static Info process(Node node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodeCount = leftInfo.nodeCount + rightInfo.nodeCount + 1;
        return new Info(height, nodeCount);
    }
}
