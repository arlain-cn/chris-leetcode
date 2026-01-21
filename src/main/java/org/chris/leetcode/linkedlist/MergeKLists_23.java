package org.chris.leetcode.linkedlist;

import org.chris.leetcode.dto.GetIntersectionNode_160_TestData;
import org.chris.leetcode.dto.ListNode;

import java.util.*;

public class MergeKLists_23 {
    /**
     * 23. 合并 K 个升序链表
     * 给你一个链表数组，每个链表都已经按升序排列。
     * <p>
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     * @param args
     */
    public static void main(String[] args) {
        ListNode list1 = GetIntersectionNode_160_TestData.createNormalByArr(new int[]{1, 4, 5});
        ListNode list2 = GetIntersectionNode_160_TestData.createNormalByArr(new int[]{1, 3, 4});
        ListNode list3 = GetIntersectionNode_160_TestData.createNormalByArr(new int[]{2, 6});

        ListNode[] lists = new ListNode[]{list1, list2, list3};

        MergeKLists_23 mergeKLists23 = new MergeKLists_23();
        ListNode res = mergeKLists23.mergeKLists(lists);
        System.out.println(res.toListString());
    }

    /**
     * 方法二：分治合并(递归版本)
     * 思路：考虑优化方法一，用分治的方法进行合并。
     * <p>
     * 1、将 k 个链表配对并将同一对中的链表合并；
     * 2、第一轮合并以后， k 个链表被合并成了 2/k个链表，平均长度为 k/2n，然后是 4/k 个链表，8/k个链表等等；
     * 3、重复这一过程，直到我们得到了最终的有序链表。
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists_MS_fac(lists, 0, lists.length - 1);
    }

    public ListNode mergeKLists_MS_fac(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;

        ListNode list1 = mergeKLists_MS_fac(lists, left, mid);
        ListNode list2 = mergeKLists_MS_fac(lists, mid + 1, right);
        return mergeTwoLists(list1, list2);
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode prev = dummyNode;

        ListNode temp1 = list1, temp2 = list2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                prev.next = temp1;
                temp1 = temp1.next;
            } else {
                prev.next = temp2;
                temp2 = temp2.next;
            }
            prev = prev.next;
        }

        if (temp1 != null) {
            prev.next = temp1;
        }
        if (temp2 != null) {
            prev.next = temp2;
        }
        return dummyNode.next;
    }

    /**
     * 方法二：分治合并(迭代法)
     * 思路：考虑优化方法一，用分治的方法进行合并。
     * <p>
     * 1、将 k 个链表配对并将同一对中的链表合并；
     * 2、第一轮合并以后， k 个链表被合并成了 2/k个链表，平均长度为 k/2n，然后是 4/k 个链表，8/k个链表等等；
     * 3、重复这一过程，直到我们得到了最终的有序链表。
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists_MergeSort(ListNode[] lists) {
        Queue<ListNode> headsQueue = new LinkedList<>();
        for (ListNode head : lists) {
            headsQueue.offer(head);
        }
        int length = lists.length;

        while (!headsQueue.isEmpty()) {
            if (length == 1) break;

            for (int i = 0; i < length; ) {
                ListNode head1 = headsQueue.poll();
                ListNode head2 = null;
                if (++i < length) {
                    head2 = headsQueue.poll();
                    ++i;
                }
                headsQueue.offer(mergeTwoLists(head1, head2));
            }
            length = (length + 1) / 2;
        }
        return headsQueue.poll();
    }

    /**
     * 方法一：顺序合并
     * 思路:我们可以想到一种最朴素的方法：用一个变量 ans 来维护以及合并的链表，第 i 次循环把第 i 个链表和 ans 合并，答案保存到 ans 中。
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        ListNode ans = null;
        for (ListNode head : lists) {
            ListNode merged = mergeTwoLists(head, ans);
            ans = merged;
        }
        return ans;
    }



    /**
     * 方法三：使用优先队列合并
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode dummyHead = new ListNode(-1);

        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (ListNode node : lists) {
            while (node != null) {
                queue.offer(node);
                node = node.next;
            }
        }

        ListNode curr = dummyHead;
        while (!queue.isEmpty()) {
            curr.next = queue.poll();
            curr = curr.next;
            curr.next = null;
        }
        return dummyHead.next;
    }

    /**
     * 方法三：使用map
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        Map<Integer, List<ListNode>> listNodeMap = new HashMap<>();
        for (ListNode node : lists) {
            ListNode curNode = node;
            while (curNode != null) {
                if (curNode.val < minVal) {
                    minVal = curNode.val;
                }
                if (curNode.val > maxVal) {
                    maxVal = curNode.val;
                }
                if (listNodeMap.containsKey(curNode.val)) {
                    listNodeMap.get(curNode.val).add(curNode);
                } else {
                    List<ListNode> nodes = new ArrayList<>();
                    nodes.add(curNode);
                    listNodeMap.put(curNode.val, nodes);
                }
                curNode = curNode.next;
            }
        }

        for (int i = minVal; i <= maxVal; i++) {
            List<ListNode> nodes = listNodeMap.get(i);
            if (nodes != null) {
                for (ListNode node : nodes) {
                    curr.next = node;
                    curr = curr.next;
                }
            }
        }
        return dummyHead.next;
    }
}
