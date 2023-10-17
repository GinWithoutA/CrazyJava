package com.ginwithouta.algorithm.class09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Package : com.ginwithouta.algorithm.class09
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周六
 * @Desc : 给定一个二维数组，返回数组中所有的岛的数量
 */
public class Code03_Island {

    public static void infection(int i, int j, int[][] array) {
        if (i < 0 || j < 0 || i >= array.length || j >= array.length || array[i][j] != '1') {
            return ;
        }
        array[i][j] = 2;
        infection(i - 1, j, array);
        infection(i + 1, j, array);
        infection(i, j - 1, array);
        infection(i, j + 1, array);
    }

    /**
     * 递归方法实现查找岛的数量
     * @param array
     * @return
     */
    public static int islandMethod1(int[][] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == '1') {
                    count++;
                    infection(i, j, array);
                }
            }
        }
        return count;
    }

    /**
     * 并查集方法实现查找岛的数量
     * @param array
     * @return
     */
    public static int islandMethod2(int[][] array) {
        Dot[][] dots = new Dot[array.length][array[0].length];
        List<Dot> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int  j = 0; j < array[0].length; j++) {
                if (array[i][j] == '1') {
                    dots[i][j] = new Dot();
                    list.add(dots[i][j]);
                }
            }
        }
        UnionSet<Dot> unionSet = new UnionSet<>(list);
        for (int i = 1; i < array.length; i++) {
            if (array[i][0] == '1' || array[i - 1][0] == '1') {
                unionSet.union(dots[i - 1][0], dots[i][0]);
            }
        }
        for (int i = 1; i < array[0].length; i++) {
            if (array[0][i] == '1' || array[0][i - 1] == '1') {
                unionSet.union(dots[0][i - 1], dots[0][i]);
            }
        }
        for (int i = 1; i < array.length; i++) {
            for (int j = 1; i < array[0].length; j++) {
                if (array[i][j] == '1') {
                    if (array[i - 1][j] == '1') {
                        unionSet.union(dots[i - 1][j], dots[i][j]);
                    }
                    if (array[i][j - 1] == '1') {
                        unionSet.union(dots[i][j - 1], dots[i][j]);
                    }
                }
            }
        }
        return unionSet.sizeMaps.size();
    }

    public static class Dot {}

    public static class Node<T> {
        public T value;
        public Node (T value) {
            this.value = value;
        }
    }

    public static class UnionSet<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMaps;
        public UnionSet (List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            for (V value : values) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMaps.put(node, 1);
            }
        }

        public Node<V> find(V value) {
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

        public void union(V value1, V value2) {
            Node<V> find1 = find(value1), find2 = find(value2);
            if (find1 != find2) {
                int size1 = sizeMaps.get(find1), size2 = sizeMaps.get(find2);
                Node<V> small = size1 <= size2 ? find1 : find2, big = small == find1 ? find2 : find1;
                parents.put(small, big);
                sizeMaps.put(big, size1 + size2);
                sizeMaps.remove(small);
            }
        }
    }

    public static int islandMethod3(int[][] array) {
        UnionSetArray unionSetArray = new UnionSetArray(array);
        for (int i = 1; i < array.length; i++) {
            if (array[i][0] == '1' && array[i - 1][0] == '1') {
                unionSetArray.union(unionSetArray.getIndex(i, 0), unionSetArray.getIndex(i - 1, 0));
            }
        }
        for (int i = 1; i < array[0].length; i++) {
            if (array[0][i] == '1' && array[0][i - 1] == '1') {
                unionSetArray.union(unionSetArray.getIndex(0, i), unionSetArray.getIndex(0, i - 1));
            }
        }
        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array[0].length; j++) {
                if (array[i][j] == '1') {
                    if (array[i - 1][j] == '1') {
                        unionSetArray.union(unionSetArray.getIndex(i, j), unionSetArray.getIndex(i - 1, j));
                    }
                    if (array[i][j - 1] == '1') {
                        unionSetArray.union(unionSetArray.getIndex(i, j), unionSetArray.getIndex(i, j - 1));
                    }
                }
            }
        }
        return unionSetArray.sets;
    }

    public static class UnionSetArray {
        public int[] sizes;
        public int[] parents;
        public int[] help;
        int col;
        public int sets;

        public int getIndex(int i, int j) {
            return i * col + j;
        }

        public UnionSetArray(int [][] array) {
            this.col = array[0].length;
            sets = 0;
            sizes = new int[array[0].length * array.length];
            parents = new int[array[0].length * array.length];
            help = new int[array[0].length * array.length];
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    if (array[i][j] == '1') {
                        int index = getIndex(i, j);
                        parents[index] = index;
                        sizes[index] = 1;
                        sets++;
                    }
                }
            }
        }

        public int find(int i) {
            int temp = 0;
            while (i != parents[i]) {
                help[temp++] = i;
                i = parents[i];
            }
            while (temp > 0) {
                parents[help[--temp]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int parentI = find(i), parentJ = find(j);
            if (parentI != parentJ) {
                if (sizes[parentI] <= sizes[parentJ]) {
                    parents[parentI] = parentJ;
                    sizes[parentJ] += sizes[parentI];
                } else {
                    parents[parentJ] = parentI;
                    sizes[parentI] += sizes[parentJ];
                }
                sets--;
            }
        }
    }
}
