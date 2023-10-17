package com.ginwithouta.algorithm.class09;

import lombok.val;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Package : com.ginwithouta.algorithm.class09
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周六
 * @Desc :
 */
public class Code01_UnionSet {
    public static class Node<T> {
        public T value;

        public Node(T value) {
            this.value = value;
        }
    }

    public static class UnionSet<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionSet(List<V> list) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : list) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(V value) {
            Stack<Node<V>> stack = new Stack<>();
            Node<V> cur = nodes.get(value);
            while (cur != parents.get(cur)) {
                stack.push(cur);
                cur = parents.get(cur);
            }
            while (!stack.isEmpty()) {
                parents.put(stack.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V value1, V value2) {
            return findFather(value1) == findFather(value2);
        }

        public void unionSet(V value1, V value2) {
            Node<V> node1 = findFather(value1), node2 = findFather(value2);
            if (node1 != node2) {
                int size1 = sizeMap.get(node1), size2 = sizeMap.get(node2);
                Node<V> small = size1 <= size2 ? node1 : node2, big = small == node1 ? node2 : node1;
                parents.put(big, small);
                sizeMap.put(small, size1 + size2);
                sizeMap.remove(big);
            }
        }
    }
}
