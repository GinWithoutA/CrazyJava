package com.ginwithouta.algorithm.class07;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Package : com.ginwithouta.algorithm.class07
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周二
 * @Desc : 找出最大的那一层有多少个节点
 */
public class Code04_MaxLevelCount {
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
    public static int maxLeverCount(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        Node curEnd = root, nextEnd = null;
        int max = Integer.MIN_VALUE, count = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            count++;
            if (node.left != null) {
                queue.add(node.left);
                nextEnd = node.left;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextEnd = node.right;
            }
            if (node == curEnd) {
                max = Math.max(max, count);
                curEnd = nextEnd;
                count = 0;
            }
        }
        return max;
    }
}
