package com.ginwithouta.algorithm.class24;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.modelmbean.ModelMBean;

/**
 * @Package : com.ginwithouta.algorithm.class24
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周六
 * @Desc : Morris遍历：
 *
 *          假设来到当前节点cur，开始时cur来到头节点位置
 *          1）如果cur没有左孩子，cur = cur.right
 *          2）如果cur有左孩子，找到左子树上最右的节点mostRight
 *              a. 如果mostRight的右指针为空，让其指向cur，然后cur = cur.left
 *              b. 如果mostRight的右指针指向cur，让其指向null，然后cur = cur.right
 *          3）cur为空时遍历停止
 */
public class Code01_MorrisTraversal {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Node {
        private int value;
        private Node left;
        private Node right;
    }

    public static void morrisTraversal(Node root) {
        if (root == null) {
            return ;
        }
        Node cur = root, mostRight = null;
        while (cur != null) {
            // 如果存在左树，找左树中的最右子节点
            if (cur.left != null) {
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 判断最右子节点的右孩子是空还是cur，如果是空，右孩子等于cur，然后cur往左边跳
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                // 如果是cur，把他重置为null，然后cur往右边跳
                mostRight.right = null;
            }
            // 如果没有左子树，直接往右边跳，根上面的mostRight.right == cur条件重合
            cur = cur.right;
        }
    }

    /**
     * 利用Morris进行先序遍历并打印，也就是第一次到达当前节点的时候进行打印
     * @param root
     */
    public static void preOrderWithMorris(Node root) {
        if (root == null) {
            return ;
        }
        Node cur = root, mostRight = null;
        while (cur != null) {
            if (cur.left != null) {
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 如果mostRight.right == null，表明第一次到达cur节点，直接进行打印
                if (mostRight.right == null) {
                    System.out.println(cur.value);
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                mostRight.right = null;
            }
            System.out.println(cur.value);
            cur = cur.right;
        }
    }
    /**
     * 利用Morris进行中序遍历并打印，如果一个节点能到达两次，则第二次到达的时候打印，如果只能到达一次，则直接打印
     * @param root
     */
    public static void midOrderWithMorris(Node root) {
        if (root == null) {
            return ;
        }
        Node cur = root, mostRight = null;
        while (cur != null) {
            if (cur.left != null) {
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 能进这个，说明当前cur肯定能到两次，不打印cur
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                mostRight.right = null;
            }
            System.out.println(cur.value);
            cur = cur.right;
        }
    }
    public static void posOrderWithMorris(Node root) {
        if (root == null) {
            return ;
        }
        Node cur = root, mostRight = null;
        while (cur != null) {
            if (cur.left != null) {
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                mostRight.right = null;
                printPos(cur);
            }
            cur = cur.right;
        }
        printPos(root);
    }

    /**
     * 逆序打印左子树的有边界
     * @param cur
     */
    public static void printPos(Node cur) {
        Node head = reverse(cur.left), temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.right;
        }
        reverse(head);
    }
    public static Node reverse(Node root) {
        Node pre = null, cur = root, next;
        while (cur != null) {
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        return cur;
    }
}
