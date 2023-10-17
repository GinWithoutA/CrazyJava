package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周五
 * @Desc :
 */
public class Problem_0035_ValidSudoku {
    public static boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] column = new boolean[9][9];
        boolean[][] bucket = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ('.' == board[i][j]) {
                    continue;
                }
                int indexBucket = (i / 3) * 3 + (j / 3);
                if (row[i][board[i][j] - '1'] || column[j][board[i][j] - '1'] || bucket[indexBucket][board[i][j] - '1']) {
                    return false;
                }
                row[i][board[i][j] - '1'] = true;
                column[j][board[i][j] - '1'] = true;
                bucket[indexBucket][board[i][j] - '1'] = true;
            }
        }
        return true;
    }
}
