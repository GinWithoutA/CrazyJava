package com.ginwithouta.algorithm.class10;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Package : com.ginwithouta.algorithm.class10
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc : 根据点次进行拓扑序的查找
 */
public class Code05_TopologicalOrderDFS2 {
    public static class DirectedGraphNode {
        public int label;
        public List<DirectedGraphNode> neighbors;
        public DirectedGraphNode(int label) {
            this.label = label;
            neighbors = new ArrayList<>();
        }
    }
    public static class Record {
        public DirectedGraphNode node;
        public int counts;
        public Record(DirectedGraphNode node, int counts) {
            this.node = node;
            this.counts = counts;
        }
    }
    public static List<DirectedGraphNode> topology(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> map = new HashMap<>(graph.size());
        for (DirectedGraphNode node : graph) {
            process(map, node);
        }
        ArrayList<Record> records = new ArrayList<>(map.values());
        records.sort((o1, o2) -> o2.counts = o1.counts);
        return records.stream().map(item -> item.node).toList();
    }

    public static Record process(HashMap<DirectedGraphNode, Record> map, DirectedGraphNode node) {
        if (map.containsKey(node)) {
            return map.get(node);
        }
        int count = 0;
        for (DirectedGraphNode neighbor : node.neighbors) {
            count += process(map, neighbor).counts;
        }
        return new Record(node, count);
    }
}
