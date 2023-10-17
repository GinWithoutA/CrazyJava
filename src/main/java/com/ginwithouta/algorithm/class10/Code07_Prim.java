package com.ginwithouta.algorithm.class10;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @Package : com.ginwithouta.algorithm.class10
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc :
 */
public class Code07_Prim {
    public static Set<Edge<Integer>> prim(Graph<Integer> graph) {
        PriorityQueue<Edge<Integer>> edges = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        HashSet<Node<Integer>> set = new HashSet<>();
        Set<Edge<Integer>> res = new HashSet<>();
        for (Node<Integer> node : graph.getNodes().values()) {
            if (!set.contains(node)) {
                set.add(node);
                edges.addAll(node.getEdges());
                while (!edges.isEmpty()) {
                    Edge<Integer> smallest = edges.poll();
                    if (!set.contains(smallest.getTo())) {
                        res.add(smallest);
                        set.add(smallest.getTo());
                        edges.addAll(smallest.getTo().getEdges());
                    }
                }
            }
        }
        return res;
    }
}
