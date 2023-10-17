package com.ginwithouta.algorithm.class29;

/**
 * @Package : com.ginwithouta.algorithm.class29
 * @Author : NONO Wang
 * @Date : 2023 - 10月 - 周日
 * @Desc : 小虎买苹果
 */
public class Code01_AppleMinBags {
    public static int appleMinBagsViolent(int require) {
        if (require < 6) {
            return -1;
        }
        int bag8 = require >> 3;
        int rest = require - (bag8 << 3);
        while (bag8 >= 0) {
            if (rest % 6 == 0) {
                return bag8 + (rest / 6);
            } else {
                bag8--;
                rest += 8;
            }
        }
        return -1;
    }

    public static int appleMinBags(int require) {
        if (require < 6) {
            return -1;
        }
        if (require < 17) {
            return require == 6 || require == 8 ? 1 : (require == 12 || require == 14 || require == 16 ? 2 : -1);
        } else {
            return require % 2 == 0 ? require / 8 + 1 : -1;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 200; i++) {
            System.out.println(i + "\t" + appleMinBagsViolent(i));
        }
    }
}
