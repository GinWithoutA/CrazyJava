package com.ginwithouta.algorithm.class04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.logging.Level;

/**
 * @Package : com.ginwithouta.algorithm.class04
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周三
 * @Desc : 给你一堆的线段，告诉你起始位置，求这些线段中最多的重合线段能重合多少个
 * @method : 把这些线段想成在一个坐标轴上，根据开头把他们依次往后排。可以利用一个小根堆，循环这些线段，每次遍历一个线段，
 *              根据这条线段的起始位置，弹出当前堆中所有比他小或者等于它的元素，然后将自己的结束位置弹进去。那么当前堆中
 *              元素的数量，就是这条线段的重合数量。怎么理解呢？因为对于每条线段，我们只将它的结尾放到小根堆中，在遍历下
 *              一个线段的时候，我们先利用这条线段的开头判断当前堆中有多少个比当前开头小的元素。这个意思就表示比他小的元
 *              一定不会和当前的线段有重合，因为我用我的开头比较你的结尾，你的结尾比我小，我们肯定没有重合。然后把这些比
 *              它小的线段的结尾都弹出来，剩下的结尾就是那些开头比当前线段早，但是结尾比当前线段开头早的线段，那么这些选
 *              段一定会和当前的线段相交
 */
public class Code04_CoverMax {
    /**
     * 暴力方法，先找到所有线段的头和位，然后每个结尾是0.5的位置判断有多少条线段
     * @param lines
     * @return
     */
    public static int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        // 找到所有线段的开头和结尾
        for (int[] line : lines) {
            min = Math.min(min, line[0]);
            max = Math.max(max, line[1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p++) {
            int cur = 0;
            for (int[] line : lines) {
                // 当前这条线段的两端在这个点的左右两边
                if (line[0] < p && line[1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    /**
     * 正常解法，利用一个小根堆来进行
     * @param arr
     * @return
     */
    public static int maxCover2(int[][] arr) {
        Line[] lines = new Line[arr.length];
        // 构建一个Line数组
        for (int i = 0; i < arr.length; i++) {
            lines[i] = new Line(arr[i][0], arr[i][1]);
        }
        // 根据起始位置，进行线段的排序
        Arrays.sort(lines, new LeftComparator());
        // 构建一个小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (Line line : lines) {
            // 遍历每个线段，先判断当前堆中是否有比自己的起始位置短的线段
            while (!heap.isEmpty() && heap.peek() <= line.left) {
                heap.poll();
            }
            // 把自己的结束位置加进去
            heap.add(line.right);
            max = Math.max(max, heap.size());
        }
        return max;
    }
    public static class Line {
        int left, right;
        public Line(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
    public static class RightComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.right - o2.right;
        }
    }
    public static class LeftComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.left - o2.left;
        }
    }

}
