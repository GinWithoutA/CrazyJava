package com.ginwithouta.algorithm.class02;

/**
 * @Package : com.ginwithouta.algorithm.class02
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周日
 * @Desc : 单双链表删除指定的值
 */
public class Code02_DeleteGivenValue {
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
    public static DoubleNode deleteGivenValue(int target, DoubleNode root) {
        while (root != null) {
            if (root.value != target) {
                break;
            }
            root = root.next;
        }
        DoubleNode pre = root, temp = root;
        while (temp != null) {
            if (temp.value != target) {
                pre = temp;
            } else {
                pre.next = temp;
            }
            temp = temp.next;
        }
        return root;
    }
    public static void main(String[] args) {

    }
}
