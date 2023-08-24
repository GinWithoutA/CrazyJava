package com.ginwithouta.algorithm.class01;

/**
 * @Package : com.ginwithouta.algorithm.class01
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周六
 * @Desc :
 */
public class Code07_EvenTimesOddTimes {
    /**
     * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
     *
     * @method 通过异或操作，由于偶数个相同的数异或完一定是0，剩下的数一定是奇数的那个数
     *
     * @param arr
     */
    public static void identifyOddNumber(int[] arr) {
        int eor = 0;
        for (int num : arr) {
            eor = eor ^ num;
        }
        System.out.println(eor);;
    }
    /**
     * 找到一个整数中最右边的1
     *
     * @method 首先对这个数取反然后加1，那么和自己与，除了最右边的1，其他部分全部都是0
     *
     * @param num
     */
    public static void identifyRightestOne(int num) {
        int result = num & (-num), index = 0;
        while (result != 0) {
            result = result >> 1;
            index++;
        }
        System.out.println(index);
    }
    /**
     * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
     *
     * @method 和前面一样，假设这两个奇数分别是a和b，首先定义一个 eor，全部异或一边，最终的结果就是a^b，
     *         记得，异或的规则是相同是0，不同是1，那么我们就能拿这个结果的随便一位1来下手，也就是说，对
     *         于a和b，这两个数一定在这一位上不同的。那么扩展这一位数，我们针对整个数组，也可以分为两个部
     *         分，一个部分是这一位为1，另一个部分是这一位不是1。再用另一个变量eor'去与其中一部分的所有数
     *         ，那么肯定会把a或者b拽出来，最后在异或eor'，就会得到b
     *
     * @param arr
     */
    public static void identifyTwoOddNumber(int[] arr) {
        int eor = 0;
        for (int num : arr) {
            eor ^= num;
        }
        int rightOne = eor & (-eor);
        int eorPrime = 0;
        for (int num : arr) {
            if ((rightOne & num) != 0) {
                eorPrime ^= num;
            }
        }
        System.out.println(eor + " " + (eor ^ eorPrime));
    }
    public static void main(String[] args) {
        identifyRightestOne(10);
    }
}
