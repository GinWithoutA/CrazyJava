package com.ginwithouta.algorithm.class07;

/**
 * @Package : com.ginwithouta.algorithm.class07
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周二
 * @Desc : 找当前节点的后继节点
 */
public class Code05_SuccessorNode {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;
        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    /**
     * 找到当前节点的后继节点
     * @param node
     * @return
     */
    public static Node findSuccessor(Node node) {
        if (node == null) {
            return null;
        }
        Node cur = node, pre;
        // 如果当前节点有右子树
        if (node.right != null) {
            cur = node.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }
        // 如果没有右子树
        while (cur.parent != null) {
            pre = cur;
            cur = cur.parent;
            if (pre == cur.left) {
                return cur;
            }
        }
        return null;
    }

}
