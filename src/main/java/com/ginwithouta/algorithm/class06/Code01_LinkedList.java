package com.ginwithouta.algorithm.class06;

import lombok.val;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Package : com.ginwithouta.algorithm.class06
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周五
 * @Desc :
 */
public class Code01_LinkedList {
    public static class Node {
        public int value;
        public Node next;
        public Node (int value) {
            this.value = value;
            this.next = null;
        }
    }
    public static Node generateRandomLinkedArray(int maxLength, int range) {
        Node root = new Node(generateRandomNumber(range)), current = root;
        int length = (int) (Math.random() * maxLength) + 2;
        for (int i = 0; i < length; i++) {
            current.next = new Node(generateRandomNumber(range));
            current = current.next;
        }
        return root;
    }
    public static int generateRandomNumber(int range) {
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }
    public static void printLinkedArray(Node root) {
        Node cur = root;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println(" ");
    }
    public static ArrayList<Integer> copy(Node root) {
        ArrayList<Integer> list = new ArrayList<>();
        Node cur = root;
        while (cur != null) {
            list.add(cur.value);
            cur = cur.next;
        }
        return list;
    }

    /**
     * 奇数个返回唯一的中点，偶数个返回上中点
     * @param root
     * @return
     */
    public static int middleNdoeUpper(Node root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        if (root.next == null) {
            return root.value;
        }
        Node slow = root, fast = root.next;
        while (fast != null && fast.next!= null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.value;
    }

    /**
     * 奇数个返回唯一的中点，偶数个返回下中点
     * @param root
     * @return
     */
    public static int middleNdoeLower(Node root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        if (root.next == null) {
            return root.value;
        }
        Node slow = root, fast = root.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null) {
            return slow.value;
        } else {
            return slow.next.value;
        }
    }
    /**
     * 奇数个返回唯一中点的前一个节点，偶数个返回上中点的前一个节点
     * @param root
     * @return
     */
    public static int middleNdoeUpperPrev(Node root) {
        // 如果链表的长度小于等于二，就没有中点前一个或者上中点前一个
        if (root == null || root.next == null || root.next.next == null) {
            return Integer.MIN_VALUE;
        }
        Node pre = null, slow = root, fast = root.next;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return pre.value;
    }
    /**
     * 奇数个返回唯一中点的前一个节点，偶数个返回下中点的前一个节点也就是上中点
     * @param root
     * @return
     */
    public static int middleNdoeLowerPrev(Node root) {
        // 如果链表的长度小于等于二，就没有中点前一个或者上中点前一个
        if (root == null || root.next == null || root.next.next == null) {
            return Integer.MIN_VALUE;
        }
        Node pre = null, slow = root, fast = root.next;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null) {
            return pre.value;
        } else {
            return slow.value;
        }
    }
    public static void main(String[] args) {
        int maxLength = 10, range = 100, testTime = 10000;
        for (int i = 0; i < testTime; i++) {
            Node root = generateRandomLinkedArray(maxLength, range);
            ArrayList<Integer> list = copy(root);
            int middleUpper = middleNdoeUpper(root);
            int middleLower = middleNdoeLower(root);
            int middleUpperPrev = middleNdoeUpperPrev(root);
            int middleLowerPrev = middleNdoeLowerPrev(root);
            // printLinkedArray(root);
            // System.out.println(middlePrev);
            // System.out.println(list);
            // System.out.println(list.get((list.size() - 1) / 2 - 1));
            if (middleUpper != list.get((list.size() - 1) / 2)) {
                System.out.println("出错啦");
            }
            if (middleLower != list.get(list.size() / 2)) {
                System.out.println("出错啦");
            }
            if (middleUpperPrev != list.get((list.size() - 1) / 2 - 1)) {
                System.out.println("出错啦");
            }
            if (list.size() % 2 != 0) {
                if (middleLowerPrev != list.get((list.size() - 1) / 2 - 1)) {
                    System.out.println("出错啦");
                }
            } else {
                // 偶数，返回下中点的上一个，也就是上中点
                if (middleLowerPrev != list.get((list.size() - 1) / 2)) {
                    System.out.println("出错啦");
                }
            }
        }
    }
}
