package com.ginwithouta.algorithm.class09;

/**
 * @Package : com.ginwithouta.algorithm.class09
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周六
 * @Desc :
 */
public class Code02_FriendCircle {
    public static class UnionSet {
        int[] parent;
        int[] size;
        int[] help;
        int sets;
        public UnionSet(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            int temp = 0;
            while (i != parent[i]) {
                help[temp++] = i;
                i = parent[i];
            }
            while (temp > 0) {
                parent[help[--temp]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int parentI = find(i), parentJ = find(j);
            if (parentI != parentJ) {
                if (size[parentI] > size[parentJ]) {
                    size[parentI] += size[parentJ];
                    parent[j] = i;
                } else {
                    size[parentJ] += size[parentI];
                    parent[i] = j;
                }
                sets--;
            }
        }

    }

    public int friendCircle(int[][] array) {
        UnionSet unionSet = new UnionSet(array.length);
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i][j] == 1) {
                    unionSet.union(i, j);
                }
            }
        }
        return unionSet.sets;
    }

    public static void test(int a) {

    }

    public static void test1() {
        UnionSet n = new UnionSet(6);
        test(n.find(1));
    }
}
