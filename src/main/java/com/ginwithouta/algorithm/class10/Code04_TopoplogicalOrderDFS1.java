package com.ginwithouta.algorithm.class10;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Package : com.ginwithouta.algorithm.class10
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc : 根据最大深度求拓扑序
 */
public class Code04_TopoplogicalOrderDFS1 {
    public static class DirectedGraphNode {
        public int label;
        public List<DirectedGraphNode> neighbors;
        public DirectedGraphNode(int label) {
            this.label = label;
            neighbors = new ArrayList<>();
        }
    }
    public static class Record {
        DirectedGraphNode node;
        int depth;
        public Record(DirectedGraphNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    public static List<DirectedGraphNode> topology(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> map = new HashMap<>(graph.size());
        for (DirectedGraphNode node : graph) {
            process(map, node);
        }
        ArrayList<Record> records = new ArrayList<>(map.values());
        records.sort((o1, o2) -> o2.depth - o1.depth);
        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        for (Record record : records) {
            res.add(record.node);
        }
        return res;
    }
    public static Record process(HashMap<DirectedGraphNode, Record> memory, DirectedGraphNode node) {
        if (memory.containsKey(node)) {
            return memory.get(node);
        }
        int depth = 0;
        for (DirectedGraphNode neighbor : node.neighbors) {
            Record record = process(memory, neighbor);
            depth = Math.max(record.depth, depth);
        }
        return new Record(node, depth + 1);
    }
}
