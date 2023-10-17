package com.ginwithouta.algorithm.class28;

import lombok.Data;

import java.util.ArrayList;

/**
 * @Package : com.ginwithouta.algorithm.class28
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周五
 * @Desc : 跳表，每个节点中维护了各种层级的链表
 */
public class Code02_SkipListMap {
    @Data
    public static class SkipListMapNode<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private ArrayList<SkipListMapNode<K, V>> nextNodes;

        public SkipListMapNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.nextNodes = new ArrayList<>();
        }
    }
    @Data
    public static class SkipListMap<K extends Comparable<K>, V> {
        private SkipListMapNode<K, V> root;
        private static final double PROBABILITY = 0.5;
        private int size;
        private int maxLevel;
        public SkipListMap() {
            this.root = new SkipListMapNode<>(null, null);
            this.size = 0;
            this.maxLevel = 0;
        }

        /**
         * 找到当前层中小于key的最右节点
         * @param cur       the current node
         * @param level     the current level
         * @param key       key
         * @return
         */
        private SkipListMapNode<K, V> mostRightLessNodeInLevel(SkipListMapNode<K, V> cur, int level, K key) {
            while (cur.nextNodes.get(level) != null && key.compareTo(cur.nextNodes.get(level).key) > 0) {
                cur = cur.nextNodes.get(level);
            }
            return cur;
        }

        /**
         * 找到整棵树中比他小的最右边的数
         * @param key   the key
         * @return
         */
        private SkipListMapNode<K, V> mostRightLessNodeInTree(K key) {
            if (key == null) {
                return null;
            }
            SkipListMapNode<K, V> cur = this.root;
            int level = this.maxLevel;
            while (level >= 0) {
                cur = mostRightLessNodeInLevel(cur, level--, key);
            }
            return cur;
        }

        /**
         * 跳表元素的插入或更新
         * @param key
         * @param value
         */
        public void put(K key, V value) {
            if (key == null) {
                return ;
            }
            // 先判断当前跳表中是否存在key
            SkipListMapNode<K, V> less = this.mostRightLessNodeInTree(key), find = less.nextNodes.get(0);
            if (find != null && key.compareTo(find.key) == 0) {
                find.value = value;
            } else {
                this.size++;
                int newNodeLevel = 0;
                SkipListMapNode<K, V> newNode = new SkipListMapNode<>(key,value);
                // 掷骰子，决定层数
                while (Math.random() >= PROBABILITY) {
                    newNodeLevel++;
                }
                // 如果新节点的层数比现在整个跳表的总层数还要高，增加头部的链表数量
                while (newNodeLevel >= this.maxLevel) {
                    this.root.nextNodes.add(null);
                    this.maxLevel++;
                }
                // 初始化链表数量
                for (int level = 0; level <= newNodeLevel; level++) {
                    newNode.nextNodes.add(null);
                }
                // 开始插入
                SkipListMapNode<K, V> pre = this.root;
                for (int level = this.maxLevel; level >= 0; level--) {
                    pre = this.mostRightLessNodeInLevel(pre, level, key);
                    // 只有当前层数来到了新节点的最大层数，才进行插入
                    if (level <= newNodeLevel) {
                        newNode.nextNodes.set(level, pre.nextNodes.get(level));
                        pre.nextNodes.set(level, newNode);
                    }
                }
            }
        }

        /**
         * 删除节点
         * @param key
         */
        public void remove(K key) {
            SkipListMapNode<K, V> less = this.mostRightLessNodeInTree(key), find = less.nextNodes.get(0);
            // 当前跳表中存在key节点
            if (find != null && key.compareTo(find.key) == 0) {
                this.size--;
                SkipListMapNode<K, V> pre = this.root;
                for (int level = this.maxLevel; level >= 0; level--) {
                    pre = this.mostRightLessNodeInLevel(pre, level, key);
                    SkipListMapNode<K, V> next = pre.nextNodes.get(level);
                    if (next != null && key.compareTo(next.key) == 0) {
                        pre.nextNodes.set(level, next.nextNodes.get(level));
                    }
                    // 如果删除完成之后只有this.root一个节点，整个一层删除
                    // 不用担心删除某一层的时候上面还有别的层，因为我们添加节点的时候如果当前节点的层数大于根节点的层数，
                    // 根节点的每一个链表都会指向当前节点，删除一个节点之后所有的链表都会删除，这也是我们为什么每层都要判断是否存在当前节点的原因
                    // 因为存的时候每个链表都会指向同一个节点
                    if (level != 0 && pre == this.root && pre.nextNodes.get(level) == null) {
                        this.root.nextNodes.remove(level);
                        this.maxLevel--;
                    }
                }
            }
        }
    }
}
