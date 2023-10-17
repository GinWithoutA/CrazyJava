package com.ginwithouta.algorithm.class10;

import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @Package : com.ginwithouta.algorithm.class10
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc : 迪杰斯特拉算法，返回到每个点的最小路径
 */
public class Code08_Dijkstra {
    public static HashMap<Node<Integer>, Integer> Dijkstra(Node<Integer> from) {
        HashMap<Node<Integer>, Integer> distancesMap = new HashMap<>();
        HashSet<Node<Integer>> selectedNode = new HashSet<>();
        distancesMap.put(from, 0);
        Node<Integer> minNode = getMinDistanceAndUnselectedNode(distancesMap, selectedNode);
        while (minNode != null) {
            int distance = distancesMap.get(minNode);
            for (Edge<Integer> edge : minNode.getEdges()) {
                Node<Integer> toNode = edge.getTo();
                if (!distancesMap.containsKey(toNode)) {
                    // 如果不存在就更新当前的距离
                    distancesMap.put(toNode, distance + edge.getWeight());
                } else {
                    // 存在判断哪个小
                    distancesMap.put(toNode, Math.min(distancesMap.get(toNode), distance + edge.getWeight()));
                }
            }
            selectedNode.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distancesMap, selectedNode);
        }
        return distancesMap;
    }
    public static Node<Integer> getMinDistanceAndUnselectedNode(HashMap<Node<Integer>, Integer> distancesMap, HashSet<Node<Integer>> selectedNode) {
        Node<Integer> minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node<Integer>, Integer> entry : distancesMap.entrySet()) {
            Node<Integer> node = entry.getKey();
            int distance = entry.getValue();
            if (!selectedNode.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }

    @Data
    public static class HeapGreater {
        private Node<Integer>[] heap;
        private HashMap<Node<Integer>, Integer> nodeIndexMap;
        private HashMap<Node<Integer>, Integer> distancesMap;
        private int size;

        public HeapGreater(int size) {
            this.heap = new Node[size];
            this.nodeIndexMap = new HashMap<>();
            this.distancesMap = new HashMap<>();
            this.size = 0;
        }

        public boolean isEnter(Node<Integer> node) {
            return nodeIndexMap.containsKey(node);
        }

        public boolean inHeap(Node<Integer> node) {
            return nodeIndexMap.containsKey(node) && nodeIndexMap.get(node) != -1;
        }

        public void swap(int index1, int index2) {
            this.nodeIndexMap.put(this.heap[index1], index2);
            this.nodeIndexMap.put(this.heap[index2], index1);
            Node<Integer> temp = this.heap[index1];
            this.heap[index1] = this.heap[index2];
            this.heap[index2] = temp;
        }

        /**
         * 向上heapify，只要比上面小就向上走
         * @param node
         * @param index
         */
        public void insertHeapify(Node<Integer> node, int index) {
            while (this.distancesMap.get(node) < this.distancesMap.get(this.heap[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void heapify(int index) {
            int left = index * 2 + 1;
            while (left < this.size) {
                int smaller = left + 1 < this.size ? (this.distancesMap.get(this.heap[left]) < this.distancesMap.get(this.heap[left + 1]) ? left : left + 1) : left;
                smaller = this.distancesMap.get(index) < this.distancesMap.get(smaller) ? index : smaller;
                if (smaller != index) {
                    swap(smaller, index);
                    index = smaller;
                    left = index * 2 + 1;
                } else {
                    break;
                }
            }
        }

        public NodeRecord pop() {
            Node<Integer> head = this.heap[0];
            swap(0, this.size-- - 1);
            this.nodeIndexMap.put(head, -1);
            return new NodeRecord(head, this.distancesMap.get(head));
        }

        public void addOrUpdateOrIgnore(Node<Integer> node, int distance) {
            if (!isEnter(node)) {
                this.heap[this.size] = node;
                this.nodeIndexMap.put(node, this.size);
                this.distancesMap.put(node, distance);
                insertHeapify(node, this.size++);
            } else if (inHeap(node)) {
                this.distancesMap.put(node, Math.min(this.distancesMap.get(node), distance));
                this.insertHeapify(node, this.nodeIndexMap.get(node));
            }
        }

        public boolean isEmpty() {
            return this.size == 0;
        }
    }

    @Data
    public static class NodeRecord {
        private Node<Integer> node;
        private int distance;
        public NodeRecord(Node<Integer> node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static HashMap<Node<Integer>, Integer> dijkstraHeap(Node<Integer> from, int size) {
        HeapGreater heapGreater = new HeapGreater(size);
        heapGreater.addOrUpdateOrIgnore(from, 0);
        HashMap<Node<Integer>, Integer> result = new HashMap<>(size);
        while (!heapGreater.isEmpty()) {
            NodeRecord record = heapGreater.pop();
            result.put(record.node, record.distance);
            for (Edge<Integer> edge : record.getNode().getEdges()) {
                heapGreater.addOrUpdateOrIgnore(edge.getTo(), edge.getWeight() + record.distance);
            }
        }
        return result;
    }
}
