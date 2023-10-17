package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周一
 * @Desc : 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
 *
 * 两个节点之间的路径长度 由它们之间的边数表示。
 * @method 二叉树递归套路：
 *         1）要求必须以x节点出发的情况：
 *                  a 可能只有自己
 *                  b 左树加上自己
 *                  c 右树加上自己
 *         2）要求不一定从x节点出发的情况：
 *                  1 不经过x节点
 *                          a 左树最长
 *                          b 右树最长
 *                  2 经过x节点
 *                          a 左树最长加上右树最长
 *
 */
public class Problem_0687_LongestUnivaluePath {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static class Info {
        // 要求必须以x节点出发的最大合法路径
        public int len;
        // 要求不一定以x节点出发的最大合法路径
        public int max;
        public Info(int len, int max) { this.len = len; this.max = max; }
    }
    public int longestUnivaluePath(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        Info info = getInfo(root);
        return Math.max(info.max, info.len) - 1;
    }
    public Info getInfo(TreeNode node) {
        if (node == null) {
            return new Info(0, 0);
        }
        // 必须以当前节点出发的情况，判断是往左边长还是往右边长还是只有自己一个节点
        int len = 1;
        Info leftInfo = getInfo(node.left), rightInfo = getInfo(node.right);
        len += node.left != null && node.val == node.left.val ? leftInfo.len : 0;
        len = Math.max(len, 1 + (node.right != null && node.val == node.right.val ? rightInfo.len : 0));
        // 不一定以当前节点出发的情况，那就判断左边的max大还是右边的max大还是加上自己以后的max大
        int max = Math.max(len, Math.max(leftInfo.max, rightInfo.max));
        if (node.left != null && node.val == node.left.val && node.right != null && node.val == node.right.val) {
            max = Math.max(max, leftInfo.len + rightInfo.len + 1);
        }
        return new Info(len, max);
    }
}
