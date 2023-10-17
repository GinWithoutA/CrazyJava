package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周三
 * @Desc :
 */
public class Problem_0019_RemoveNthNodeFromEndOfList {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next;}
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        for (int i = 1; i <= n; i++) {
            cur = cur.next;
        }
        // 当链表中只有一个节点或者删除头节点的时候的时候，此时的cur都会是null
        if (cur == null) {
            if (head.next == null) {
                return null;
            } else {
                cur = head;
                head = head.next;
                cur.next = null;
                return head;
            }
        }
        ListNode pre = head;
        while (cur.next != null) {
            pre = pre.next;
            cur = cur.next;
        }
        cur = pre.next;
        pre.next = cur.next;
        cur.next = null;
        return head;
    }
}
