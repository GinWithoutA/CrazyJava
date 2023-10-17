package com.ginwithouta.algorithm.class10;

import lombok.Data;

/**
 * @Package : com.ginwithouta.algorithm.class10
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc :
 */
@Data
public class Edge<T> {
    private Node<T> from;
    private Node<T> to;
    private T weight;

    public Edge(T weight, Node<T> from, Node<T> to) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
