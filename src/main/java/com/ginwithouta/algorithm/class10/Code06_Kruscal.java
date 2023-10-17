package com.ginwithouta.algorithm.class10;

import java.util.*;

/**
 * @Package : com.ginwithouta.algorithm.class10
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc : 克鲁斯卡尔算法求最小生成树
 */
public class Code06_Kruscal {
    public static class UnionSet {
        public HashMap<Node<Integer>, Node<Integer>> parents;
        public HashMap<Node<Integer>, Integer> sizes;
        public UnionSet(List<Node<Integer>> list) {
            parents = new HashMap<>();
            sizes = new HashMap<>();
            for (Node<Integer> node : list) {
                parents.put(node, node);
                sizes.put(node, 1);
            }
        }
        public Node<Integer> find(Node<Integer> node) {
            Stack<Node<Integer>> stack = new Stack<>();
            while (node != parents.get(node)) {
                stack.push(node);
                node = parents.get(node);
            }
            while (!stack.isEmpty()) {
                parents.put(stack.pop(), node);
            }
            return node;
        }
        public boolean isSameSet(Node<Integer> node1, Node<Integer> node2) {
            return find(node1) == find(node2);
        }
        public void union(Node<Integer> node1, Node<Integer> node2) {
            Node<Integer> parent1 = find(node1), parent2 = find(node2);
            if (parent2 != parent1) {
                Node<Integer> small = sizes.get(parent1) <= sizes.get(parent2) ? parent1 : parent2;
                Node<Integer> big = small == parent1 ? parent2 : parent1;
                sizes.put(big, sizes.get(parent1) + sizes.get(parent2));
                parents.put(small, big);
                sizes.remove(small);
            }
        }
    }
    public static List<Edge<Integer>> kruscal(Graph<Integer> graph) {
        UnionSet unionSet = new UnionSet(graph.getNodes().values().stream().toList());
        PriorityQueue<Edge<Integer>> edges = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        edges.addAll(graph.getEdges());
        List<Edge<Integer>> res = new ArrayList<>();
        while (!edges.isEmpty()) {
            Edge<Integer> edge = edges.poll();
            if (!unionSet.isSameSet(edge.getFrom(), edge.getTo())) {
                res.add(edge);
                unionSet.union(edge.getFrom(), edge.getTo());
            }
        }
        return res;
    }
}
