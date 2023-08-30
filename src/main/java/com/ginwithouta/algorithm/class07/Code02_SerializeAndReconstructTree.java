package com.ginwithouta.algorithm.class07;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Package : com.ginwithouta.algorithm.class07
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周二
 * @Desc : 树的序列化以及反序列化（只有前序和后序，中序会产生歧义）
 */
public class Code02_SerializeAndReconstructTree {
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
    public static Queue<String> preSerial(Node root) {
        if (root == null) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        pres(root, queue);
        return queue;
    }
    public static void pres(Node node, Queue<String> queue) {
        if (node == null) {
            queue.add(null);
        } else {
            queue.add(String.valueOf(node.value));
            pres(node.left, queue);
            pres(node.right, queue);
        }

    }
    public static Node preBuild(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        return preb(queue);
    }
    public static Node preb(Queue<String> queue) {
        String value = queue.poll();
        if (value == null) {
            return null;
        }
        Node node = new Node(Integer.parseInt(value));
        node.left = preb(queue);
        node.right = preb(queue);
        return node;
    }
    public static Queue<String> levelSerial(Node root) {
        if (root == null) {
            return null;
        }
        // 存放序列化字符的队列
        Queue<String> result = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        result.add(String.valueOf(root.value));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left != null) {
                // 放入的时候进行序列化
                result.add(String.valueOf(node.left.value));
                queue.add(node.left);
            } else {
                result.add(null);
            }
            if (node.right != null) {
                result.add(String.valueOf(node.right.value));
                queue.add(node.right);
            } else {
                result.add(null);
            }
        }
        return result;
    }
    public static Node generateNode(String value) {
        if (value == null) {
            return null;
        }
        return new Node(Integer.parseInt(value));
    }
    public static Node levelb(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        Queue<Node> nodeQueue = new LinkedList<>();
        Node root = generateNode(queue.poll());
        if (root == null) {
            return null;
        }
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            Node node = nodeQueue.poll();
            node.left = generateNode(queue.poll());
            node.right = generateNode(queue.poll());
            if (node.left != null) {
                nodeQueue.add(node.left);
            }
            if (node.right != null) {
                nodeQueue.add(node.right);
            }
        }
        return root;
    }
}
