package org.chris.leetcode;

import org.chris.leetcode.dto.GetIntersectionNode_160_TestData;
import org.chris.leetcode.dto.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SortList_148 {
    /**
     * 148. 排序链表
     *
     * @param args
     */
    public static void main(String[] args) {
        SortList_148 sortList148 = new SortList_148();
        ListNode list1 = GetIntersectionNode_160_TestData.createNormalByArr(new int[]{3, 2, 4});
//        System.out.println(sortList148.sortList(list1).toListString());
        System.out.println(sortList148.sortList(list1).toListString());
    }

    public ListNode sortList(ListNode head) {
//        todo
        return head;
    }

    /**
     * 插入排序(标准版)
     *
     * @param head
     * @return
     */
    public ListNode sortList2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode lastedNSorted = head;
        ListNode curr = head.next;
        while (curr != null) {
            if (curr.val >= lastedNSorted.val) {
                lastedNSorted = lastedNSorted.next;
            } else {
                ListNode prev = dummyNode;
                while (prev.next.val < curr.val) {
                    prev = prev.next;
                }
                lastedNSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastedNSorted.next;
        }
        return dummyNode.next;
    }

    /**
     * 插入排序(破解版)
     *
     * @param head
     * @return
     */
    public ListNode sortList2_1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode lastedNSorted = head;
        ListNode curr = head.next;
        while (curr != null) {
            if (curr.val >= lastedNSorted.val) {
                lastedNSorted.next = curr;
                lastedNSorted = lastedNSorted.next;
                curr = curr.next;
            } else {
                ListNode prev = dummyNode;
                while (prev.next.val < curr.val) {
                    prev = prev.next;
                }
                ListNode next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
                curr = next;
            }
        }
        lastedNSorted.next = null;
        return dummyNode.next;
    }

    /**
     * O(n)额外空间的优先队列
     *
     * @param head
     * @return
     */
    public ListNode sortList1(ListNode head) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        ListNode node = head;
        while (node != null) {
            priorityQueue.offer(node);
            node = node.next;
        }
        ListNode newPreHead = new ListNode(-1);
        ListNode newCurrHead = newPreHead;
        while (!priorityQueue.isEmpty()) {
            newCurrHead.next = priorityQueue.poll();
            newCurrHead = newCurrHead.next;
        }

        newCurrHead.next = null;
        return newPreHead.next;
    }


}
