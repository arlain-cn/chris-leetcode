package org.chris.leetcode.linkedlist;

import org.chris.leetcode.dto.GetIntersectionNode_160_TestData;
import org.chris.leetcode.dto.ListNode;

public class HasCycle_141 {

    public static void main(String[] args) {
        HasCycle_141 hasCycle141 = new HasCycle_141();
        ListNode head1 = GetIntersectionNode_160_TestData.createNormalByArr(new int[]{3, 2, 0, -4});
        ListNode head = GetIntersectionNode_160_TestData.createCycleByArr(new int[]{3, 2, 0, -4}, 1);
        System.out.println(hasCycle141.hasCycle2(head1));
        System.out.println(hasCycle141.hasCycle2(head));
    }


    /**
     * 快慢指针
     *
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 我们可以根据上述思路来解决本题。具体地，我们定义两个指针，一快一慢。慢指针每次只移动一步，而快指针每次移动两步。初始时，慢指针在位置 head，而快指针在位置 head.next。这样一来，如果在移动的过程中，快指针反过来追上慢指针，就说明该链表为环形链表。否则快指针将到达链表尾部，该链表不为环形链表。
     * <p>
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
