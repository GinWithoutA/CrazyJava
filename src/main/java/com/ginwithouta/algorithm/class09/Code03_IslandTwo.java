package com.ginwithouta.algorithm.class09;

import javax.print.attribute.standard.MediaSizeName;
import javax.swing.text.Position;
import java.nio.file.StandardWatchEventKinds;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Package : com.ginwithouta.algorithm.class09
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周六
 * @Desc : 还是一样，给定一个二维数组，刚开始的时候都是0，接下来会随机给某一个位置，将其变为'1'，在每次变更的时候都返回当前变更完成之后有多少个连通岛
 */
public class Code03_IslandTwo {

    public static List<Integer> island(int rows, int cols, int[][] positions) {
        UnionSetIsland unionSetIsland = new UnionSetIsland(rows, cols);
        List<Integer> list = new ArrayList<Integer>();
        for (int[] position : positions) {
            list.add(unionSetIsland.connect(position[0], position[1]));
        }
        return list;
    }
    public static class UnionSetIsland {
        public int[] sizes;
        public int[] parents;
        public int[] help;
        public int cols, rows, sets;
        public int getIndex(int i, int j) {
            return i * cols + j;
        }
        public UnionSetIsland(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            int size = rows * cols;
            sizes = new int[size];
            parents = new int[size];
            help = new int[size];
            sets = 0;
        }

        /**
         *
         * @param i 修改的i位置
         * @param j 修改的j位置
         * @return
         */
        public int connect(int i, int j) {
            int index = getIndex(i, j);
            if (sizes[index] == 0) {
                parents[index] = index;
                sizes[index] = 1;
                sets++;
                if (i - 1 >= 0) {
                    union(getIndex(i - 1, j), getIndex(i, j));
                }
                if (i + 1 < rows) {
                    union(getIndex(i + 1, j), getIndex(i, j));
                }
                if (j - 1 >= 0) {
                    union(getIndex(i, j - 1), getIndex(i, j));
                }
                if (j + 1 < cols) {
                    union(getIndex(i, j + 1), getIndex(i, j));
                }
            }
            return sets;
        }

        public void union(int i, int j) {
            int parentI = find(i), parentJ = find(j);
            if (parentJ != parentI) {
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

    }

    /**
     *  当集合很大，但是到的点很少的时候的做法
     */
    public static class UnionSetIsland2 {
        public HashMap<String, String> parents;
        public HashMap<String, Integer> sizes;
        public int rows, cols, sets;

        public UnionSetIsland2(int rows, int cols) {
            this.parents = new HashMap<>();
            this.sizes = new HashMap<>();
            this.sets = 0;
            this.rows = rows;
            this.cols = cols;
        }

        public int connect(int i, int j) {
            String position = i + "_" + j;
            // 如果新到达的点已经在之前出现过，直接返回即可
            if (!parents.containsKey(position)) {
                parents.put(position, position);
                sizes.put(position, 1);
                sets++;
                if (i - 1 >= 0) {
                    union((i - 1) + "_" + j, position);
                }
                if (i + 1 < rows) {
                    union((i + 1) + "_" + j, position);
                }
                if (j - 1 >= 0) {
                    union(i + "_" + (j - 1), position);
                }
                if (j + 1 < cols) {
                    union(i + "_" + (j + 1), position);
                }
            }
            return sets;
        }

        public void union(String i, String j) {
            String parentI = find(i), parentJ = find(j);
            if (!parentJ.equals(parentI)) {
                String small = sizes.get(parentI) < sizes.get(parentJ) ? parentI : parentJ;
                String big = small.equals(parentI) ? parentJ : parentI;
                parents.put(small, big);
                sizes.put(big, sizes.get(parentJ) + sizes.get(parentI));
                sets--;
            }
        }

        public String find(String i) {
            Stack<String> stack = new Stack<>();
            while (!i.equals(parents.get(i))) {
                stack.push(i);
                i = parents.get(i);
            }
            while (!stack.isEmpty()) {
                parents.put(parents.get(stack.pop()), i);
            }
            return i;
        }

    }
}
