package com.ginwithouta.algorithm.leetCode;

import java.security.AlgorithmConstraints;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周一
 * @Desc : 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 */
public class Problem_0048_RotateImage {
    public void rotate(int[][] matrix) {
        int top = 0, bot = matrix.length - 1;
        while (top < bot) {
            process(matrix, top, top, bot, bot);
            top++;
            bot--;
        }
    }

    /**
     * 每四个一组，轮流进行旋转
     * @param matrix
     * @param topRow
     * @param topCol
     * @param botRow
     * @param botCol
     */
    public void process(int[][] matrix, int topRow, int topCol, int botRow, int botCol) {
        int temp;
        for (int group = 0; group < botCol - topCol; group++) {
            temp = matrix[topRow][topCol + group];
            matrix[topRow][topCol + group] = matrix[botRow - group][topCol];
            matrix[botRow - group][topCol] = matrix[botRow][botCol - group];
            matrix[botRow][botCol - group] = matrix[topRow + group][botCol];
            matrix[topRow + group][botCol] = temp;
        }
    }

}
