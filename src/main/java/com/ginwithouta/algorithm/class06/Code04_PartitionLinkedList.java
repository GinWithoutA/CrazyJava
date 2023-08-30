package com.ginwithouta.algorithm.class06;

/**
 * @Package : com.ginwithouta.algorithm.class06
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周日
 * @Desc : 链表的Partition
 */
public class Code04_PartitionLinkedList {
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
    public static void printLinkedList(Code03_InsertOneByOne.Node root) {
        Code03_InsertOneByOne.Node cur = root;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println(" ");
    }
    public static Node partitionLinkedList(Node root, int value) {
        if (root == null || root.next == null) {
            return null;
        }
        Node smallRoot = null, smallTail = null;
        Node equalRoot = null, equalTail = null;
        Node greaterRoot = null, greaterTail = null;
        Node next = null;
        while (root != null) {
            next = root.next;
            root.next = null;
            if (root.value < value) {
                if (smallRoot == null) {
                    smallRoot = root;
                    smallTail = root;
                } else {
                    smallTail.next = root;
                    smallTail = root;
                }
            } else if (root.value > value) {
                if (greaterRoot == null) {
                    greaterRoot = root;
                    greaterTail = root;
                } else {
                    greaterTail.next = root;
                    greaterTail = root;
                }
            } else {
                if (equalRoot == null) {
                    equalRoot = root;
                    equalTail = root;
                } else {
                    equalTail.next = root;
                    equalTail = root;
                }
            }
            root = next;
        }
        // 开始连接各个区域的节点，从小于区域开始，依次往后连接
        // 首先判断小于区域是否为空，如果不为空，直接去连等于区域的头
        if (smallRoot != null) {
            smallTail.next = equalRoot;
            // 如果等于区为空，那么等于区域的尾巴就是小于区域的尾巴，将相当于把小于区和等于区合并了，后续equalTail.next会把之前的equalRoot给覆盖掉
            // 如果不为空，直接正常连接就行
            equalTail = equalRoot == null ? smallTail : equalTail;
        }
        // 有可能小于区和等于区都没有，因此还要做一次判断
        if (equalTail != null) {
            equalTail.next = greaterRoot;
        }
        return smallRoot != null ? smallRoot : (equalRoot != null ? equalRoot : greaterRoot);
    }
}
