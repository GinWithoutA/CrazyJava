package com.ginwithouta.algorithm.class27;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;

/**
 * @Package : com.ginwithouta.algorithm.class27
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周四
 * @Desc :
 */
public class Code01_AVLTreeMap {
    @Data
    public static class AVLNode<K extends Comparable<K>, V> {
        private AVLNode<K, V> left;
        private AVLNode<K, V> right;
        private K key;
        private V value;
        private int height;
        public AVLNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
            this.left = null;
            this.right = null;
        }
    }
    @Data
    @AllArgsConstructor
    public static class AVLTreeMap<K extends Comparable<K>, V> {
        private AVLNode<K, V> root;

        /**
         * 左旋
         * @param cur   需要左旋的节点
         * @return      返回左旋后的新节点
         */
        public AVLNode<K, V> leftRotation(AVLNode<K, V> cur) {
             AVLNode<K, V> right = cur.getRight();
             cur.setRight(right.getLeft());
             right.setLeft(cur);
             cur.setHeight(Math.max(cur.getLeft() == null ? 0 : cur.getLeft().getHeight(), cur.getRight() == null ? 0 : cur.getRight().getHeight()) + 1);
             right.setHeight(Math.max(cur.getHeight(), right.getRight() == null ? 0 : right.getRight().getHeight()) + 1);
            return right;
        }

        /**
         * 右旋
         * @param cur   当前节点
         * @return      新地作为头的节点
         */
        public AVLNode<K, V> rightRotation(AVLNode<K, V> cur) {
            AVLNode<K, V> left = cur.getLeft();
            cur.setLeft(left.getRight());
            left.setRight(cur);
            cur.setHeight(Math.max(cur.getLeft() == null ? 0 : cur.getLeft().getHeight(), cur.getRight() == null ? 0 : cur.getRight().getHeight()) + 1);
            left.setHeight(Math.max(cur.getHeight(), cur.getLeft() == null ? 0 : cur.getLeft().getHeight()) + 1);
            return left;
        }

        public AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new AVLNode<>(key, value);
            }
            if (key.compareTo(cur.getKey()) < 0) {
                cur.setLeft(add(cur.getLeft(), key, value));
            } else {
                cur.setRight(add(cur.getRight(), key, value));
            }
            cur.setHeight(Math.max(cur.getLeft() == null ? 0 : cur.getLeft().getHeight(), cur.getRight() == null ? 0 : cur.getRight().getHeight()) + 1);
            return this.maintain(cur);
        }

        public AVLNode<K, V> delete(AVLNode<K, V> cur,  K key) {
            if (key.compareTo(cur.getKey()) < 0) {
                cur.setLeft(delete(cur.getLeft(), key));
            } else if (key.compareTo(cur.getKey()) > 0) {
                cur.setRight(delete(cur.getRight(), key));
            } else {
                if (cur.getLeft() == null && cur.getRight() == null) {
                    cur = null;
                } else if (cur.getLeft() != null && cur.getRight() == null) {
                    cur = cur.getLeft();
                } else if (cur.getLeft() == null && cur.getRight() != null) {
                    cur = cur.getRight();
                } else {
                    AVLNode<K, V> del = cur.getRight();
                    while (del.getLeft() != null) {
                        del = del.getLeft();
                    }
                    cur.setRight(delete(cur.getRight(), del.getKey()));
                    del.setLeft(cur.getLeft());
                    del.setRight(cur.getRight());
                    cur = del;
                }
            }
            if (cur != null) {
                cur.setHeight(Math.max(cur.getLeft() == null ? 0 : cur.getLeft().getHeight(), cur.getRight() == null ? 0 : cur.getRight().getHeight()) + 1);
            }
            return this.maintain(cur);
        }

        public AVLNode<K, V> maintain(AVLNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            int leftHeight = cur.getLeft() == null ? 0 : cur.getLeft().getHeight();
            int rightHeight = cur.getRight() == null ? 0 : cur.getRight().getHeight();
            if (Math.abs(leftHeight - rightHeight) > 1) {
                if (leftHeight > rightHeight) {
                    int leftLeftHeight = cur.getLeft() != null && cur.getLeft().getLeft() != null ? cur.getLeft().getLeft().getHeight() : 0;
                    int leftRightHeight = cur.getRight() != null && cur.getRight().getRight() != null ? cur.getRight().getRight().getHeight() : 0;
                    // 如果是LL型或者是LL和LR型同时碰到
                    if (leftLeftHeight >= leftRightHeight) {
                        cur = this.rightRotation(cur);
                    } else {
                        cur.setLeft(this.leftRotation(cur.getLeft()));
                        cur = this.rightRotation(cur);
                    }
                } else {
                    int rightLeftHeight = cur.getLeft() != null && cur.getLeft().getLeft() != null ? cur.getLeft().getLeft().getHeight() : 0;
                    int rightRightHeight = cur.getRight() != null && cur.getRight().getRight() != null ? cur.getRight().getRight().getHeight() : 0;
                    if (rightLeftHeight <= rightRightHeight) {
                        cur = this.leftRotation(cur);
                    } else {
                        cur.setRight(this.rightRotation(cur.getRight()));
                        cur = this.leftRotation(cur);
                    }
                }
            }
            return cur;
        }
    }
}
