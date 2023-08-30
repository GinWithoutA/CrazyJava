package com.ginwithouta.algorithm.class06;

/**
 * @Package : com.ginwithouta.algorithm.class06
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周一
 * @Desc : 判断两个链表是否有相交，如果相交，返回相交节点，如果不相交，返回空
 * @method : 难点在于要判断是否有环
 */
public class Code07_IntersectionOfLinkedList {
    public static class Node {
        public int value;
        public Node next;
        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    /**
     * 生成随机数，范围在(-range, range)
     * @param range
     * @return
     */
    public static int generateRandomNumber(int range) {
        return ((int) (Math.random() * range)) - ((int) (Math.random() * range));
    }

    /**
     * 生成随机链表
     * @param maxLength
     * @param range
     * @return
     */
    public static Node[] generateRandomLinkedList(int maxLength, int range) {
        Node root = new Node(generateRandomNumber(range)), cur = root;
        int length = (int) (Math.random() * maxLength);
        Node[] list = new Node[length];
        list[0] = root;
        for (int i = 1; i <= length; i++) {
            Node node = new Node(generateRandomNumber(range));
            list[i] = node;
            cur.next = node;
            cur = node;
        }
        return list;
    }

    /**
     * 针对一个单链表，随机生成环
     * @param list
     * @return
     */
    public static Node generateRandomLoop(Node[] list) {
        if (Math.random() > 0.5) {
            int loopIndex = (int) (Math.random() * (list.length));
            list[list.length - 1].next = list[loopIndex];
            return list[loopIndex];
        } else {
            return null;
        }
    }

    /**
     * 随机生成两个链表的交点
     * @param tail1
     * @param tail2
     */
    public static void generateRandomIntersection(Node tail1, Node tail2) {
        if (Math.random() <= 0.5) {
            return ;
        }

    }

    /**
     * 获得环的起始节点
     * @param root
     * @return
     */
    public static Node getLoopNode(Node root) {
        if (root == null || root.next == null) {
            return null;
        }
        Node slow = root, fast = root.next.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = root;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 没有环的链表判断是否相交
     * @param root1
     * @param root2
     * @return
     */
    public static Node noLoop(Node root1, Node root2) {
        if (root1 == root2) {
            return root1;
        }
        Node node1 = root1, node2 = root2;
        int n = 0;
        while (node1.next != null) {
            n++;
            node1 = node1.next;
        }
        while (node2.next != null) {
            n--;
            node2 = node2.next;
        }
        // 判断尾巴是否相等，如果相等，说明前面就可能发生相交，如果不等，说明不相交
        if (node1 != node2) {
            return null;
        }
        // 判断哪一条链表长
        node1 = n > 0 ? root1 : root2;
        node2 = node1 == root1 ? root2 : root1;
        n = Math.abs(n);
        // 长的那个先走
        while (n != 0) {
            n--;
            node1 = node1.next;
        }
        // 一起走
        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;
    }
    /**
     * 部分没有环的链表判断是否相交
     * @param root1
     * @param root2
     * @return
     */
    public static Node noLoop(Node root1, Node root2, Node end1, Node end2) {
        if (root1 == root2) {
            return root1;
        }
        Node node1 = root1, node2 = root2;
        int n = 0;
        while (node1.next != end1) {
            n++;
            node1 = node1.next;
        }
        while (node2.next != end2) {
            n--;
            node2 = node2.next;
        }
        // 判断尾巴是否相等，如果相等，说明前面就可能发生相交，如果不等，说明不相交
        if (node1 != node2) {
            return null;
        }
        // 判断哪一条链表长
        node1 = n > 0 ? root1 : root2;
        node2 = node1 == root1 ? root2 : root1;
        n = Math.abs(n);
        // 长的那个先走
        while (n != 0) {
            n--;
            node1 = node1.next;
        }
        // 一起走
        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;
    }

    /**
     * 两条链表都有环的情况，判断是否存在交点
     * @param root1
     * @param root2
     * @param loop1
     * @param loop2
     * @return
     */
    public static Node loop(Node root1, Node root2, Node loop1, Node loop2) {
        // 先判断是不是环的点相同
        if (loop1 == loop2) {
            return noLoop(root1, root2, loop1, loop2);
        }
        // 有环，说明不可能有null的情况出现
        Node cur = loop1.next;
        while (cur != loop1) {
            if (cur == loop2) {
                return loop2;
            }
            cur = cur.next;
        }
        // 如果不是提前相交，或者不是环是同一个，但是不同的入口，说明不相交
        return null;
    }

    public static Node getIntersectionNode(Node root1, Node root2) {
        if (root1 == null || root2 == null) {
            return null;
        }
        // 首先判断是不是有环
        Node loop1 = getLoopNode(root1), loop2 = getLoopNode(root2);
        if (loop1 == null && loop2 == null) {
            // 两个链表都没有环，直接判断是否相交
            return noLoop(root1, root2, null, null);
        }
        if (loop1 != null && loop2 != null) {
            return loop(root1, root2, loop1, loop2);
        }
        // 一个有环，一个没环，肯定不相交
        return null;
    }
}
