package com.ginwithouta.algorithm.class07;

/**
 * @Package : com.ginwithouta.algorithm.class07
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周二
 * @Desc : 给你一张纸，对折一次，有一个凹折痕，再对折，有两个凹折痕，一个凸折痕，对折n次，打印出所有的折痕
 */
public class Code06_PrintAllFolds {
    public static void printFolds(int N) {
        if (N <= 0) {
            return ;
        }
        process(1, N, true);
    }
    public static void process(int i, int N, boolean flag) {
        if (i > N) {
            return ;
        }
        process(i + 1, N, true);
        System.out.print((flag ? "凹" : "凸") + " ");
        process(i + 1, N, false);
    }

    public static void main(String[] args) {
        int N = 2;
        printFolds(N);
    }

}
