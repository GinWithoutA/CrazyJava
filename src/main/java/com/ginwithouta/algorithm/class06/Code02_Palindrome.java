package com.ginwithouta.algorithm.class06;

import lombok.val;

import javax.xml.stream.FactoryConfigurationError;
import java.text.BreakIterator;

/**
 * @Package : com.ginwithouta.algorithm.class06
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周日
 * @Desc :
 */
public class Code02_Palindrome {
    public static class Node {
        public int value;
        public Node next;
        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }
    public static Node generateLinkedList(int[] arr) {
        Node root = new Node(arr[0]), cur = root;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new Node(arr[i]);
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
    /**
     * 判断是否有回文结构
     * @param root
     * @return
     */
    public static boolean palindrome(Node root) {
        // 先找到中点
        if (root == null || root.next == null) {
            return false;
        }
        Node slow = root, fast = root.next;
        boolean result = true;
        // 如果fast == null，说明现在是奇数个节点，如果fast.next == null,说明现在是偶数个节点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 从中点开始的每一个节点都要反转
        Node last = reverse(slow);
        slow = root;
        fast = last;
        while (slow != null && fast != null) {
            if (slow.value != fast.value) {
                result = false;
                break;
            }
            slow = slow.next;
            fast = fast.next;
        }
        reverse(last);
        return result;
    }
    public static Node reverse(Node root) {
        Node cur = root, next = root, pre = null;
        while (next != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    public static void main(String[] args) {
        int range = 100, maxLength = 100, testTime = 10000;
        int[] arr = new int[] {1, 2, 3, 2, 1};
        Node root = generateLinkedList(arr);
        printLinkedList(root);
        boolean result = palindrome(root);
        printLinkedList(root);
        System.out.println(result);
    }
}
