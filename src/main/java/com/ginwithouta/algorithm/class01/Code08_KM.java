package com.ginwithouta.algorithm.class01;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Package : com.ginwithouta.algorithm.class01
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周六
 * @Desc :
 */
public class Code08_KM {
    /**
     * 一个数组中有一种数出现了K次，K<M，其他数都出现了M,M>1，其他数都出现了M,M>1次，怎么找到并打印这种数，
     * 如果这个数没有出现K次，返回-1
     *
     * @method 可以通过一个32位的标志数组来计算。首先我们计算每一位1出现的次数，得到了之后我们可以判断，
     *         如果当前位除以M是整除，那么说明这个数在这一位不是1（如果是1，那么肯定是M的整数倍再加上K）
     *         ，如果不是整除，那么说明这个数在这一位是1，通过这样循环的方式，就可以得到这个数
     *
     * @param arr
     */
    public static int onlyKTimes(int[] arr, int k, int m) {
        int[] t = new int[32];
        for (int num: arr) {
            for (int i = 0; i < 32; ++i) {
                if (((num >> i) & 1) != 0) {
                    t[i]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (t[i] % m != 0) {
                if (t[i] % m != k) {
                    return -1;
                }
                // 每次发现某一位有1，说明这个数在这一位上有1，就往那一位上加1
                ans |= (1 << i);
            }
        }
        // 当要找的那一个数一定出现k次的时候，0是不会对程序有影响的，因为我们只需要通过其他的数就可以进行判断了
        // 当k发生变化，如果出现了0，此时我们就不能通过别的数来判断是否出现了k次，因为此时t[i] % m 本身就不一定
        // 等于k，再加上0本来就没有1，因此会直接返回-1，所以需要再加一个额外的判断
        if (ans == 0) {
            int count = 0;
            for (int num : arr) {
                if (num == 0) {
                    count++;
                }
            }
            if (count != k) {
                return -1;
            }
        }
        return ans;
    }
    public static int test(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>(arr.length);
        for (int num: arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (int num: map.keySet()) {
            if (map.get(num) == k) {
                return num;
            }
        }
        return -1;
    }
    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        int kTimesNum = randomNumber(range);
        int times = Math.random() < 0.5 ? k : ((int) (Math.random() * (m - 1)) + 1);
        int kindNums = (int) (Math.random() * maxKinds) + 2;
        int[] arr = new int[times + ((kindNums - 1) * m)];
        int index = 0;
        for(; index < times; ++index) {
            arr[index] = kTimesNum;
        }
        --kindNums;
        HashSet<Integer> set = new HashSet<>(arr.length);
        set.add(kTimesNum);
        while (kindNums != 0) {
            int insertNum;
            // 这里用do while 能够少调用 randomNumber 一次
            do {
                insertNum = randomNumber(range);
            } while (set.contains(insertNum));
            set.add(insertNum);
            --kindNums;
            for (int i = 0; i < m; ++i) {
                arr[index++] = insertNum;
            }
        }
        // 打乱
        for (int i = 0; i < arr.length; i++) {
            int j = (int) (Math.random() * arr.length);
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }
    public static int randomNumber(int range) {
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }
    public static void main(String[] args) {
        int kinds = 4;
        int range = 200;
        int testTime = 100000;
        int max = 9;
        for (int i = 0; i < testTime; i++) {
            int a = (int)(Math.random() * max) + 1;
            int b = (int)(Math.random() * max) + 1;
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m++;
            }
            int[] arr = randomArray(kinds, range, k, m);
            int ans1 = onlyKTimes(arr, k, m);
            int ans2 = test(arr, k, m);
            if (ans1 != ans2) {
                System.out.println("出错了！");
                return ;
            }
        }
    }
}
