package com.ginwithouta.algorithm.class06;

/**
 * @Package : com.ginwithouta.algorithm.class06
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周一
 * @Desc : 拷贝一个链表，这个链表中带有一个随机指针
 */
public class Code05_CopyLinkedListWithRandom {
    public static class Node {
        public int value;
        public Node next;
        public Node random;
        public Node(int value) {
            this.value = value;
            this.next = null;
            this.random = null;
        }
    }
    public static int generateRandomNumber(int range) {
        return ((int) (Math.random() * range)) - ((int) (Math.random() * range));
    }
    public static Node generateRandomLinkedListWithRandom(int maxLength, int range) {
        Node root = new Node(generateRandomNumber(range)), cur = root;
        int length = (int) (Math.random() * maxLength) + 2;
        Node[] linkedList = new Node[length + 1];
        linkedList[0] = root;
        for (int i = 1; i <= length; i++) {
            Node node = new Node(generateRandomNumber(range));
            linkedList[i] = node;
            cur.next = node;
            cur.random = linkedList[(int) (Math.random() * (i + 1))];
            cur = cur.next;
        }
        return root;
    }
    public static void printLinkedList(Node root) {
        Node cur = root;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println(" ");
    }
    public static void copyWithRandom(Node root) {
        if (root == null || root.next == null) {
            return ;
        }
        // 构建每个节点的复制节点，插在原来的链表上
        Node cur = root;
        while (cur != null) {
            Node node = new Node(cur.value);
            node.next = cur.next;
            cur.next = node;
            cur = node.next;
        }
        Node copy = root.next;
        cur = root;
        while (copy != null) {
            copy.random = cur.random.next;
            cur = copy.next;
            copy = cur == null ? null : cur.next;
        }
        cur = root.next;
        root.next = null;
        // Copy的头节点
        root = cur;
        while (cur.next != null) {
            copy = cur.next.next;
            cur.next.next = null;
            cur.next = copy;
            cur = copy;
        }
    }

}
