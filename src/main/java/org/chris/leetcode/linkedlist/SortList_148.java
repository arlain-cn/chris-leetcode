package org.chris.leetcode.linkedlist;

import org.chris.leetcode._dto.GetIntersectionNode_160_TestData;
import org.chris.leetcode._dto.ListNode;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public class SortList_148 {
    /**
     * 148. 排序链表
     *
     * @param args
     */
    public static void main(String[] args) {
        SortList_148 sortList148 = new SortList_148();
//        ListNode list1 = GetIntersectionNode_160_TestData.createNormalByArr(new int[]{4, 2, 1, 7, 3});
        ListNode list1 = GetIntersectionNode_160_TestData.createNormalByArr(new int[]{4, 2, 1, 3});
        ListNode node = sortList148.sortList(list1);
        System.out.println(Objects.isNull(node) ? "null" : node.toListString());
    }

    /**
     * 插入排序的时间复杂度是O(n*n)，其中 n 是链表的长度。这道题考虑时间复杂度更低的排序算法。题目的进阶问题要求达到O(nlogn)的时间复杂度和O(1)的空间复杂度，时间复杂度是O(nlogn)的排序算法包括归并排序、堆排序和快速排序（快速排序的最差时间复杂度是O(n*n)），其中最适合链表的排序算法是归并排序。
     * <p>
     * 归并排序基于分治算法。最容易想到的实现方式是自顶向下的递归实现，考虑到递归调用的栈空间，自顶向下归并排序的空间复杂度是 O(logn)。如果要达到 O(1) 的空间复杂度，则需要使用自底向上的实现方式。
     * <p>
     * 方法一：自顶向下归并排序
     * 对链表自顶向下归并排序的过程如下。
     * 1、找到链表的中点，以中点为分界，将链表拆分成两个子链表。寻找链表的中点可以使用快慢指针的做法，快指针每次移动 2 步，慢指针每次移动 1 步，当快指针到达链表末尾时，慢指针指向的链表节点即为链表的中点。
     * 2、对两个子链表分别排序。
     * 3、将两个排序后的子链表合并，得到完整的排序后的链表。可以使用「21. 合并两个有序链表」的做法，将两个有序的子链表进行合并。
     * <p>
     * 上述过程可以通过递归实现。递归的终止条件是链表的节点个数小于或等于 1，即当链表为空或者链表只包含 1 个节点时，不需要对链表进行拆分和排序。
     * <p>
     * 时间复杂度：O(nlogn)、空间复杂度：O(logn)
     *
     * @param head
     * @return
     */
    public ListNode sortList3(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            // 分割链表：这是归并排序中分割链表的关键步骤。当 head.next == tail 时，表示从 head 到 tail 之间只有一个节点（即 head 本身），这是递归的终止条件。
            // 防止循环：确保在递归处理子链表时，不会意外地访问到其他部分的链表节点。
            head.next = null;
            return head;
        }
        ListNode mid = findMidNode(head, tail);
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        return merge(list1, list2);
    }

    /**
     * 快慢指针找中点
     *
     * @param head
     * @return
     */
    public ListNode findMidNode(ListNode head, ListNode tail) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        //首先，我们设定一个哨兵节点 prehead ，这可以在最后让我们比较容易地返回合并后的链表。我们维护一个 prev 指针，我们需要做的是调整它的 next 指针
        ListNode dummyNode = new ListNode(-1);
        ListNode curr = dummyNode, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                curr.next = temp1;
                temp1 = temp1.next;
            } else {
                curr.next = temp2;
                temp2 = temp2.next;
            }
            curr = curr.next;
        }
        //合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        if (temp1 != null) {
            curr.next = temp1;
        }
        if (temp2 != null) {
            curr.next = temp2;
        }
        return dummyNode.next;
    }

    /**
     * 方法二：自底向上归并排序
     * 使用自底向上的方法实现归并排序，则可以达到 O(1) 的空间复杂度。
     * 首先求得链表的长度 length，然后将链表拆分成子链表进行合并。具体做法如下。
     * <p>
     * 1、用 subLength 表示每次需要排序的子链表的长度，初始时 subLength=1。
     * 2、每次将链表拆分成若干个长度为 subLength 的子链表（最后一个子链表的长度可以小于 subLength），按照每两个子链表一组进行合并，合并后即可得到若干个长度为 subLength×2 的有序子链表（最后一个子链表的长度可以小于 subLength×2）。合并两个子链表仍然使用「21. 合并两个有序链表」的做法。
     * 3、将 subLength 的值加倍，重复第 2 步，对更长的有序子链表进行合并操作，直到有序子链表的长度大于或等于 length，整个链表排序完毕。
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            //curr是基于“排序”链表的，所以要基于 dummyHead来
            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = curr.next;
                //分割链表
                curr.next = null;
                curr = next;

                ListNode head2 = curr;
                //多一个curr != null判断是因为，可能到了链表末尾
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }

                if (Objects.nonNull(curr)) {
                    next = curr.next;
                    //分割链表
                    curr.next = null;
                }
                curr = next;

                ListNode merged = merge(head1, head2);
                prev.next = merged;
                while (prev.next != null) {
                    prev = prev.next;
                }
            }
        }
        return dummyHead.next;
    }

    /**
     * 快慢指针找中点
     *
     * @param head
     * @return
     */
    public ListNode findMidNode2(ListNode head, ListNode tail) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        return slow;
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
