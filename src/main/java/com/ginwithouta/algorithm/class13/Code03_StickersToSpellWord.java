package com.ginwithouta.algorithm.class13;

import java.util.HashMap;

/**
 * @Package : com.ginwithouta.algorithm.class13
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周三
 * @Desc :
 */
public class Code03_StickersToSpellWord {
    public static int stickersToSpellWord(String[] stickers, String target) {
        if (stickers == null || stickers.length == 0 || target == null || target.isEmpty()) {
            return -1;
        }
        int ans = process(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     *
     * @param stickers  贴纸
     * @param target    目标字符串
     * @return 返回最小需要的贴纸数量
     */
    public static int process(String[] stickers, String target) {
        // 长度为0，不需要任何贴纸
        if (target.isEmpty()) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String rest = restString(sticker, target);
            if (rest.length() != target.length()) {
                min = Math.min(min, process(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? min : 1);
    }
    public static String restString(String sticker, String target) {
        int[] counts = new int[26];
        for (int i = 0; i < sticker.length(); i++) {
            --counts[sticker.charAt(i) - 'a'];
        }
        for (int i = 0; i < target.length(); i++) {
            ++counts[target.charAt(i) - 'a'];
        }
        StringBuilder rest = new StringBuilder();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                for (int j = 0; j < counts[i]; j++) {
                    rest.append('a' + i);
                }
            }
        }
        return rest.toString();
    }

    public static int stickersToSpellWordMethod2(String[] stickers, String target) {
        if (stickers == null || stickers.length == 0 || target == null || target.isEmpty()) {
            return -1;
        }
        int[][] counts = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            for (int j = 0; j < stickers[i].length(); j++) {
                counts[i][stickers[i].charAt(j) - 'a']++;
            }
        }
        int ans = process2(counts, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process2(int[][] stickers, String target) {
        // 长度为0，不需要任何贴纸
        if (target.isEmpty()) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int[] counts = new int[26];
        for (int i = 0; i < target.length(); i++) {
            counts[target.charAt(i) - 'a']++;
        }
        for (int[] sticker : stickers) {
            // 剪枝，每次只判断是否存在target开头的字符
            if (sticker[target.charAt(0) - 'a'] > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < sticker.length; i++) {
                    if (sticker[i] > 0) {
                        counts[i] -= sticker[i];
                        if (counts[i] > 0) {
                            stringBuilder.append(String.valueOf('a' + i).repeat(Math.max(0, counts[i])));
                        }
                    }
                }
                String rest = stringBuilder.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? min : 1);
    }

    public static int stickersToSpellWordDP(String[] stickers, String target) {
        if (stickers == null || stickers.length == 0 || target == null || target.isEmpty()) {
            return -1;
        }
        int[][] counts = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            for (int j = 0; j < stickers[i].length(); j++) {
                counts[i][stickers[i].charAt(j) - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        int ans = processDP(counts, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int processDP(int[][] stickers, String target, HashMap<String, Integer> dp) {
        // 长度为0，不需要任何贴纸
        if (target.isEmpty()) {
            return 0;
        }
        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        int min = Integer.MAX_VALUE;
        int[] counts = new int[26];
        for (int i = 0; i < target.length(); i++) {
            counts[target.charAt(i) - 'a']++;
        }
        for (int[] sticker : stickers) {
            // 剪枝，每次只判断是否存在target开头的字符
            if (sticker[target.charAt(0) - 'a'] > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < sticker.length; i++) {
                    if (sticker[i] > 0) {
                        counts[i] -= sticker[i];
                        if (counts[i] > 0) {
                            stringBuilder.append(String.valueOf('a' + i).repeat(Math.max(0, counts[i])));
                        }
                    }
                }
                String rest = stringBuilder.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        int result = min + (min == Integer.MAX_VALUE ? min : 1);
        dp.put(target, result);
        return result;
    }

    public static void main(String[] args) {
        String str = "asdfdsa";
        System.out.println(str.charAt(2) - 'a');
    }
}
