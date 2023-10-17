package com.ginwithouta.algorithm.class19;

/**
 * @Package : com.ginwithouta.algorithm.class19
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc : 给定一个二维数组Matrix，其中的值不是0就是1，返回全部由1组成的子矩形的数量
 * @method : 有一个技巧，就是要知道数组的数量怎么求：给定一个大小是N * M的矩阵，求所有的子数组的数量：
 *          我们先看第一行有多少个子数组，不难发现，第一行的子数组个数就是以第一个元素为开头，向后延伸能产生的所有子数组，M个
 *          然后第二个元素开头，向后延伸，能产生的数量是M - 1，以此类推，是一个等差数列，总的个数就是(M * (M + 1)) / 2
 *          引入第二行，由于第一行已经算过了，我们只需要算行数为2的子矩阵有多少个，和第一行一样，以第一个元素为基址，向后延伸，
 *          能产生M个矩阵，以此类推，也能产生(M * (M + 1)) / 2个数的矩形。引入所有的行，能产生的矩形的个数就是N * (M * (M + 1)) / 2
 */
public class Code05_CountSubMatrixWithAllOnes {
    public static int sunOfSubArrayMinimums(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        // 需要一个地基数组，用来判断矩阵中1的元素是否能连通
        int[] help = new int[matrix[0].length], stack = new int[matrix[0].length];
        int all = 0, top = -1;
        for (int i = 0; i < matrix.length; i++) {
            // 更新以每一层为地基的help数组
            for (int j = 0; j < help.length; j++) {
                help[j] = matrix[i][j] == 0 ? 0 : help[j] + 1;
            }
            // 利用单调栈判断连通区域
            for (int index = 0; index < help.length; index++) {
                while (top != -1 && help[stack[top]] >= help[index]) {
                    int cur = stack[top--];
                    if (help[cur] > help[index]) {
                        // 计算当前的连通区域有多高，记得要减去左右两边大的那个值，因为左右两边计算的时候会计算他们自己的高度的值，如果现在计算会重复
                        int height = help[cur] - Math.max(top == -1 ? 0 : help[stack[top]], help[index]);
                        // 计算连通区域的宽度
                        int width = top == -1 ? index : index - stack[top] - 1;
                        // 套公式，算有多少个子矩形
                        all += height * (width * (width + 1)) / 2;
                    }
                }
                stack[++top] = index;
            }
            while (top != -1) {
                int cur = stack[top--];
                int height = help[cur] - Math.max(top == -1 ? 0 : help[stack[top]], 0);
                int width = top == -1 ? help.length : help.length - stack[top] - 1;
                all += height * (width * (width + 1)) / 2;
            }
        }
        return all;
    }
}
