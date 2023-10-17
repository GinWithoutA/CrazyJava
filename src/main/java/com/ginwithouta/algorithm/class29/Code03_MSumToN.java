package com.ginwithouta.algorithm.class29;

/**
 * @Package : com.ginwithouta.algorithm.class29
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周日
 * @Desc : 看一个数是否可以由连续正数和累加生成
 */
public class Code03_MSumToN {
    public static boolean continuePositiveViolent(int num) {
        if (num < 3) {
            return false;
        }
        for (int start = 1; start <= num; start++) {
            int sum = start;
            for (int i = start + 1; i <= num; i++) {
                if (sum + i < num) {
                    sum = sum + i;
                } else if (sum + i > num) {
                    break;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean continuePositive(int num) {
        return (num & (num - 1)) != 0;
    }

    public static void main(String[] args) {
        // for (int i = 0; i <= 200; i++) {
        //     System.out.println(i + "\t" + continuePositiveViolent(i));
        // }
        System.out.println(12 & (~12 + 1));
    }
}
