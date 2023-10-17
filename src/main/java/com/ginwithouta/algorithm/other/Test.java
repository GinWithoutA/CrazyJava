package com.ginwithouta.algorithm.other;

import com.ginwithouta.algorithm.class17.Code03_NQueens;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周五
 * @Desc :
 */
public class Test {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode PruneTree (TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val == 0 ? null : root;
        }
        List<TreeNode> nodes = level(root);
        for (int index = nodes.size() - 1; index > 0; index--) {
            TreeNode cur = nodes.get(index);
            if (cur.left == null  && cur.right == null && cur.val == 0) {
                TreeNode parent = nodes.get(index / 2);
                if (parent.left == cur) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                nodes.set(index, null);
            }
        }
        return root;
    }

    public static List<TreeNode> level (TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            list.add(cur);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return list;
    }







    public static int LowestCost (int[] cost) {
        // write code here
        return process(cost, -1);
    }
    public static int process(int[] cost, int index) {
        if (index == cost.length) {
            return 0;
        }
        int base = (index == -1 ? 0 : cost[index]);
        // case1： 当前站点补给，并跳到下一个站点进行补给
        int p1 = process(cost, index + 1);
        // case2:  当前站点补给，并跳到辖两个站点进行补给
        int p2 = process(cost, index + 2);
        return Math.min(p1, p2) + base;
    }

    public static void main(String[] args) {

    }
}
