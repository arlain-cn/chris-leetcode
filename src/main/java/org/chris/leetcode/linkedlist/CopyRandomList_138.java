package org.chris.leetcode.linkedlist;

import org.chris.leetcode._dto.CopyRandomList_138TestData;
import org.chris.leetcode._dto.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * 138. 随机链表的复制
 * <p>
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
 */
public class CopyRandomList_138 {

    public static void main(String[] args) {
        CopyRandomList_138 copyRandomList138 = new CopyRandomList_138();

        // 构建测试数据
        Node testList = CopyRandomList_138TestData.buildTestList();
        System.out.println("原链表构建完成");

        // 打印原链表信息
        CopyRandomList_138TestData.printListWithRandom(testList);

        // 执行深拷贝
        Node copiedList = copyRandomList138.copyRandomList(testList);
        System.out.println("\n深拷贝完成");

        // 打印拷贝后的链表信息
        CopyRandomList_138TestData.printListWithRandom(testList);
        CopyRandomList_138TestData.printListWithRandom(copiedList);

    }

    /**
     * 方法一：复制随机链表 - 使用哈希表方法
     * <p>
     * 解题思路：
     * 1. 第一次遍历：创建所有新节点并建立原节点到新节点的映射
     * 2. 第二次遍历：设置新节点的next和random指针
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param head 原链表头节点
     * @return 拷贝后的链表头节点
     */
    public Node copyRandomList1(Node head) {
        if (head == null) {
            return null;
        }
        // 使用哈希表存储原节点到新节点的映射
        Map<Node, Node> old_new_node_map = new HashMap<>();
        Node curr = head;
        while (curr != null) {
            // 第一次遍历：创建所有新节点并建立映射关系
            Node newCurr = new Node(curr.val);
            old_new_node_map.put(curr, newCurr);
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            // 第二次遍历：设置新节点的next和random指针
            Node newCurr = old_new_node_map.get(curr);
            Node newNext = old_new_node_map.get(curr.next);
            Node newRandom = old_new_node_map.get(curr.random);
            newCurr.next = newNext;
            newCurr.random = newRandom;
            curr = curr.next;
        }
        // 返回头节点对应的新节点
        return old_new_node_map.get(head);
    }

    /**
     * 方法一：回溯 + 哈希表
     *
     * @param head
     * @return
     */
    Map<Node, Node> old_new_node_map = new HashMap<>();

    public Node copyRandomListFac(Node head) {
        if (head == null) {
            return null;
        }
        if (old_new_node_map.containsKey(head)) {
            return old_new_node_map.get(head);
        }

        Node newNode = new Node(head.val);
        old_new_node_map.put(head, newNode);
        newNode.next = copyRandomListFac(head.next);
        newNode.random = copyRandomListFac(head.random);
        return newNode;
    }

    /**
     * 方法二：迭代 + 节点拆分
     * 思路及算法
     * 注意到方法一需要使用哈希表记录每一个节点对应新节点的创建情况，而我们可以使用一个小技巧来省去哈希表的空间。
     * <p>
     * 1、我们首先将该链表中每一个节点拆分为两个相连的节点，例如对于链表 A→B→C，我们可以将其拆分为 A→A→B→B→C→C。对于任意一个原节点 S，其拷贝节点 S′ 即为其后继节点。
     * <p>
     * 2、这样，我们可以直接找到每一个拷贝节点 S′的随机指针应当指向的节点，即为其原节点 S 的随机指针指向的节点 T 的后继节点 T′。需要注意原节点的随机指针可能为空，我们需要特别判断这种情况。
     * <p>
     * 3、当我们完成了拷贝节点的随机指针的赋值，我们只需要将这个链表按照原节点与拷贝节点的种类进行拆分即可，只需要遍历一次。同样需要注意最后一个拷贝节点的后继节点为空，我们需要特别判断这种情况。
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        for (Node curr = head; curr != null; curr = curr.next.next) {
            Node newCurr = new Node(curr.val);
            newCurr.next = curr.next;
            curr.next = newCurr;
        }
        for (Node curr = head; curr != null; curr = curr.next.next) {
            Node newCurr = curr.next;
            if (curr.random != null) {
                newCurr.random = curr.random.next;
            }
        }
        Node preHead = new Node(-1);
        Node preNode = preHead;
        for (Node curr = head; curr != null && curr.next != null; curr = curr.next) {
            Node newCurr = curr.next;
            preNode.next = newCurr;
            preNode = preNode.next;

            curr.next = curr.next.next;
        }

        return preHead.next;
//        Node headNew = head.next;
//        for (Node node = head; node != null; node = node.next) {
//            Node nodeNew = node.next;
//            node.next = node.next.next;
//            nodeNew.next = (nodeNew.next != null) ? nodeNew.next.next : null;
//        }
//        return headNew;
    }
}
