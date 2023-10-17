package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周三
 * @Desc :
 */
public class Problem_0021_MergeTwoSortedLists {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next;}
    }
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode head = null, cur = null;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                 cur.next =list2;
                break;
            }
            if (list2 == null) {
                cur.next = list1;
                break;
            }
            if (list1.val <= list2.val) {
                if (head == null) {
                    head = list1;
                    list1 = list1.next;
                    cur = head;
                } else {
                    cur.next = list1;
                    cur = cur.next;
                    list1 = list1.next;
                }
            } else {
                if (head == null) {
                    head = list2;
                    list2 = list2.next;
                    cur = head;
                } else {
                    cur.next = list2;
                    cur = cur.next;
                    list2 = list2.next;
                }
            }
        }
        return head;
     }
}
