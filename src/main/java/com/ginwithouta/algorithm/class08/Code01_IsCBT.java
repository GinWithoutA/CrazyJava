package com.ginwithouta.algorithm.class08;


import com.ginwithouta.algorithm.class02.Code03_DoubleEndsQueueToStackAndQueue;

import javax.swing.plaf.synth.SynthInternalFrameUI;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Package : com.ginwithouta.algorithm.class08
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周三
 * @Desc :
 */
public class Code01_IsCBT {
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
    public static boolean isCBT(Node root) {
        if (root == null) {
            return false;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left == null && node.right != null) {
                return false;
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return true;
    }
    public static class Info {
        public int height;
        public boolean isCBT;
        public boolean isFull;
        public Info(int height, boolean isCBT, boolean isFull) {
            this.height = height;
            this.isCBT = isCBT;
            this.isFull = isFull;
        }
    }
    public static boolean isCBTRecursion(Node root) {
        return process(root).isCBT;
    }
    public static Info process(Node node) {
        if (node == null) {
            return new Info(0, true, true);
        }
        Info leftInfo = process(node.left), rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isCBT = isFull;
        if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
            isCBT = true;
        } else if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
            isCBT = true;
        } else if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
            isCBT = true;
        }
        return new Info(height, isCBT, isFull);
    }
}
