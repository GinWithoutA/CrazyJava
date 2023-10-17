package com.ginwithouta.algorithm.class29;

/**
 * @Package : com.ginwithouta.algorithm.class29
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周日
 * @Desc : 牛羊吃草看谁赢
 */
public class Code02_EatGrass {
    public static String whoWinsViolent(int grass) {
        if (grass < 5) {
            return grass == 2 ? "后手" : "先手";
        }
        for (int want = 1; want <= grass && (want << 2) <= (grass / 4); want <<= 2) {
            if ("后手".equals(whoWinsViolent(grass - want))) {
                return "先手";
            }
        }
        return "后手";
    }

    public static String whoWins(int grass) {
        if (grass < 5) {
            return grass == 2 ? "后手" : "先手";
        } else {
            return grass % 5 == 2 || grass % 5 == 0 ? "后手" : "先手";
        }
    }

    public static void main(String[] args) {
        for (int grass = 0; grass <= 50; grass++) {
            System.out.println(grass + "\t" + whoWinsViolent(grass));
        }
    }
}
