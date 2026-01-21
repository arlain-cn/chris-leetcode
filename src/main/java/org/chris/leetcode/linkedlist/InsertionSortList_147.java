package org.chris.leetcode.linkedlist;

import org.chris.leetcode.dto.GetIntersectionNode_160_TestData;
import org.chris.leetcode.dto.ListNode;

public class InsertionSortList_147 {
    /**
     * 147.对链表进行插入排序
     *
     * @param args
     */
    public static void main(String[] args) {
        InsertionSortList_147 sortList147 = new InsertionSortList_147();
        ListNode list1 = GetIntersectionNode_160_TestData.createNormalByArr(new int[]{1, 2, 3, 30, 25});
        System.out.println(sortList147.insertionSortList(list1).toListString());
        System.out.println(sortList147.insertionSortList1(list1).toListString());
    }

    /**
     * 方法一：从前往后找插入点
     * 对链表进行插入排序的具体过程如下。
     * 1、首先判断给定的链表是否为空，若为空，则不需要进行排序，直接返回。
     * 2、创建哑节点 dummyHead，令 dummyHead.next = head。引入哑节点是为了便于在 head 节点之前插入节点。
     * 3、维护 lastSorted 为链表的已排序部分的最后一个节点，初始时 lastSorted = head。
     * 4、维护 curr 为待插入的元素，初始时 curr = head.next。
     * 5、比较 lastSorted 和 curr 的节点值。
     * <p> 1. 若 lastSorted.val <= curr.val，说明 curr 应该位于 lastSorted 之后，将 lastSorted 后移一位，curr 变成新的 lastSorted。
     * <p> 2. 否则，从链表的头节点开始往后遍历链表中的节点，寻找插入 curr 的位置。令 prev 为插入 curr 的位置的前一个节点，进行如下操作，完成对 curr 的插入：
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head;
        ListNode curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                //这个解法的核心目的是推进lastSorted到最后
                //由节点的推进关系可知：curr就是 lastSorted.next
                lastSorted = lastSorted.next;//等价于lastSorted = curr;
            } else {
                ListNode prev = dummyHead;
                // 这里不用判断prev.next为null的原因是：如果curr当前最大应该走上边if分支
                while (prev.next.val < curr.val) {
                    prev = prev.next;
                }
                //链接上 lastSorted 链表
                //还有一个作用是清空lastSorted的尾巴，避免成环
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            //由节点的推进关系可知：curr就是 lastSorted.next
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }

    /**
     * 手写的插入排序
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList1(ListNode head) {
        ListNode newPreHead = new ListNode(Integer.MIN_VALUE);
        ListNode node = head;
        while (node != null) {
            ListNode newCurr = newPreHead;
            while (newCurr != null) {
                if (newCurr.next != null) {
                    if (node.val >= newCurr.val && node.val <= newCurr.next.val) {
                        //保留原链表的推进关系
                        ListNode next = node.next;
                        //newPreHead表示已加入排序的有序队列，node后的元素尚未排序，不可以添加进来
                        node.next = newCurr.next;
                        newCurr.next = node;
                        node = next;
                        break;
                    }
                    newCurr = newCurr.next;
                } else {
                    //保留原链表的推进关系
                    ListNode next = node.next;
                    //newPreHead表示已加入排序的有序队列，node后的元素尚未排序，不可以添加进来
                    node.next = null;
                    newCurr.next = node;
                    node = next;
                    break;
                }
            }
        }
        return newPreHead.next;
    }

}
