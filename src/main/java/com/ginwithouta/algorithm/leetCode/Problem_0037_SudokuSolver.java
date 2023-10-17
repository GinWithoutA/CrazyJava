package com.ginwithouta.algorithm.leetCode;

import java.lang.annotation.IncompleteAnnotationException;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周五
 * @Desc :
 */
public class Problem_0037_SudokuSolver {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] area = new boolean[9][9];
        this.init(area, row, col, board);
        this.process(board, row, col, area, 0, 0);
    }
    public void init(boolean[][] area, boolean[][] row, boolean[][] col, char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                row[i][board[i][j] - '1'] = true;
                col[j][board[i][j] - '1'] = true;
                area[(i / 3) * 3 + (j / 3)][board[i][j] - '1'] = true;
            }
        }
    }

    public boolean process(char[][] board, boolean[][] row, boolean[][] col, boolean[][] area, int indexRow, int indexCol) {
        if (indexRow == 9) {
            return true;
        }
        int nextRow = indexCol == 8 ? indexRow + 1 : indexRow;
        int nextCol = indexCol == 8 ? 0 : indexCol + 1;
        if (board[indexRow][indexCol] != '.') {
            return this.process(board, row, col, area, nextRow, nextCol);
        }
        int indexBucket = (indexRow / 3) * 3 + (indexCol / 3);
        for (int index = 0; index < 9; index++) {
            if (!row[indexRow][index] && !col[indexCol][index] && !area[indexBucket][index]) {
                row[indexRow][index] = true;
                col[indexCol][index] = true;
                area[indexBucket][index] = true;
                board[indexRow][indexCol] = (char) ('1' + index);
                if (this.process(board, row, col, area, nextRow, nextCol)) {
                    return true;
                }
                row[indexRow][index] = false;
                col[indexCol][index] = false;
                area[indexBucket][index] = false;
                board[indexRow][indexCol] = '.';
            }
        }
        return false;
    }
}
