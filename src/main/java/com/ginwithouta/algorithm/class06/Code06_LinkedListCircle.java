package com.ginwithouta.algorithm.class06;


/**
 * @Package : com.ginwithouta.algorithm.class06
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周一
 * @Desc : 判断单链表中是否有环，如果有，返回入环节点，如果没有，返回null
 */
public class Code06_LinkedListCircle {
    public static class Node {
        public int value;
        public Node next;
        public Node (int value) {
            this.value = value;
            this.next = null;
        }
    }
    public static int generateRandomNumber(int range) {
        return ((int) (Math.random() * range)) - ((int) (Math.random() * range));
    }
    public static Node[] generateRandmomLinkedList(int maxLength, int range) {
        int length = (int) (Math.random() * maxLength);
        Node root = new Node(generateRandomNumber(range)), cur = root;
        Node[] nodes = new Node[length + 1];
        nodes[0] = root;
        for (int i = 1; i <= length; i++) {
            Node node = new Node(generateRandomNumber(range));
            nodes[i] = node;
            cur.next = node;
            cur = node;
        }
        return nodes;
    }
    public static int generateRandomLoop(Node[] nodes) {
        if (Math.random() > 0.5) {
            int index = (int) (Math.random() * nodes.length);
            nodes[nodes.length - 1].next = nodes[index];
            return index;
        } else {
            return -1;
        }
    }
    public static Node findLoop(Node root) {
        if (root == null || root.next == null) {
            return null;
        }
        Node slow = root, fast = root.next.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        // 这时候肯定碰到了
        fast = root;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    public static void main(String[] args) {
        int maxLength = 10, range = 100;
        Node[] nodes = generateRandmomLinkedList(maxLength, range);
        int enter = generateRandomLoop(nodes);
        Node result = findLoop(nodes[0]);
        System.out.println(enter == -1 ? (result == null) : (result == nodes[enter]));
    }
}
