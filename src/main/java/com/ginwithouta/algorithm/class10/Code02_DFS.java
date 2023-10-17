package com.ginwithouta.algorithm.class10;

import java.util.HashSet;
import java.util.Stack;

/**
 * @Package : com.ginwithouta.algorithm.class10
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc :
 */
public class Code02_DFS {
    public static void DFS1(Node<Integer> start) {
        if (start == null) {
            return;
        }
        HashSet<Node<Integer>> set = new HashSet<>();
        process(set, start);
    }

    public static void process(HashSet<Node<Integer>> set, Node<Integer> node) {
        for (Node<Integer> next : node.getNexts()) {
            if (next != null && !set.contains(next)) {
                process(set, next);
            }
        }
        System.out.print(node.getValue() + " ");
    }

    public static void DFS2(Node<Integer> start) {
        if (start == null) { return; }
        Stack<Node<Integer>> stack = new Stack<>();
        HashSet<Node<Integer>> set = new HashSet<>();
        stack.push(start);
        set.add(start);
        System.out.print(start.getValue() + " ");
        while (!stack.isEmpty()) {
            Node<Integer> node = stack.pop();
            for (Node<Integer> next : node.getNexts()) {
                if (!set.contains(next)) {
                    // 发现任何一个邻居没被遍历过，就把原来弹出的节点押回去
                    stack.push(node);
                    stack.push(next);
                    set.add(next);
                    System.out.print(next.getValue());
                    break;
                }
            }
        }
    }


}
