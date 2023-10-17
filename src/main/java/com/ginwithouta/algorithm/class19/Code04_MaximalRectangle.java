package com.ginwithouta.algorithm.class19;

import java.util.Stack;

/**
 * @Package : com.ginwithouta.algorithm.class19
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc : 给定一个二维数组Matrix，其中的值不是0就是1，返回全部由1组成的最大子矩形的内部有多少个1
 * @method : 以二维数组中的每一行作为地基，如果地基碰到0，直接是0，如果碰到1，那么对应位置的值加1，看这个地基能延伸多长
 */
public class Code04_MaximalRectangle {
    public static int maximalRectangle(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        // 一个地基数组
        int[] help = new int[matrix[0].length];
        // 单调栈
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < help.length; j++) {
                help[j] = matrix[i][j] == 0 ? 0 : help[j] + matrix[i][j];
            }
            // 接下来的过程和前面的计算直方图最大面积一样，以每个位置的值作为基准值，看可以延伸多远
            for (int index = 0; index < help.length; index++) {
                while (!stack.isEmpty() && help[stack.peek()] >= help[index]) {
                    int cur = stack.pop();
                    max = Math.max(max, (stack.isEmpty() ? index : index - stack.peek() - 1) * help[cur]);
                }
                stack.push(index);
            }
            while (!stack.isEmpty()) {
                int cur = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? help.length : help.length - stack.peek() - 1) * help[cur]);
            }
        }
        return max;
    }
}
