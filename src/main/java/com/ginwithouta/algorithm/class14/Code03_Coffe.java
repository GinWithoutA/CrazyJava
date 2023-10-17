package com.ginwithouta.algorithm.class14;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Package : com.ginwithouta.algorithm.class14
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周四
 * @Desc : 有一组咖啡机，给定一个arr数组，数组中的下标就是咖啡机的编号，值是咖啡机需要的工作时间。咖啡机工作完成之后可以立马喝完咖啡。同时给定一系
 *          列的杯子，一人一个，有一个洗杯机子，自然风干假设需要K时间，洗杯机只需要M时间，M <<< K，但是洗杯机一次只能洗一个杯子，中间没有冷却。问：
 *          所有人喝完咖啡并且杯子都已经干净的最短时间
 */
public class Code03_Coffe {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Machine {
        private int workTime;
        private int availableTime;
    }

    /**
     * 获取每个人喝完咖啡的最快时间
     * @param arr       咖啡机队列
     * @param peoples   所有的小人数量
     * @return
     */
    public static int[] minDrinkTime(int[] arr, int peoples) {
        PriorityQueue<Machine> workQueue = new PriorityQueue<>(Comparator.comparingInt(o -> (o.getWorkTime() + o.getAvailableTime())));
        for (int i = 0; i < arr.length; i++) {
            workQueue.add(new Machine(arr[i], 0));
        }
        // 用来存放每个人喝完的最小时间
        int[] drinks = new int[peoples];
        for (int i = 0; i < peoples; i++) {
            Machine suitableMachine = workQueue.poll();
            suitableMachine.setAvailableTime(suitableMachine.getAvailableTime() + suitableMachine.getWorkTime());
            workQueue.add(suitableMachine);
            drinks[i] = suitableMachine.getAvailableTime();
        }
        return drinks;
    }

    /**
     * 所有杯子清洗完的最短时间
     * @param arr           每个人最短喝完咖啡的时间数组
     * @param washTime      机器清洁杯子所需要的时间
     * @param dryTime       杯子风干所需要的时间
     * @param index         当前到第几个人要去洗杯子了
     * @param availableTime 洗杯子的机子在何时才会空闲
     * @return              所有杯子洗完的时间
     */
    public static int bottleCleanTime(int[] arr, int washTime, int dryTime, int index, int availableTime) {
        // baseCase，当到达arr的边界的时候，没有杯子需要洗了，直接返回0
        if (index == arr.length) {
            return 0;
        }
        // case1：当前的人选择用洗杯子的机子
        int cleanTimeWash = Math.max(arr[index], availableTime) + washTime;
        int propagationWash = bottleCleanTime(arr, washTime, dryTime, index + 1, cleanTimeWash);
        // 我洗完的时间和剩下的人洗完的时间取一个最大值
        int bestWashTime = Math.max(cleanTimeWash, propagationWash);
        // case2：当前的人选择用风干的方式
        int cleanTimeDry = arr[index] + dryTime;
        int propagationDry = bottleCleanTime(arr, washTime, dryTime, index + 1, availableTime);
        int bestDryTime = Math.max(cleanTimeDry, propagationDry);
        return Math.min(bestDryTime, bestWashTime);
    }
    public static int bestTime(int[] arr, int peoples, int washTime, int dryTime) {
        if (arr == null || arr.length == 0 || peoples <= 0 || washTime <= 0 || dryTime <= 0) {
            return 0;
        }
        // 先获取每个人最快喝完的时间
        int[] drinkTIme = minDrinkTime(arr, peoples);
        // 再获取所有杯子洗完的最短时间
        return bottleCleanTime(drinkTIme, washTime, dryTime, 0, 0);
    }

    public static int bestTimeDP(int[] arr, int peoples, int washTime, int dryTime) {
        if (arr == null || arr.length == 0 || peoples <= 0 || washTime <= 0 || dryTime <= 0) {
            return 0;
        }
        // 先获取每个人最快喝完的时间
        int[] drinkTimes = minDrinkTime(arr, peoples);
        // 两个可变参数，先利用业务需求把咖啡机的最大可用时间点计算出来
        int maxAvailableTime = 0;
        for (int drinkTime : drinkTimes) {
            maxAvailableTime += drinkTime + washTime;
        }
        int[][] dp = new int[drinkTimes.length + 1][maxAvailableTime + 1];
        // baseCase：dp的最后一行全是0
        // 每一行依赖下一行所在的人洗完的时间
        for (int index = drinkTimes.length - 1; index >= 0; index--) {
            for (int availableTime = 0; availableTime <= maxAvailableTime; availableTime++) {
                // case1：当前的人选择机器洗杯子
                int cleanTimeWash = Math.max(drinkTimes[index], availableTime) + washTime;
                // 边界判断，如果出现了越界的情况，由于知道现实中一定不可能发生，所以直接跳过
                if (cleanTimeWash > maxAvailableTime) {
                    continue;
                }
                int propagationWash = dp[index + 1][cleanTimeWash];
                dp[index][availableTime] = Math.max(cleanTimeWash, propagationWash);
                // case2：当前的人选择风干
                int cleanTimeDry = drinkTimes[index] + dryTime;
                int propagationDry = dp[index + 1][availableTime];
                int bestTimeDry = Math.max(cleanTimeDry, propagationDry);
                dp[index][availableTime] = Math.max(bestTimeDry, dp[index][availableTime]);
            }
        }
        return dp[0][0];
    }
}
