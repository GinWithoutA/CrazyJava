package com.ginwithouta.algorithm.leetCode;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周一
 * @Desc : 和前面的旋转矩阵类似，从左上开始，右下开始，不断往中间推进
 */
public class Problem_0054_SpiralMatrix {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int topRow = 0, topCol = 0, botRow = matrix.length - 1, botCol = matrix[0].length - 1;
        while (topRow <= botRow && topCol <= botCol) {
            process(matrix, ans, topRow, topCol, botRow, botCol);
            topRow++;
            topCol++;
            botRow--;
            botCol--;
        }
        return ans;
    }
    public static void process(int[][] matrix, List<Integer> ans, int topRow, int topCol, int botRow, int botCol) {
        if (topRow == botRow) {
            while (topCol <= botCol) {
                ans.add(matrix[topRow][topCol++]);
            }
        } else if (topCol == botCol) {
            while (topRow <= botRow) {
                ans.add(matrix[topRow++][topCol]);
            }
        } else {
            int tempCol = topCol, tempRow = topRow;
            while (topCol < botCol) {
                ans.add(matrix[topRow][topCol++]);
            }
            while (topRow < botRow) {
                ans.add(matrix[topRow++][botCol]);
            }
            while (botCol > tempCol) {
                ans.add(matrix[botRow][botCol--]);
            }
            while (botRow > tempRow) {
                ans.add(matrix[botRow--][tempCol]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(spiralOrder(matrix));
    }
}
