package com.ginwithouta.algorithm.class26;

/**
 * @Package : com.ginwithouta.algorithm.class26
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc : IndexTree，一个用来求累加和的数据结构，和线段树一样，0位置不使用，直接从1位置开始使用。他有两个主要的功能
 *          1）如果某个位置的值发生变化，整个数组每个位置的累加和应该怎么变化
 *          2）求某一个位置的累加和
 *          3）通过加工，可以求某一个区间范围的累加和
 *          4）线段树推到2维很难，但是indexTree推到2维很容易，即给定一个矩阵，求该矩阵内部某个区间范围的累加和
 */
public class Code01_IndexTree {
    public static class IndexTree {
        private int[] indexTree;
        public IndexTree(int size) {
            this.indexTree = new int[size + 1];
        }

        /**
         * 求某一个位置的累加和是多少
         * 每次index减去当前最右边的1所得到的值就是需要的值
         * @param index     当前位置
         * @return          当前位置累加和
         */
        public int sum(int index) {
            int res = 0;
            while (index > 0) {
                res += this.indexTree[index];
                index -= index & -index;
            }
            return res;
        }

        /**
         * 在某个位置添加对应的值，对整个indexTree只要包含了当前位置的元素都应该进行变更
         * 每次影响的范围应该是当前index加上最右边的1
         * @param index     当前位置
         * @param value     添加的值
         */
        public void add(int index, int value) {
            while (index < this.indexTree.length) {
                this.indexTree[index] += value;
                index += index & -index;
            }
        }

        /**
         * 将当前位置的值更新成给定的值
         * @param index     当前位置
         * @param value     目标值
         */
        public void update(int index, int value) {
            int addValue = (index == 1 ? this.indexTree[index] : sum(index) - sum(index - 1)) - value;
            this.add(index, addValue);
        }


        /**
         * 求指定区间范围内的累加和
         * @param left      区间左边界
         * @param right     区间右边界
         * @return          区间内部的累加和
         */
        public int cumulativeSum(int left, int right) {
            int res = right < this.indexTree.length ? this.sum(right) : 0;
            res -= left > 1 ? this.sum(left - 1) : 0;
            return res;
        }

    }
}
