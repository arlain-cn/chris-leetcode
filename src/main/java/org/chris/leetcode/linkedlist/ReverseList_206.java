package org.chris.leetcode.linkedlist;

import org.chris.leetcode._dto.GetIntersectionNode_160_TestData;
import org.chris.leetcode._dto.ListNode;

import java.util.Deque;
import java.util.LinkedList;

public class ReverseList_206 {

    public static void main(String[] args) {
        ReverseList_206 reverseList206 = new ReverseList_206();
        ListNode head = GetIntersectionNode_160_TestData.createNormalByArr(new int[]{1, 2, 3, 4, 5});
        System.out.println(reverseList206.reverseList0(head).toListString());
    }

    /**
     * 迭代法：用三个指针（prev, curr, next）遍历链表。初始时prev = null, curr = head，每一步如何移动指针并反转curr.next指向？（例如，链表1->2->3->null，第一步后应变为null<-1 2->3->null）
     * 递归法：递归终止条件是什么？递归返回的节点如何与当前节点连接？（提示：递归到链表末尾后，逐步反转指针）
     * 用小例子手动模拟：head = [1,2,3]，记录每一步指针变化和链表状态。
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = curr.next;
        //及时清空远头节点的next，避免成环
        head.next = null;
        while (next != null) {
            prev = curr;
            curr = next;
            next = curr.next;
            //
            curr.next = prev;
        }
        return curr;
    }

    /**
     * 想起来了历史用栈，为啥没想到用递归？？
     *
     * @param head
     * @return
     */
    public ListNode reverseList0(ListNode head) {
        // 链表为空时直接返回，链表不为空则到返回最后一个节点
        if (head == null || head.next == null) {
            return head;
        }
        // newHead先指向最后一个节点，注意此时参数是倒数第二个节点
        // 这一步很精妙，每一次newHead都是指向空指针（链表为空）或保留在原链表中的最后一个节点（链表不空），作用就是返回新的头结点
        ListNode newHead = reverseList0(head.next);
        // 最后一个节点指向倒数第二个节点
        head.next.next = head;
        // 倒数第二个节点的下一节点置空。此时倒数第三个节点仍指向倒数第二个节点，下一次递归中将倒数第二个节点下一节点指向倒数第三个节点，不断重复这一过程
        head.next = null;
        return newHead;
    }


    public ListNode reverseList3(ListNode head) {
        ListNode ans = null;
        if (head == null) {
            return ans;
        }
        Deque<ListNode> stack = new LinkedList<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node);
            node = node.next;
        }
        ans = stack.pop();
        node = ans;
        //清理原头节点，不然成环了
        head.next = null;
        while (!stack.isEmpty()) {
            ListNode one = stack.pop();
            node.next = one;
            node = node.next;
        }
        return ans;
    }
}
