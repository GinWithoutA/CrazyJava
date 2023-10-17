package com.ginwithouta.algorithm.class12;

import com.sun.nio.sctp.SendFailedNotification;
import org.ietf.jgss.GSSManager;

import java.util.HashMap;

/**
 * @Package : com.ginwithouta.algorithm.class12
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周二
 * @Desc : 给定一堆纸牌，顺序排放，每个纸牌有分数。两个人，都绝顶聪明，每次都只能从左右两边选一张纸牌，问谁能赢
 */
public class Code02_CardsInLine {
    public static int cardsMethod1(int[] cards) {
        if (cards == null || cards.length == 1) {
            return 0;
        }
        int firstHand = first(cards, 0, cards.length - 1);
        int secondHand = second(cards, 0, cards.length - 1);
        return Math.max(firstHand, secondHand);
    }

    /**
     * 先手函数
     * @param cards 纸牌列表
     * @param left  左边纸牌
     * @param right 右边纸牌 （左右纸牌用来判断拿那一边的纸牌）
     * @return
     */
    public static int first(int[] cards, int left, int right) {
        if (left == right) {
            return cards[left];
        }
        // 我拿了左边的牌
        int chooseLeft = cards[left] + second(cards, left + 1, right);
        // 我拿了右边的牌
        int chooseRight = cards[right] + second(cards, left, right - 1);
        return Math.max(chooseLeft, chooseRight);
    }

    /**
     * 后手函数（后手函数的主视角是先手的人）
     * @param cards 纸牌列表
     * @param left  左边纸牌
     * @param right 右边纸牌 （左右纸牌用来判断拿那一边的纸牌）
     * @return
     */
    public static int second(int[] cards, int left, int right) {
        if (left == right) {
            // 如果只剩一张牌，因为我是后手，肯定被对手拿走了，我自己得到0
            return 0;
        }
        // 他把左边的牌拿走了，我就得不到我左边的牌的分数，那么我在剩余的牌里面努力获取最大分
        int heChooseLeft = first(cards, left - 1, right);
        // 他把右边的牌拿走了，我就得不到我右边的牌的分数，那么我在剩余的牌里面努力获取最大分
        int heChooseRight = first(cards, left, right - 1);
        // 最后一定会得到最小的，因为他拿的牌的目标一定是要让我得到的分数最小
        return Math.min(heChooseRight, heChooseLeft);
    }

    /**
     * 第二种方法，加缓存
     * @param cards
     * @return
     */
    public static int cardsMethod2(int[] cards) {
        if (cards == null || cards.length == 1) {
            return 0;
        }
        int[][] firstMap = new int[cards.length][cards.length], secondMap = new int[cards.length][cards.length];
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards.length; j++) {
                firstMap[i][j] = -1;
                secondMap[i][j] = -1;
            }
        }
        int firstHand = firstMethod2(cards, 0, cards.length - 1, firstMap);
        int secondHand = secondMethod2(cards, 0, cards.length - 1, secondMap);
        return Math.max(firstHand, secondHand);
    }

    /**
     * 先手函数
     * @param cards 纸牌列表
     * @param left  左边纸牌
     * @param right 右边纸牌 （左右纸牌用来判断拿那一边的纸牌）
     * @return
     */
    public static int firstMethod2(int[] cards, int left, int right, int[][] firstMap) {
        if (firstMap[left][right] != -1) {
            return firstMap[left][right];
        }else if (left == right) {
            firstMap[left][right] = cards[left];
        } else {
            // 我拿了左边的牌
            int chooseLeft = cards[left] + second(cards, left + 1, right);
            // 我拿了右边的牌
            int chooseRight = cards[right] + second(cards, left, right - 1);
            firstMap[left][right] = Math.max(chooseLeft, chooseRight);
        }
        return firstMap[left][right];
    }

    /**
     * 后手函数（后手函数的主视角是先手的人）
     * @param cards 纸牌列表
     * @param left  左边纸牌
     * @param right 右边纸牌 （左右纸牌用来判断拿那一边的纸牌）
     * @param secondMap 后手缓存
     * @return
     */
    public static int secondMethod2(int[] cards, int left, int right, int[][] secondMap) {
        if (secondMap[left][right] != -1) {
            return secondMap[left][right];
        } else if (left == right) {
            // 如果只剩一张牌，因为我是后手，肯定被对手拿走了，我自己得到0
            secondMap[left][right] = cards[left];
        } else {
            // 他把左边的牌拿走了，我就得不到我左边的牌的分数，那么我在剩余的牌里面努力获取最大分
            int heChooseLeft = first(cards, left - 1, right);
            // 他把右边的牌拿走了，我就得不到我右边的牌的分数，那么我在剩余的牌里面努力获取最大分
            int heChooseRight = first(cards, left, right - 1);
            // 最后一定会得到最小的，因为他拿的牌的目标一定是要让我得到的分数最小
            secondMap[left][right] = Math.min(heChooseRight, heChooseLeft);
        }
        return secondMap[left][right];
    }

    /**
     * 第三种方法，动态规划
     * @param cards
     * @return
     */
    public static int cardsMethod3(int[] cards) {
        if (cards == null || cards.length == 1) {
            return 0;
        }
        int[][] firstMap = new int[cards.length][cards.length], secondMap = new int[cards.length][cards.length];
        for (int i = 0; i < cards.length; i++) {
            firstMap[i][i] = cards[i];
        }
        for (int startCol = 1; startCol < cards.length; startCol++) {
            int L = 0;
            int R = 1;
            while (R < cards.length) {
                firstMap[L][R] = Math.max(secondMap[L + 1][R], secondMap[L][R - 1]);
                secondMap[L][R] = Math.min(firstMap[L + 1][R], secondMap[L][R - 1]);
                L++;
                R++;
            }
        }
        return Math.max(firstMap[0][cards.length - 1], secondMap[0][cards.length - 1]);
    }

}
