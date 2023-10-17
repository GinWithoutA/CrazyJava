package com.ginwithouta.algorithm.class18;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Package : com.ginwithouta.algorithm.class18
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周日
 * @Desc : arr是货币数组，每一个值都是正数，在给定一个aim，每个值都认为是一张货币，求组成aim的最少货币数量
 */
public class Code04_MinCoinsNumber {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info {
        private int[] value;
        private int[] zhang;
    }
    public static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!count.containsKey(arr[i])) {
                count.put(arr[i], 1);
            } else {
                count.put(arr[i], count.get(arr[i]) + 1);
            }
        }
        int[] value = new int[count.size()], zhang = new int[value.length];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            value[index] = entry.getKey();
            zhang[index++] = entry.getValue();
        }
        return new Info(value, zhang);
    }
    public static int minCoinsNumberDP(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0 || arr[0] != aim) {
            return 0;
        }
        Info info = getInfo(arr);
        int[][] dp = new int[info.getValue().length + 1][aim + 1];
        dp[dp.length - 1][0] = 0;
        for (int rest = 1; rest < dp[0].length; rest++) {
            dp[dp.length - 1][rest] = Integer.MAX_VALUE;
        }
        for (int index = dp.length - 2; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int min = Integer.MAX_VALUE;
                for (int zhang = 0; zhang <= info.getZhang()[index] && zhang * info.getValue()[index] <= rest; zhang++) {
                    if (rest - info.getValue()[index] != Integer.MAX_VALUE) {
                        min = Math.min(min, zhang + dp[index + 1][rest - info.getValue()[index]]);
                    }
                }
               dp[index][rest] =  min;
            }
        }
        return dp[0][aim];
    }
    public static int process(int[] value, int[] zhangs, int index, int rest) {
        if (index == value.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        for (int zhang = 0; zhang <= zhangs[index] && zhang * value[index] <= rest; zhang++) {
            min = Math.min(min, zhang + process(value, zhangs, index + 1, rest - value[index]));
        }
        return min;
    }

    /**
     * 用时间窗口消除枚举的情况
     * @param arr
     * @param aim
     * @return
     */
    public static int minCoinsNumDpSlidingWindow(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[][] dp = new int[info.getValue().length + 1][aim + 1];
        dp[info.getValue().length][0] = 0;
        for (int rest = 1; rest <= aim; rest++) {
            dp[info.getValue().length][rest] = Integer.MAX_VALUE;
        }
        for (int index = info.getValue().length - 1; index >= 0; index--) {
            // 分组，利用面值从左到右更新，总共能分的组的数量就是从0开始到当前面值，也就是[0, value[i])
            // 注意可能出现当前的面值超过了dp表的长度，如果超过了就是按照dp表的长度来分组，也就是原始方式
            for (int mod = 0; mod < Math.min(aim + 1, info.getValue()[index]); mod++) {
                // 开始利用存放最小值的滑动窗口来更新dp表格，因为我们每次返回都是通过获得最小值返回的
                LinkedList<Integer> minWindow = new LinkedList<>();
                minWindow.push(mod);
                // 初始化[index][0]位置的值，这一个位置的值是zhang == 0的时候的值
                dp[index][mod] = dp[index + 1][mod];
                for (int left = mod + info.getValue()[index]; left <= aim; left += info.getValue()[index]) {
                    // 更新滑动窗口的值
                    // 如果当前窗口的值不为空，并且记录的最小值不是非法的，并且记录的最小值加上补偿值还比我的小，那我就不踢
                    while (!minWindow.isEmpty() && dp[index + 1][minWindow.peekLast()] != Integer.MAX_VALUE
                            && ((left - minWindow.peekLast()) / info.getValue()[index]) + dp[index + 1][minWindow.peekLast()] >= dp[index + 1][left]) {
                        minWindow.pollLast();
                    }
                    minWindow.push(left);
                    // 如果钱的张数超了，记得退出
                    // info.getZhang()[index] + 1的含义是如果当前minWindow的第一个值的下标刚好就是我当前的rest - （所有的张数 + 1），说明它超边界了，删除
                    int overdue = left - info.getValue()[index] * (info.getZhang()[index] + 1);
                    if (minWindow.peekFirst() == overdue) {
                        minWindow.pollFirst();
                    }
                    // 于是当前的值就等于我前面找的最小值加上补偿值
                    // 补偿值的计算方法就是当前的rest减去记录的最小值的rest的差除以当前的面额
                    dp[index][left] = ((left - minWindow.peekFirst()) / info.getValue()[index]) + dp[index + 1][minWindow.peekFirst()];
                }
            }
        }
        return dp[0][aim];
    }
}
