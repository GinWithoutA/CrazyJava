package com.ginwithouta.algorithm.class02;

import org.w3c.dom.Node;

/**
 * @Package : com.ginwithouta.algorithm.class02
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周六
 * @Desc : 单链表和双链表反转
 */
public class Code01_ReverseList {
    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;
        public DoubleNode(int data) {
            this.value = data;
            this.last = null;
            this.next = null;
        }
    }
    public static DoubleNode generateLinkedList(int maxSize, int range) {
        DoubleNode root = new DoubleNode(randomNumber(range)), temp = root;
        int size = (int) (Math.random() * maxSize);
        for (int i = 0; i < size; i++) {
            DoubleNode node = new DoubleNode(randomNumber(range));
            temp.next = node;
            temp = node;
        }
        return root;
    }
    public static DoubleNode generateDoubleLinkedList(int maxSize, int range) {
        DoubleNode root = new DoubleNode(randomNumber(range)), temp = root;
        int size = (int) (Math.random() * maxSize);
        for (int i = 0; i < size; i++) {
            DoubleNode node = new DoubleNode(randomNumber(range));
            node.last = temp;
            temp.next = node;
            temp = node;
        }
        return root;
    }
    public static int randomNumber(int range) {
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }
    public static DoubleNode linkedListReverse(DoubleNode root) {
        DoubleNode pre = null, next;
        while (root != null) {
            next = root.next;
            root.next = pre;
            pre = root;
            root = next;
        }
        return pre;
    }
    public static DoubleNode doubleLinkedListReverse(DoubleNode root) {
        DoubleNode pre = null, next;
        while (root != null) {
            next = root.next;
            root.last = next;
            root.next = pre;
            pre = root;
            root = next;
        }
        return pre;
    }
    public static void printList(DoubleNode root) {
        DoubleNode temp = root;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println(" ");
    }
    public static void main(String[] args) {
        int maxSize = 10, range = 100;
        DoubleNode root = generateLinkedList(maxSize, range);
        DoubleNode doubleRoot = generateDoubleLinkedList(maxSize, range);
        printList(root);
        printList(doubleRoot);
        DoubleNode newRoot = linkedListReverse(root);
        DoubleNode newDoubleRoot = doubleLinkedListReverse(doubleRoot);
        printList(newRoot);
        printList(newDoubleRoot);
    }
}
