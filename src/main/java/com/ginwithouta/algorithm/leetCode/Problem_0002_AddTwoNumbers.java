package com.ginwithouta.algorithm.leetCode;
/**
 * @Package : com.ginwithouta.algorithm.leetCode.class01
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周一
 * @Desc :
 */
public class Problem_0002_AddTwoNumbers {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node1 = l1, node2 = l2, pre = null, node, root = null;
        int n1 = 0, n2 = 0, ca = 0, n = 0;
        while (node1 != null || node2 != null) {
            n1 = node1 == null ? 0 : node1.val;
            n2 = node2 == null ? 0 : node2.val;
            n = n1 + n2 + ca;
            ca = n / 10;
            if (pre == null) {
                pre = new ListNode(n % 10);
                root = pre;
            } else {
                node = new ListNode(n % 10);
                pre.next = node;
                pre = pre.next;
            }
            node1 = node1 == null ? null : node1.next;
            node2 = node2 == null ? null : node2.next;
        }
        if (ca > 0) {
            node = new ListNode(ca);
            pre.next = node;
        }
        return root;
    }
    public static ListNode reverse(ListNode node) {
        ListNode prev = null, next = node.next;
        while (next != null) {
            node.next = prev;
            prev = node;
            node = next;
            next = node.next;
        }
        return node;
    }
}
