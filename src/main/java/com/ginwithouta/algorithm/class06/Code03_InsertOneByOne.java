package com.ginwithouta.algorithm.class06;

import lombok.val;

/**
 * @Package : com.ginwithouta.algorithm.class06
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周日
 * @Desc :
 */
public class Code03_InsertOneByOne {
    public static class Node {
        public int value;
        public Node next;
        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }
    public static int generateRandomNumber(int range) {
        return ((int) (Math.random() * range)) - ((int) (Math.random() * range));
    }
    public static Node generateRandomLinkedList(int maxLength, int range) {
        Node root = new Node(generateRandomNumber(range)), cur = root;
        int length = (int) (Math.random() * maxLength) + 2;
        for (int i = 0; i < length; i++) {
            cur.next = new Node(generateRandomNumber(range));
            cur = cur.next;
        }
        return root;
    }
    public static void insertOneByOne(Node root) {
        if (root == null || root.next == null || root.next.next == null) {
            return;
        }
        Node slow = root, fast = root.next;
        // 找到中点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node right = slow.next;
        slow.next = null;
        slow = root;
        while (right != null) {
            fast = right;
            right = right.next;
            fast.next = slow.next;
            slow.next = fast;
            slow = fast.next;
        }
    }
    public static void printLinkedList(Node root) {
        Node cur = root;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println(" ");
    }
    public static void main(String[] args) {
        int maxLength = 10, range = 100;
        Node root = generateRandomLinkedList(maxLength, range);
        printLinkedList(root);
        insertOneByOne(root);
        printLinkedList(root);
    }
}
