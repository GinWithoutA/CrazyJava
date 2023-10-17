package com.ginwithouta.algorithm.class08;

/**
 * @Package : com.ginwithouta.algorithm.class08
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周三
 * @Desc :
 */
public class Code02_IsBalancedTree {
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
        public boolean isBalanced;
        public int height;
        public Info (int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }
    public static boolean isBalanced(Node root) {
        return process(root).isBalanced;
    }
    public static Info process(Node node) {
        if (node == null) {
            return new Info(0, true);
        }
        // 左子树找信息
        Info leftInfo = process(node.left);
        // 右子树找信息
        Info rightInfo = process(node.right);
        // 得到自己的高度
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        // 判断以自己为头的树是否是平衡树
        boolean flag = leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height - rightInfo.height) <= 1;
        return new Info(height, flag);
    }
}
