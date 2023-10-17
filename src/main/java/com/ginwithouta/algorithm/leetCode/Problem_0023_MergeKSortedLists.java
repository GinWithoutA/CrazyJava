package com.ginwithouta.algorithm.leetCode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周三
 * @Desc :
 */
public class Problem_0023_MergeKSortedLists {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next;}
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            if (list != null) {
                heap.add(list);
            }
        }
        ListNode head = null, cur = head, temp;
        while (!heap.isEmpty()) {
            if (head == null) {
                head = heap.poll();
                if (head.next != null) {
                    heap.add(head.next);
                    head.next = null;
                }
                cur = head;
            } else {
                temp = heap.poll();
                if (temp.next != null) {
                    heap.add(temp.next);
                    temp.next = null;
                }
                cur.next = temp;
                cur = cur.next;
            }
        }
        return head;
    }
}
