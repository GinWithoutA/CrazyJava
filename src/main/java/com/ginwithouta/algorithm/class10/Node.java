package com.ginwithouta.algorithm.class10;

import lombok.Data;

import java.util.ArrayList;

/**
 * @Package : com.ginwithouta.algorithm.class10
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc :
 */
@Data
public class Node<T> {
    private int in;
    private int out;
    private T value;
    private ArrayList<Edge<T>> edges;
    private ArrayList<Node<T>> nexts;
    
    public Node(T value) {
        in = 0;
        out = 0;
        this.value = value;
        edges = new ArrayList<>();
        nexts = new ArrayList<>();
    }
}
