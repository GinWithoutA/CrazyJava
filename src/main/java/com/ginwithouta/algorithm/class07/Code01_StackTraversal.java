package com.ginwithouta.algorithm.class07;

import java.util.Stack;

/**
 * @Package : com.ginwithouta.algorithm.class07
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周一
 * @Desc : 树的遍历（用栈实现）
 */
public class Code01_StackTraversal {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
            this.left = this.right = null;
        }
    }
    public static int generateRandomNumber(int range) {
        return ((int) (Math.random() * range)) - ((int) (Math.random() * range));
    }
    public static int[] generateRandomArray(int maxLength, int range) {
        int length = (int) (Math.random() * maxLength);
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = generateRandomNumber(range);
        }
        return array;
    }
    public static void inTraversal(Node root) {
        if (root == null) {
            return ;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while (!stack.isEmpty() || cur != null) {
            stack.push(cur);
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
        System.out.println();
    }
    public static void preTraversal(Node root) {
        if (root == null) {
            return ;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                System.out.print(cur.value + " ");
                cur = cur.left;
            } else {
                cur = stack.pop();
                cur = cur.right;
            }
        }
        System.out.println();
    }
    public static void postTraversal(Node root) {
        if (root == null) {
            return ;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = root, pre = null;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                Node peek = stack.peek();
                if (peek.right == null || peek.right == pre) {
                    System.out.print(peek.value + " ");
                    pre = stack.pop();
                } else {
                    cur = peek.right;
                }
            }
        }
    }

}
