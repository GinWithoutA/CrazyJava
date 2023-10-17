package com.ginwithouta.algorithm.class10;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Package : com.ginwithouta.algorithm.class10
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc : 图的宽度优先遍历
 */
public class Code01_BFS {
    public static void BFS(Node<Integer> start) {
        if (start == null) {
            return;
        }
        Queue<Node<Integer>> queue = new LinkedList<>();
        HashSet<Node<Integer>> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node<Integer> node = queue.poll();
            System.out.print(node.getValue() + " ");
            for (Node<Integer> next : node.getNexts()) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }
}
