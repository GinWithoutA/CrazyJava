package com.ginwithouta.algorithm.class26;

import com.ginwithouta.algorithm.class02.Code03_DoubleEndsQueueToStackAndQueue;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Package : com.ginwithouta.algorithm.class26
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周一
 * @Desc : AC自动机：有一篇大文章，给你一堆敏感词，返回大文章中所有的敏感词
 */
public class Code03_ACAutomation {
    /**
     * AC自动机中每个节点的对象类
     */
    @Data
    public static class Node {
        // 每个Node都有一个end，如果end为空，表明当前节点不是叶子节点
        // 如果end不为空，表明当前节点是叶节点，并且end的值就是当前敏感词
        private String end;
        // 判断当前敏感词是否使用过，同样，只有当前节点是叶节点的时候，这个判断才有用
        private boolean endUsed;
        // 一个指针，表名如果当前匹配失败了的话，我应该去哪
        // 这个指针的目的就是为了找以当前节点为末尾节点的最长公共前缀
        private Node fail;
        // 前缀树的next数组，里面存放了所有的孩子，这里的长度是26，指向所有可能的26个字符
        private Node[] next;
        public Node() {
            this.end = null;
            this.endUsed = false;
            this.fail = null;
            this.next = new Node[26];
        }
    }

    /**
     * AC自动机类
     */
    @Data
    public static class ACAutomation {
        // AC自动机中的根节点，也就是前缀树的根节点，默认就是个空的东西
        private Node root;
        public ACAutomation() {
            root = new Node();
        }

        /**
         * 构建前缀树
         * @param sensitive 敏感词
         */
        public void insert(String sensitive) {
            Node cur = this.root;
            char[] str = sensitive.toCharArray();
            for (char c : str) {
                if (cur.next[c - 'a'] == null) {
                    cur.next[c - 'a'] = new Node();
                }
                cur = cur.next[c - 'a'];
            }
            cur.end = sensitive;
        }

        /**
         * 构建AC自动机（带fail指针的前缀树）
         */
        public void build() {
            Queue<Node> queue = new LinkedList<>();
            queue.add(this.root);
            while (!queue.isEmpty()) {
                // 通过父节点设置所有孩子节点的fail指针
                Node cur = queue.poll();
                for (int i = 0; i < 26; i++) {
                    if (cur.next[i] != null) {
                        // 记录当前有值的孩子节点
                        Node child = cur.next[i];
                        // 初始化当前孩子的fail节点，最坏的情况就是指向this.root
                        child.fail = this.root;
                        // 记录当前节点cur的fail节点
                        Node curFail = cur.fail;
                        while (curFail != null) {
                            // 如果当前fail节点有i位置的值，即当前的fail节点有和cur节点一样指向i位置的值，则当前孩子节点的fail就指向cur.fail.next[i]
                            // 此时就说明我找到了一个最长前缀
                            if (curFail.next[i] != null) {
                                child.fail = curFail.next[i];
                                break;
                            }
                            curFail = curFail.fail;
                        }
                        queue.add(child);
                    }
                }
            }
        }

        /**
         * 查找大文章中是否存在敏感词
         * @param content   大文章
         * @return
         */
        public List<String> isContain(String content) {
            char[] str = content.toCharArray();
            List<String> sensitive = new ArrayList<>();
            Node cur = this.root, check;
            int index;
            for (int i = 0; i < str.length; i++) {
                // 当前的路
                index = str[i] - 'a';
                // 只要没有匹配上，并且当前节点不是root，当前节点就往fail上跳
                // 如果当前节点是root，往fail上跳就会变成null，因此要判断一下是不是root
                while (cur.next[index] == null && cur != root) {
                    cur = cur.fail;
                }
                // 判断当前跳完的节点是满足了哪一个条件才跳出了上面的循环，如果真的有路，跳下来
                cur = cur.next[index] != null ? cur.next[index] : root;
                check = cur;
                // 站在原地，check一下所有的fail有没有是某一个字符串的末尾的，即判断是否存在以自己为结尾的最长后缀是敏感词
                while (check != root) {
                    // 这是一整个环，只有第一个不是false了，那么后面的也全都不是false
                    if (check.isEndUsed()) {
                        break;
                    }
                    // 当前节点者的是末尾节点
                    if (check.getEnd() != null) {
                        check.setEndUsed(true);
                        sensitive.add(check.getEnd());
                    }
                    check = check.getFail();
                }
            }
            return sensitive;
        }
    }
}
