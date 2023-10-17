package com.ginwithouta.algorithm.class08;

/**
 * @Package : com.ginwithouta.algorithm.class08
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周四
 * @Desc :
 */
public class Code07_MaxSubBSTRoot {
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
        public int max;
        public int min;
        public Node root;
        public int maxSubSize;
        public int size;
        public Info(int max, int min, Node root, int maxSubSize, int size) {
            this.max = max;
            this.min = min;
            this.root = root;
            this.maxSubSize = maxSubSize;
            this.size = size;
        }
    }
    public static Node maxSubSBT(Node root) {
        return process(root).root;
    }
    public static Info process(Node node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left), rightInfo = process(node.right);
        int max = node.value, min = node.value, maxSubSize = 0, size = 1;
        Node root = null;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            size += leftInfo.size;
            min = Math.min(leftInfo.min, min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            size += rightInfo.size;
            min = Math.min(rightInfo.min, min);
        }
        int p1 = -1;
        if (leftInfo != null) {
            p1 = leftInfo.maxSubSize;
        }
        int p2 = -1;
        if (rightInfo != null) {
            p2 = rightInfo.maxSubSize;
        }
        int p3 = -1;
        boolean leftBST = leftInfo == null || (leftInfo.maxSubSize == leftInfo.size);
        boolean rightBST = rightInfo == null || (rightInfo.maxSubSize == rightInfo.size);
        if (leftBST && rightBST) {
            boolean leftLessNode = leftInfo == null || (leftInfo.max <= node.value);
            boolean rightLessNode = rightInfo == null || (rightInfo.min >= node.value);
            if (leftLessNode && rightLessNode) {
                p3 = (leftInfo == null ? 0 : leftInfo.size) + (rightInfo == null ? 0 : rightInfo.size) + 1;
            }
        }
        if (p1 > p2 && p1 > p3) {
            maxSubSize = p1;
            root = leftInfo.root;
        } else if (p2 > p1 && p2 > p3) {
            maxSubSize = p2;
            root = rightInfo.root;
        } else if (p3 > p1 && p3 > p2) {
            maxSubSize = p3;
            root = node;
        }
        return new Info(max, min, root, maxSubSize, size);
    }
}
