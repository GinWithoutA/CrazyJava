package com.ginwithouta.algorithm.leetCode;

/**
 * @Package : com.ginwithouta.algorithm.leetCode.class01
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周二
 * @Desc :
 */
public class Problem_0012_IntegerToRoman {
    public String intToRoman(int num) {
        String[][] map = {{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                {"", "M", "MM", "MMM"}};
        StringBuilder sb = new StringBuilder();
        sb.append(map[3][num / 1000 % 10]);
        sb.append(map[2][num / 100 % 10]);
        sb.append(map[1][num / 10 % 10]);
        sb.append(map[0][num % 10]);
        return sb.toString();
    }
}
