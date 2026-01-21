package org.chris.leetcode;

import org.chris.leetcode.dto.GetIntersectionNode_160_TestData;
import org.chris.leetcode.dto.ListNode;
import org.chris.leetcode.linkedlist.GetIntersectionNode_160;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * 相交链表算法单元测试
 * LeetCode 160. 相交链表
 */
public class GetIntersectionNode_160Test {

    private GetIntersectionNode_160 solution;

    @BeforeEach
    void setUp() {
        solution = new GetIntersectionNode_160();
    }

    @Test
    void testNonIntersectingLists() {
        ListNode[] lists = GetIntersectionNode_160_TestData.createNonIntersectingLists();
        ListNode result = solution.getIntersectionNode(lists[0], lists[1]);
        ListNode result2 = solution.getIntersectionNode2(lists[0], lists[1]);

        assertNull(result, "两个无交点的链表应该返回null");
        assertNull(result2, "两个无交点的链表应该返回null");
    }

    @Test
    void testIntersectingAtEnd() {
        ListNode[] lists = GetIntersectionNode_160_TestData.createIntersectingAtEnd();
        ListNode result = solution.getIntersectionNode(lists[0], lists[1]);
        ListNode result2 = solution.getIntersectionNode2(lists[0], lists[1]);

        assertSame(lists[0].next.next.next, result, "交点应该在值为5的节点");
        assertSame(lists[1].next, result, "交点应该在值为5的节点");

        assertSame(lists[0].next.next.next, result2, "交点应该在值为5的节点");
        assertSame(lists[1].next, result2, "交点应该在值为5的节点");
    }

    @Test
    void testIntersectingInMiddle() {
        ListNode[] lists = GetIntersectionNode_160_TestData.createIntersectingInMiddle();
        ListNode result = solution.getIntersectionNode(lists[0], lists[1]);
        ListNode result2 = solution.getIntersectionNode2(lists[0], lists[1]);

        assertSame(lists[0].next.next, result, "交点应该在值为3的节点");
        assertSame(lists[1].next, result, "交点应该在值为3的节点");

        assertSame(lists[0].next.next, result2, "交点应该在值为3的节点");
        assertSame(lists[1].next, result2, "交点应该在值为3的节点");
    }

    @Test
    void testIntersectingAtHead() {
        ListNode[] lists = GetIntersectionNode_160_TestData.createIntersectingAtHead();
        ListNode result = solution.getIntersectionNode(lists[0], lists[1]);
        ListNode result2 = solution.getIntersectionNode2(lists[0], lists[1]);

        assertSame(lists[0], result, "交点应该在头节点");
        assertSame(lists[1], result, "交点应该在头节点");

        assertSame(lists[0], result2, "交点应该在头节点");
        assertSame(lists[1], result2, "交点应该在头节点");
    }

    @Test
    void testEmptyAndNonEmptyLists() {
        ListNode[] lists = GetIntersectionNode_160_TestData.createEmptyAndNonEmptyLists();
        ListNode result = solution.getIntersectionNode(lists[0], lists[1]);
        ListNode result2 = solution.getIntersectionNode2(lists[0], lists[1]);

        assertNull(result, "一个空链表和一个非空链表应该返回null");
        assertNull(result2, "一个空链表和一个非空链表应该返回null");
    }

    @Test
    void testBothEmptyLists() {
        ListNode[] lists = GetIntersectionNode_160_TestData.createBothEmptyLists();
        ListNode result = solution.getIntersectionNode(lists[0], lists[1]);
        ListNode result2 = solution.getIntersectionNode2(lists[0], lists[1]);

        assertNull(result, "两个空链表应该返回null");
        assertNull(result2, "两个空链表应该返回null");
    }

    @Test
    void testSameList() {
        ListNode[] lists = GetIntersectionNode_160_TestData.createSameList();
        ListNode result = solution.getIntersectionNode(lists[0], lists[1]);
        ListNode result2 = solution.getIntersectionNode2(lists[0], lists[1]);

        assertSame(lists[0], result, "相同链表的交点应该在头节点");
        assertSame(lists[1], result, "相同链表的交点应该在头节点");

        assertSame(lists[0], result2, "相同链表的交点应该在头节点");
        assertSame(lists[1], result2, "相同链表的交点应该在头节点");
    }

    @Test
    void testLongAndShortIntersecting() {
        ListNode[] lists = GetIntersectionNode_160_TestData.createLongAndShortIntersecting();
        ListNode result = solution.getIntersectionNode(lists[0], lists[1]);
        ListNode result2 = solution.getIntersectionNode2(lists[0], lists[1]);

        assertSame(lists[0].next.next.next, result, "交点应该在值为4的节点");
        assertSame(lists[1].next.next, result, "交点应该在值为4的节点");

        assertSame(lists[0].next.next.next, result2, "交点应该在值为4的节点");
        assertSame(lists[1].next.next, result2, "交点应该在值为4的节点");
    }

    @Test
    void testYShapedIntersecting() {
        ListNode[] lists = GetIntersectionNode_160_TestData.createYShapedIntersecting();
        ListNode result = solution.getIntersectionNode(lists[0], lists[1]);
        ListNode result2 = solution.getIntersectionNode2(lists[0], lists[1]);

        assertSame(lists[0].next.next, result, "交点应该在值为8的节点");
        assertSame(lists[1].next.next.next, result, "交点应该在值为8的节点");

        assertSame(lists[0].next.next, result2, "交点应该在值为8的节点");
        assertSame(lists[1].next.next.next, result2, "交点应该在值为8的节点");
    }
}