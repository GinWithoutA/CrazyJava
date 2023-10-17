package com.ginwithouta.algorithm.class24;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package : com.ginwithouta.algorithm.class24
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周六
 * @Desc : 利用Morris遍历判断是否是BST
 */
public class Code02_IsBSTWithMorris {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Node {
        private int value;
        private Node left;
        private Node right;
    }

    /**
     * 利用Morris的中序遍历判断当前树是否是二叉搜索树
     * @param root
     * @return
     */
    public static boolean isBSTWithMorris(Node root) {
        if (root == null) {
            return false;
        }
        Node cur = root, mostRight = null, pre = null;
        boolean flag = true;
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
            }
            if (pre != null && cur.value < pre.value) {
                flag = false;
            }
            pre = cur;
            cur = cur.right;
        }
        return flag;
    }
}
