package com.ginwithouta.algorithm.class10;

import java.util.*;

/**
 * @Package : com.ginwithouta.algorithm.class10
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc :
 */
public class Code03_TopologySort {
    public static List<Node<Integer>> topologySort(Graph<Integer> graph) {
        HashMap<Node<Integer>, Integer> inMap = new HashMap<>(graph.getNodes().size());
        Queue<Node<Integer>> queue = new LinkedList<>();
        List<Node<Integer>> list = new ArrayList<>();
        for (Node<Integer> node : graph.getNodes().values()) {
            inMap.put(node, node.getIn());
            if (node.getIn() == 0) {
                list.add(node);
                queue.add(node);
            }
        }
        while (!queue.isEmpty()) {
            Node<Integer> node = queue.poll();
            for (Node<Integer> next : node.getNexts()) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    queue.add(next);
                    list.add(next);
                }
            }
        }
        return list;
    }
}
