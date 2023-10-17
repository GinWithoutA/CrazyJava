package com.ginwithouta.algorithm.class08;

/**
 * @Package : com.ginwithouta.algorithm.class08
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周三
 * @Desc :
 */
public class Code04_MaxDistance {
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
        public int maxDistance;
        public Info(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }
    public static int maxDistance(Node root) {
        return process(root).height;
    }
    public static Info process(Node node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = leftInfo.height + rightInfo.height + 1;
        // 有三种可能性，第一种是左树的最大距离就是整个的最大距离，第二种是幼鼠的最大距离就是最大，第三种是经过自己的最大距离才是最大
        int distance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance), leftInfo.maxDistance + rightInfo.maxDistance + 1);
        return new Info(height, distance);
    }
}
