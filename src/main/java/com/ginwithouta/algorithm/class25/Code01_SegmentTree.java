package com.ginwithouta.algorithm.class25;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.NetworkInterface;

/**
 * @Package : com.ginwithouta.algorithm.class25
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc : 线段树：区间内的更新，添加，查找
 */
public class Code01_SegmentTree {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SegmentTree {
        private int maxN;
        // 新存储的数组（不使用0位置，从1开始存放数据）
        private int[] arr;
        // 懒加载的任务列表，每一个位置的元素表示在当前位置对应的treeArr范围上添加lazy[cur]的值
        private int[] lazy;
        // 线段树数组
        private int[] treeArr;
        // 需要更新的值
        private int[] updateValue;
        // 当前位置的值是否是更新
        private boolean[] valid;

        public SegmentTree(int[] arr) {
            this.maxN = arr.length;
            this.arr = new int[this.maxN + 1];
            for (int i = 1; i < this.arr.length; i++) {
                this.arr[i] = arr[i];
            }
            this.lazy = new int[this.maxN << 2];
            this.treeArr = new int[this.maxN << 2];
            this.updateValue = new int[this.maxN << 2];
            this.valid = new boolean[this.maxN << 2];
        }

        /**
         * 获取当前在线段树数组中的格子的值
         * @param cur 当前线段树数组的下标
         */
        public void generateCur(int cur) {
            this.treeArr[cur] = this.treeArr[cur << 1] + this.treeArr[cur << 1 | 1];
        }

        /**
         * 递归构建线段树数组，线段树数组中的每个格子都表示左孩子的累加和加上右孩子的累加和
         * @param left          当前格子表示的累加和的起始位置
         * @param right         当前格子表示的累加和的终止位置
         * @param shouldBe      当前格子应该在线段树中的哪个位置
         */
        public void build(int left, int right, int shouldBe) {
            if (left == right) {
                this.treeArr[shouldBe] = arr[left];
                return ;
            }
            int mid = (left + right) >> 1;
            build(left, mid, shouldBe << 1);
            build(mid + 1, right, shouldBe << 1 + 1);
            this.generateCur(shouldBe);
        }

        /**
         * 下发任务，要求在给定的范围内每个值都添加相应的值
         * @param taskLeft      给定范围的左边界
         * @param taskRight     给定范围的右边界
         * @param taskValue     给定添加的目标值
         * @param left          当前格子所代表含义的左边界
         * @param right         当前格子所代表含义的右边界
         * @param cur           当前格子的下标
         */
        public void add(int taskLeft, int taskRight, int taskValue, int left, int right, int cur) {
            // 当前任务的范围大于当前格子所表示的范围，那么直接修改当前treeArr的值，然后更新lazy的值，不下发
            if (taskLeft <= left && taskRight >= right) {
                this.treeArr[cur] += taskValue * (right - left + 1);
                this.lazy[cur] += taskValue;
                return ;
            }
            // 当前任务的两个边界并没有把当前位置的范围给包含起来，需要判断需要下发到哪一个分支上
            // 下发之前，需要将现在累积的懒任务先进行下发
            int mid = (left + right) >> 1;
            this.pushDown(mid - left + 1, right - mid + 1, cur);
            if (taskLeft <= mid) {
                add(taskLeft, taskRight, taskValue, left, mid, cur << 1);
            }
            if (taskRight > mid) {
                add(taskLeft, taskRight, taskValue, mid + 1, right, cur << 1 | 1);
            }
            // 任务下发完成之后，把当前位置的值进行更新
            this.generateCur(cur);
        }

        /**
         * 下发任务，要求在给定的范围内每个值都变成相应的值
         * @param taskLeft      给定范围的左边界
         * @param taskRight     给定范围的右边界
         * @param taskValue     给定添加的目标值
         * @param left          当前格子所代表含义的左边界
         * @param right         当前格子所代表含义的右边界
         * @param cur           当前格子的下标
         */
        public void update(int taskLeft, int taskRight, int taskValue, int left, int right, int cur) {
            if (taskLeft <= left && taskRight >= right) {
                this.updateValue[cur] = taskValue;
                this.valid[cur] = true;
                this.treeArr[cur] = taskValue * (right - left + 1);
                this.lazy[cur] = 0;
                return ;
            }
            int mid = (left + right) >> 1;
            this.pushDown(mid - left + 1, right - mid + 1, cur);
            if (taskLeft <= mid) {
                this.update(taskLeft, taskRight, taskValue, left, mid, cur << 1);
            }
            if (taskRight > mid) {
                this.update(taskLeft, taskRight, taskValue, mid + 1, right, cur << 1 | 1);
            }
            this.generateCur(cur);
        }

        /**
         * 查询给定范围内所有值的累加和
         * @param taskLeft
         * @param taskRight
         * @param left
         * @param right
         * @param cur
         * @return
         */
        public long query(int taskLeft, int taskRight, int left, int right, int cur) {
            if (taskLeft <= left && taskRight >= right) {
                return this.treeArr[cur];
            }
            int mid = (left + right) >> 1;
            this.pushDown(mid - left + 1, right - mid + 1, cur);
            long ans = 0;
            if (taskLeft <= mid) {
                ans += query(taskLeft, taskRight, left, mid, cur << 1);
            }
            if (taskRight > mid) {
                ans += query(taskLeft, taskRight, mid + 1, right, cur << 1 | 1);
            }
            // 之前进行add或者update操作的时候，都是到包含的范围就停止下发了，这时候会更新自己的值，并更新累积的值
            // 因此，对于自己来说值是正确的，对于下面的孩子来说，之后收到下发的任务之后，值才是正确的，因此直接下发即可
            return ans;
        }

        /**
         * 对于当前treeArr位置的懒任务，先进行一层的任务下发
         * @param leftNum      当前格子代表的左子元素有多少个
         * @param rightNum     当前格子代表的右子元素有多少个
         * @param cur          当前位置下标
         */
        private void pushDown(int leftNum, int rightNum, int cur) {
            // 先判断是否需要更新的下发，防止在lazy下发之后被更新下发所覆盖
            if (this.valid[cur]) {
                this.treeArr[cur << 1] = this.updateValue[cur] * leftNum;
                this.treeArr[cur << 1 | 1] = this.updateValue[cur] * rightNum;
                this.lazy[cur << 1] = 0;
                this.lazy[cur << 1 | 1] = 0;
                this.valid[cur << 1] = true;
                this.valid[cur << 1 | 1] = true;
                this.valid[cur] = false;
                this.updateValue[cur << 1] = this.updateValue[cur];
                this.updateValue[cur << 1 | 1] = this.updateValue[cur];
            }
            // 当前可以进行任务的下发，在下发完成之后，重置当前位置的懒任务
            if (this.lazy[cur] != 0) {
                this.treeArr[cur << 1] += this.lazy[cur] * leftNum;
                this.treeArr[cur << 1 | 1] += this.lazy[cur] * rightNum;
                this.lazy[cur << 1] += this.lazy[cur];
                this.lazy[cur << 1 | 1] += this.lazy[cur];
                this.lazy[cur] = 0;
            }
        }
    }
}
