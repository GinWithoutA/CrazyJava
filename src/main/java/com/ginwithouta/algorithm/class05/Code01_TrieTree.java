package com.ginwithouta.algorithm.class05;

import java.util.Optional;

/**
 * @Package : com.ginwithouta.algorithm.class05
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周四
 * @Desc : 前缀树
 *      比如对于一个字符串数组，想把他们加到一颗树里，我们可以从第一个字符串开始，遍历每个字符，把每个字符存在路径上。如果这个字符已经在
 *      路径上，则去看下一个字符，如果没有，则创建字符路径，以此类推。有一个固定的根节点，每次有新的字符会创建新的路径。对于这个节点，我们
 *      保存两个值，p和e，p表示在加路径的过程中这个节点经过了几次，e表示这个节点有多少个字符串以它结尾
 *      那么我们就可以通过这个树完成：
 *      1）当前字符串中，有多少个xx字符串？从头开始，找，如果找得到，答案就是e的值，如果找不到，就是没有
 *      2）当前字符串中，有多少以xx为开头的字符串？从头开始找，如果找得到前缀的最后一个字母，那么这个字母所在的节点的p就是答案
 *      前缀树在用来找字符串个数，字符串前缀的时候，很方便
 */
public class Code01_TrieTree {
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public static class Node1 {
        public int pass;
        public int end;
        public Node1[] nexts;

        /**
         * 因为每个节点相当于会存一个字符，那么找这个字符所在的位置是不是有路，直接用char-'a'去判断nexts里面是不是空
         * @param pass 当前节点经过了多少次
         * @param end  以当前节点为结尾的字符串有多少个
         */
        public Node1 (int pass, int end){
            this.end = end;
            this.pass = pass;
            nexts = new Node1[26];
        }
    }
    public static class TrieTree {
        private final Node1 root;
        public TrieTree() {
            root = new Node1(0, 0);
        }

        /**
         * 前缀树插入
         * @param word
         */
        public void insert(String word) {
            if (word == null) {
                return ;
            }
            char[] words = word.toCharArray();
            Node1 node = root;
            node.pass++;
            for (char curWord: words) {
                int path = curWord - 'a';
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node1(0, 0);

                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }

        /**
         * 查询给定的字符串在这个数组中出现了多少次
         * @param word
         * @return
         */
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            Node1 node = root;
            char[] words = word.toCharArray();
            for (char curWord : words) {
                int path = curWord - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.end;
        }

        /**
         * 查询在这个字符数组中，有多少个符合给定前缀的字符串
         * @param prefix
         * @return
         */
        public int prefixNumber(String prefix) {
            if (prefix == null) {
                return 0;
            }
            Node1 node = root;
            char[] words = prefix.toCharArray();
            for (char curWord : words) {
                int path = curWord - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.pass;
        }

        /**
         * 在字符串中删除对应的字符，要注意一点，如果这个字符是存在多个的话，则要判断存在的个数
         * @param word
         */
        public void remove(String word) {
            int count = search(word);
            if (word == null || count == 0) {
                return;
            }
            char[] words = word.toCharArray();
            Node1 node = root;
            node.pass -= count;
            // curWord实际上是在下一个节点上，而当前我们都是在curWord的前一个节点去看的，因此不会发生null
            for (char curWord : words) {
                int path = curWord - 'a';
                if ((node.nexts[path].pass - count) == 0) {
                    node.nexts[path] = null;
                    return ;
                }
                node.nexts[path].pass -= count;
                node = node.nexts[path];
            }
            node.end -= count;
        }
    }
    public static String[] generateArray(int range, int wordSize) {
        int length = (int) (Math.random() * range);
        String[] words = new String[length];
        for (int i = 0; i < length; i++) {
            words[i] = generateRandomString(wordSize);
        }
        return words;
    }
    public static String generateRandomString(int size) {
        int length = (int) (Math.random() * size);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(alphabet.charAt((int) (Math.random() * 26)));
        }
        return result.toString();
    }
    public static void main(String[] args) {
        int maxLength = 10, wordSize = 10;
        // String[] arr = generateArray(maxLength, wordSize);
        String[] arr = new String[] {"abc", "abc", "abc", "def", "abcd"};
        TrieTree tree = new TrieTree();
        for (String word : arr) {
            tree.insert(word);
        }
        tree.remove("abc");
        System.out.println(tree.prefixNumber("abc"));
    }
}
