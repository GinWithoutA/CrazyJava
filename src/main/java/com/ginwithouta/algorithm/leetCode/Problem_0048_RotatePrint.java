package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周一
 * @Desc : 类似旋转，都是从左上角和右下角一起往里面走，不断重复就行
 */
public class Problem_0048_RotatePrint {
    public static void rotatePrint(int count) {
        if (count < 1) {
            return ;
        }
        char[][] res = new char[count][count];
        int top = 0, bot = count - 1;
        while (top <= bot) {
            process(res, top, top, bot, bot);
            top = top + 2;
            bot = bot - 2;
        }
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                System.out.print(res[i][j] + "\t");
            }
            System.out.println(" ");
        }
    }
    public static void process(char[][] matrix, int topRow, int topCol, int botRow, int botCol) {
        // 打印上面行
        for (int col = topCol; col <= botCol; col++) {
            matrix[topRow][col] = '*';
        }
        // 打印右边列
        for (int row = topRow + 1; row <= botRow; row++) {
            matrix[row][botCol] = '*';
        }
        // 打印下面行
        for (int col = botCol - 1; col >= topCol + 1; col--) {
            matrix[botRow][col] = '*';
        }
        // 打印左边行
        for (int row = botRow - 1; row > topRow + 1; row--) {
            matrix[row][topCol + 1] = '*';
        }
    }

    public static void main(String[] args) {
        rotatePrint(6);
    }
}
