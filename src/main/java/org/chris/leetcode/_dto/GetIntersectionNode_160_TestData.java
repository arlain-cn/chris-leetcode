package org.chris.leetcode._dto;

/**
 * 相交链表测试数据构造器
 * LeetCode 160. 相交链表
 */
public class GetIntersectionNode_160_TestData {

    /**
     * 创建两个无交点的链表
     * ListA: 1->2->3
     * ListB: 4->5->6
     * 交点: null
     */
    public static ListNode[] createNonIntersectingLists() {
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(3);

        ListNode headB = new ListNode(4);
        headB.next = new ListNode(5);
        headB.next.next = new ListNode(6);

        return new ListNode[]{headA, headB};
    }

    /**
     * 创建两个有交点的链表 (交点在末尾)
     * ListA: 1->2->3
     * \
     * 5->6
     * /
     * ListB:    4->-
     * 交点: 5
     */
    public static ListNode[] createIntersectingAtEnd() {
        ListNode intersect = new ListNode(5);
        intersect.next = new ListNode(6);

        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(3);
        headA.next.next.next = intersect;

        ListNode headB = new ListNode(4);
        headB.next = intersect;

        return new ListNode[]{headA, headB};
    }

    /**
     * 创建两个有交点的链表 (交点在中间)
     * ListA: 1->2->3->4
     * /
     * ListB:    5-
     * 交点: 3
     */
    public static ListNode[] createIntersectingInMiddle() {
        ListNode intersect = new ListNode(3);
        intersect.next = new ListNode(4);

        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = intersect;

        ListNode headB = new ListNode(5);
        headB.next = intersect;

        return new ListNode[]{headA, headB};
    }

    /**
     * 创建两个有交点的链表 (交点在头部)
     * ListA: 1->2->3
     * ListB: /
     * 1->2->3
     * 交点: 1
     */
    public static ListNode[] createIntersectingAtHead() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        return new ListNode[]{head, head};
    }

    /**
     * 创建一个空链表和一个非空链表
     * ListA: null
     * ListB: 1->2->3
     * 交点: null
     */
    public static ListNode[] createEmptyAndNonEmptyLists() {
        ListNode headB = new ListNode(1);
        headB.next = new ListNode(2);
        headB.next.next = new ListNode(3);

        return new ListNode[]{null, headB};
    }

    /**
     * 创建两个空链表
     * ListA: null
     * ListB: null
     * 交点: null
     */
    public static ListNode[] createBothEmptyLists() {
        return new ListNode[]{null, null};
    }

    /**
     * 创建相同链表
     * ListA: 1->2->3
     * ListB: 1->2->3 (same objects)
     * 交点: 1
     */
    public static ListNode[] createSameList() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        return new ListNode[]{head, head};
    }

    /**
     * 创建长链表与短链表相交
     * ListA: 1->2->3->4->5
     * /
     * ListB:       6->7-
     * 交点: 4
     */
    public static ListNode[] createLongAndShortIntersecting() {
        ListNode intersect = new ListNode(4);
        intersect.next = new ListNode(5);

        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(3);
        headA.next.next.next = intersect;

        ListNode headB = new ListNode(6);
        headB.next = new ListNode(7);
        headB.next.next = intersect;

        return new ListNode[]{headA, headB};
    }

    /**
     * 创建Y字型相交链表 (LeetCode示例)
     * ListA: 4->1->8->4->5
     * /
     * ListB: 5-6-1->8->4->5
     * 交点: 8
     */
    public static ListNode[] createYShapedIntersecting() {
        // 共享部分: 8->4->5
        ListNode shared8 = new ListNode(8);
        shared8.next = new ListNode(4);
        shared8.next.next = new ListNode(5);

        // ListA: 4->1->8->4->5
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = shared8;

        // ListB: 5->6->1->8->4->5
        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = shared8;

        return new ListNode[]{headA, headB};
    }

    /**
     *
     * @param nums
     * @return
     */
    public static ListNode createNormalByArr(int[] nums) {
        ListNode head = null;
        if (nums.length == 0) {
            return head;
        }
        head = new ListNode(nums[0]);
        ListNode node = head;
        for (int i = 1; i < nums.length; i++) {
            node.next = new ListNode(nums[i]);
            node = node.next;
        }
        return head;
    }

    /**
     *
     * @param nums
     * @return
     */
    public static ListNode createCycleByArr(int[] nums, int pos) {
        ListNode head = null;
        if (nums.length == 0) {
            return head;
        }
        head = new ListNode(nums[0]);
        ListNode node = head;
        ListNode posNode = head;
        for (int i = 1; i < nums.length; i++) {
            node.next = new ListNode(nums[i]);
            node = node.next;
            if (i == pos) {
                posNode = node;
            }
            if (i == nums.length - 1) {
                node.next = posNode;
            }
        }
        return head;
    }
}