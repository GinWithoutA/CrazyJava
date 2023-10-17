package com.ginwithouta.algorithm.class10;


/**
 * @Package : com.ginwithouta.algorithm.class10
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc :
 */
public class GraphGenerator<T> {
    public Graph<T> generateGraph(T[][] matrix) {
        Graph<T> graph = new Graph<>();
        for (T[] info : matrix) {
            T weight = info[0], from = info[1], to = info[2];
            if (!graph.getNodes().containsKey(from)) {
                graph.getNodes().put(from, new Node<>(from));
            }
            if (!graph.getNodes().containsKey(to)) {
                graph.getNodes().put(to, new Node<>(to));
            }
            Node<T> fromNode = graph.getNodes().get(from), toNode = graph.getNodes().get(to);
            Edge<T> edge = new Edge<>(weight, fromNode, toNode);
            graph.getEdges().add(edge);
            fromNode.setOut(fromNode.getOut() + 1);
            toNode.setIn(toNode.getIn() + 1);
            fromNode.getEdges().add(edge);
            fromNode.getNexts().add(toNode);
        }
        return graph;
    }

}
