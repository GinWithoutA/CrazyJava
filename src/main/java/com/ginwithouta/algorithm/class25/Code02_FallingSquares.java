package com.ginwithouta.algorithm.class25;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * @Package : com.ginwithouta.algorithm.class25
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc : leetCode原题699
 */
public class Code02_FallingSquares {
    public static class SegmentTree {
        public int maxN;
        public int[] max;
        public int[] updateValue;
        public boolean[] valid;
        public SegmentTree(int size) {
            this.maxN = size + 1;
            this.max = new int[this.maxN << 2];
            this.updateValue = new int[this.maxN << 2];
            this.valid = new boolean[this.maxN << 2];
        }
        public void generateCur(int cur) {
            this.max[cur] = Math.max(this.max[cur << 1], this.max[cur << 1 | 1]);
        }
        public void pushDown(int leftNum, int rightNum, int cur) {
            if (this.valid[cur]) {
                this.valid[cur << 1] = true;
                this.valid[cur << 1 | 1] = true;
                this.valid[cur] = false;
                this.updateValue[cur << 1] = this.updateValue[cur];
                this.updateValue[cur << 1 | 1] = this.updateValue[cur];
                this.max[cur << 1] = this.updateValue[cur];
                this.max[cur << 1 | 1] = this.updateValue[cur];
            }
        }
        public void update(int taskLeft, int taskRight, int taskValue, int left, int right, int cur) {
            if (taskLeft <= left && taskRight >= right) {
                this.updateValue[cur] = taskValue;
                this.valid[cur] = true;
                this.max[cur] = taskValue;
                return ;
            }
            int mid = (left + right) >> 1;
            pushDown(mid - left + 1, right - mid + 1, cur);
            if (taskLeft <= mid) {
                this.update(taskLeft, taskRight, taskValue, left, mid, cur << 1);
            }
            if (taskRight > mid) {
                this.update(taskLeft, taskRight, taskValue, mid + 1, right, cur << 1 | 1);
            }
            this.generateCur(cur);
        }
        public int query(int taskLeft, int taskRight, int left, int right, int cur) {
            if (taskLeft <= left && taskRight >= right) {
                return this.max[cur];
            }
            int mid = (left + right) >> 1;
            this.pushDown(mid - left + 1, right - mid + 1, cur);
            int leftAns = 0, rightAns = 0;
            if (taskLeft <= mid) {
                leftAns = query(taskLeft, taskRight, left, mid, cur << 1);
            }
            if (taskRight > mid) {
                rightAns = query(taskLeft, taskRight, mid + 1, right, cur << 1 | 1);
            }
            return Math.max(leftAns, rightAns);
        }
    }
    public HashMap<Integer, Integer> index(int[][] positions) {
        TreeSet<Integer> pos = new TreeSet<>();
        for (int[] arr : positions) {
            pos.add(arr[0]);
            pos.add(arr[0] + arr[1] - 1);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (Integer index : pos) {
            map.put(index, ++count);
        }
        return map;
    }

    public List<Integer> fallingSquares(int[][] positions) {
        HashMap<Integer, Integer> map = index(positions);
        SegmentTree segmentTree = new SegmentTree(map.size());
        int max = 0;
        List<Integer> res = new ArrayList<>();
        for(int[] arr : positions){
            int left = map.get(arr[0]);
            int right = map.get(arr[0] + arr[1] - 1);
            int height = segmentTree.query(left, right, 1, map.size(), 1) + arr[1];
            max = Math.max(max, height);
            res.add(max);
            segmentTree.update(left, right, height, 1, map.size(), 1);

        }
        return res;
    }
}
