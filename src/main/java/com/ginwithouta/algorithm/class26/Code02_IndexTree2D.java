package com.ginwithouta.algorithm.class26;

/**
 * @Package : com.ginwithouta.algorithm.class26
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc :
 */
public class Code02_IndexTree2D {
    public static class IndexTree2D {
        private int[][] indexTree;
        public IndexTree2D(int row, int col) {
            this.indexTree = new int[row + 1][col + 1];
        }

        /**
         * 返回从[1, 1]开始到[row, col]的累加和
         * @param row
         * @param col
         * @return
         */
        public int sum(int row, int col) {
            int res = 0;
            for (int x = row; x > 0; x -= x & -x) {
                for (int y = col; y > 0; y -= y & -y) {
                    res += this.indexTree[x][y];
                }
            }
            return res;
        }

        /**
         * 在给定的位置添加上给定的值，更新全局的indexTree
         * @param row
         * @param col
         * @param value
         */
        public void add(int row, int col, int value) {
            for (int x = row; x < this.indexTree.length; x += x & -x) {
                for (int y = col; y < this.indexTree[0].length; y += y & -y) {
                    this.indexTree[x][y] += value;
                }
            }
        }

        /**
         * 将给定位置的值修改为给定的值
         * @param row
         * @param col
         * @param value
         */
        public void update(int row, int col, int value) {
            int addValue = value - this.indexTree[row][col];
            this.add(row, col, addValue);
        }

        /**
         * 返回给定区间内的累加和
         * @param leftRow   左上角的行号
         * @param leftCol   左上角的列好
         * @param rightRow  右下角的行号
         * @param rightCol  右小角的列号
         * @return
         */
        public int cumulativeSum(int leftRow, int leftCol, int rightRow, int rightCol) {
            int res = this.sum(rightRow, rightCol);
            res -= this.sum(rightRow, leftCol);
            res -= this.sum(leftRow, rightCol);
            res += this.sum(leftRow, leftCol);
            return res;
        }
    }
}
