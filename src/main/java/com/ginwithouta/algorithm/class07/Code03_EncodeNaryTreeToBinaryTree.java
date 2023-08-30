package com.ginwithouta.algorithm.class07;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package : com.ginwithouta.algorithm.class07
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周二
 * @Desc : 将多叉树转换成二叉树，同时也将二叉树转化为唯一的多叉树
 * @method : 孩子都在左边，兄弟都在右边
 */
public class Code03_EncodeNaryTreeToBinaryTree {
    public static class Node {
        public int value;
        public List<Node> children;
        public Node(int value, List<Node> children) {
            this.value = value;
            this.children = children;
        }
    }
    public static class TreeNode {
        public int value;
        // 存放当前节点的孩子
        public TreeNode left;
        // 存放当前节点的的兄弟
        public TreeNode right;
        public TreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    public static TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }
        TreeNode treeRoot = new TreeNode(root.value);
        treeRoot.left = en(root.children);
        return treeRoot;
    }
    public static TreeNode en(List<Node> children) {
        if (children == null) {
            return null;
        }
        // root保存当前的第一个孩子节点，cur用来判断当前指向第几个兄弟
        TreeNode root = null, cur = null;
        for (Node child : children) {
            TreeNode node = new TreeNode(child.value);
            // root == null说明当前刚进循环，是第一个孩子
            if (root == null) {
                root = node;
            } else {
                // 不是第一个孩子，上一个兄弟的又孩子指向它
                cur.right = node;
            }
            cur = node;
            node.left = en(child.children);
        }
        return root;
    }
    public static Node decode(TreeNode treeRoot) {
        if (treeRoot == null) {
            return null;
        }
        return new Node(treeRoot.value, de(treeRoot.left));

    }
    public static List<Node> de(TreeNode treeRoot) {
        ArrayList<Node> children = new ArrayList<>();
        while (treeRoot != null) {
            Node node = new Node(treeRoot.value, de(treeRoot.left));
            children.add(node);
            // 不断往右边走，把所有兄弟都加到children集合里面
            treeRoot = treeRoot.right;
        }
        return children;
    }
}
