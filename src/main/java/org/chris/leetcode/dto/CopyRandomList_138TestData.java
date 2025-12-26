package org.chris.leetcode.dto;

import java.util.HashMap;
import java.util.Map;

public class CopyRandomList_138TestData {

    /**
     * 构建用于测试的随机链表 - 示例：[[7,null],[13,0],[11,4],[10,2],[1,0]]
     *
     * 链表结构如下：
     * - 节点0 (值=7): next->节点1, random->null
     * - 节点1 (值=13): next->节点2, random->节点0 (索引0)
     * - 节点2 (值=11): next->节点3, random->节点4 (索引4)
     * - 节点3 (值=10): next->节点4, random->节点2 (索引2)
     * - 节点4 (值=1): next->null, random->节点0 (索引0)
     *
     * @return 构建好的测试链表
     */
    public static Node buildTestList() {
        // 创建5个节点，对应 [[7,null],[13,0],[11,4],[10,2],[1,0]]
        Node node0 = new Node(7);  // [7,null]
        Node node1 = new Node(13); // [13,0]
        Node node2 = new Node(11); // [11,4]
        Node node3 = new Node(10); // [10,2]
        Node node4 = new Node(1);  // [1,0]

        // 设置next指针
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        // node4.next 为 null（默认）

        // 设置random指针，根据 [[7,null],[13,0],[11,4],[10,2],[1,0]] 的要求：
        node0.random = null;    // [7,null] - random指向null
        node1.random = node0;   // [13,0] - random指向索引0的节点
        node2.random = node4;   // [11,4] - random指向索引4的节点
        node3.random = node2;   // [10,2] - random指向索引2的节点
        node4.random = node0;   // [1,0] - random指向索引0的节点

        return node0; // 返回头节点
    }

    /**
     * 构建另一种测试随机链表 - 简单的5节点链表
     *
     * 创建一个具有5个节点的链表，如下结构：
     * 1 -> 2 -> 3 -> 4 -> 5 -> null
     * 随机指针：
     * - 节点1的random指向节点3
     * - 节点2的random指向节点5
     * - 节点3的random指向节点1
     * - 节点4的random指向节点2
     * - 节点5的random指向节点4
     *
     * @return 构建好的测试链表
     */
    public static Node buildSimpleTestList() {
        // 创建5个节点
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        // 设置next指针
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        // node5.next 为 null（默认）

        // 设置random指针
        node1.random = node3;  // 节点1的random指向节点3
        node2.random = node5;  // 节点2的random指向节点5
        node3.random = node1;  // 节点3的random指向节点1
        node4.random = node2;  // 节点4的random指向节点2
        node5.random = node4;  // 节点5的random指向节点4

        return node1; // 返回头节点
    }

    /**
     * 打印链表结构（包括random指针）
     *
     * @param head 链表头节点
     */
    public static void printListWithRandom(Node head) {
        Node curr = head;
        int index = 0;
        Map<Node, Integer> nodeToIndex = new HashMap<>();

        // 首先建立节点到索引的映射
        Node temp = head;
        while (temp != null) {
            nodeToIndex.put(temp, index++);
            temp = temp.next;
        }

        System.out.println("链表结构（值 -> next值, random值）：");
        curr = head;
        while (curr != null) {
            String nextVal = curr.next != null ? String.valueOf(curr.next.val) : "null";
            String randomVal = curr.random != null ? String.valueOf(curr.random.val) : "null";
            int currentIndex = nodeToIndex.get(curr);
            System.out.println("节点" + currentIndex + " (值=" + curr.val + "): next->" + nextVal + ", random->" + randomVal);
            curr = curr.next;
        }
    }
}
