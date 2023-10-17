package com.ginwithouta.algorithm.class10;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Package : com.ginwithouta.algorithm.class10
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc :
 */
@Data
public class Graph<T> {
    private HashMap<T, Node<T>> nodes;
    private HashSet<Edge<T>> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
