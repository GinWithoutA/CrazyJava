package com.ginwithouta.algorithm.class02;

/**
 * @Package : com.ginwithouta.algorithm.class02
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周日
 * @Desc : 双向链表实现队列和栈
 */
public class Code03_DoubleEndsQueueToStackAndQueue {
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
    public static class Queue {
        public DoubleNode head;
        public DoubleNode rail;
        public Queue(DoubleNode head, DoubleNode rail) {
            this.head = head;
            this.rail = rail;
        }
        public static void add(int value) {

        }
    }
    public static void main(String[] args) {

    }
}
