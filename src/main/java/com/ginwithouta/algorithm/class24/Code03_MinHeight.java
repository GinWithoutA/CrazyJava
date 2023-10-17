package com.ginwithouta.algorithm.class24;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package : com.ginwithouta.algorithm.class24
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周六
 * @Desc : 求二叉树的最小深度
 */
public class Code03_MinHeight {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TreeNode {
        private int value;
        private TreeNode left;
        private TreeNode right;
    }
    public static int minHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root);
    }

    /**
     * 递归的过程求的是以当前孩子节点为头的子树的最小高度
     * @param node 当前节点
     * @return 当前节点node为头的树的最小高度
     */
    public static int process(TreeNode node) {
        if (node.right == null && node.left == null) {
            return 1;
        }
        int res = Integer.MAX_VALUE;
        if (node.left != null) {
            res = Math.min(res, process(node.left));
        }
        if (node.right != null) {
            res = Math.min(res, process(node.right));
        }
        return 1 + res;
    }
    public static int minHeightWithMorris(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode cur = root, mostRight = null;
        int min = Integer.MAX_VALUE, curLevel = 0, rightLength;
        while (cur != null) {
            // 如果有左子树，判断左子树的有边界有多长
            if (cur.left != null) {
                rightLength = 1;
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                    rightLength++;
                }
                // 如果是第一次到达cur，当前cur的Level++
                if (mostRight.right == null) {
                    curLevel++;
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                // 第二次到达
                // 首先判断当前节点是不是叶子节点,如果是，那么它的高度就是curLevel的高度
                if (mostRight.left == null) {
                    min = Math.min(min, curLevel);
                }
                // 回归到真正curLevel的高度
                curLevel -= rightLength;
                mostRight.right = null;
            } else {
                curLevel++;
            }
            cur = cur.right;
        }
        cur = root;
        rightLength = 1;
        while (cur.right != null) {
            cur = cur.right;
            rightLength++;
        }
        if (cur.left == null) {
            min = Math.min(min, rightLength);
        }
        return min;
    }
}
