package org.chris.leetcode.linkedlist;

import org.chris.leetcode._dto.GetIntersectionNode_160_TestData;
import org.chris.leetcode._dto.ListNode;

import java.util.Deque;
import java.util.LinkedList;

public class RemoveNthFromEnd_21 {

    public static void main(String[] args) {
        RemoveNthFromEnd_21 removeNthFromEnd21 = new RemoveNthFromEnd_21();
        ListNode list1 = GetIntersectionNode_160_TestData.createNormalByArr(new int[]{1, 2, 4});
        System.out.println(removeNthFromEnd21.removeNthFromEnd(list1, 2).toListString());

    }

    /**
     * 双指针找要剔除的节点
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode left = head;
        ListNode right = head;
        //原计划i<n-1,考虑到right要走到null，就不减一了
        for (int i = 1; i <= n; i++) {
            right = right.next;
        }
        if (right == null) {
            return left.next;
        }
        ListNode prev = null;
        while (right != null) {
            prev = left;
            left = left.next;
            right = right.next;
        }
        prev.next = left.next;
        return head;
    }

    /**
     * 双指针找要剔除的节点
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode preHead = new ListNode(-1);
        preHead.next = head;

        ListNode left = preHead;
        ListNode right = preHead;

        for (int i = 1; i <= n; i++) {
            right = right.next;
        }

        while (right != null) {
            left = left.next;
            right = right.next;
        }

        left.next = left.next.next;
        return preHead.next;
    }

    /**
     * 方法二：栈
     * 思路与算法
     * <p>
     * 我们也可以在遍历链表的同时将所有节点依次入栈。根据栈「先进后出」的原则，我们弹出栈的第 n 个节点就是需要删除的节点，并且目前栈顶的节点就是待删除节点的前驱节点。这样一来，删除操作就变得十分方便了。
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Deque<ListNode> stack = new LinkedList<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node);
            node=node.next;
        }
        ListNode prev = null;
        for (int i = 1; i <= n; i++) {
            stack.pop();
        }
        prev = stack.peek();
        if (prev == null) {
            return head.next;
        }
        prev.next = prev.next.next;
        return head;
    }
}
