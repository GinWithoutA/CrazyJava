package com.ginwithouta.algorithm.class08;

/**
 * @Package : com.ginwithouta.algorithm.class08
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周三
 * @Desc :
 */
public class Code06_MaxSubBSTSize {
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
        public int maxSubBSTSize;
        public int size;
        public int max;
        public int min;
        public Info (int maxSubBSTSize, int size, int max, int min) {
            this.max = max;
            this.min = min;
            this.maxSubBSTSize = maxSubBSTSize;
            this.size = size;
        }
    }
    public static int maxSubBSTSize(Node root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxSubBSTSize;
    }
    public static Info process(Node node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left), rightInfo = process(node.right);
        int max = node.value, min = node.value, size = 1;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            size += leftInfo.size;
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            size += rightInfo.size;
        }
        // 如果子树经过node节点
        //      判断本身是不是BST
        // 如果子树不经过node节点
        //      左子树的最大和右子树的最大pk
        int p1 = -1;
        if (leftInfo != null) {
            p1 = leftInfo.maxSubBSTSize;
        }
        int p2 = -1;
        if (rightInfo != null) {
            p2 = rightInfo.maxSubBSTSize;
        }
        int p3 = getP3(node, leftInfo, rightInfo);
        return new Info(Math.max(Math.max(p1, p2), p3), size, max, min);
    }

    private static int getP3(Node node, Info leftInfo, Info rightInfo) {
        int p3 = 1;
        boolean leftBST = leftInfo == null || (leftInfo.maxSubBSTSize == leftInfo.size);
        boolean rightBST = rightInfo == null || (rightInfo.maxSubBSTSize == rightInfo.size);
        if (leftBST && rightBST) {
            boolean leftLessNode = leftInfo == null || (leftInfo.max <= node.value);
            boolean rightLessNode = rightInfo == null || (rightInfo.min >= node.value);
            if (leftLessNode && rightLessNode) {
                p3 += (leftInfo == null ? 0 : leftInfo.size) + (rightInfo == null ? 0 : rightInfo.size);
            }
        }
        return p3;
    }
}
