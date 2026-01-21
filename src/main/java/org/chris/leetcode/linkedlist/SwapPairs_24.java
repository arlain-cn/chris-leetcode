package org.chris.leetcode.linkedlist;

import org.chris.leetcode.dto.GetIntersectionNode_160_TestData;
import org.chris.leetcode.dto.ListNode;

public class SwapPairs_24 {

    public static void main(String[] args) {
        SwapPairs_24 swapPairs24 = new SwapPairs_24();
        ListNode list1 = GetIntersectionNode_160_TestData.createNormalByArr(new int[]{1, 2, 3, 4});
        System.out.println(swapPairs24.swapPairs(list1).toListString());
    }


    /**
     * 递归的终止条件是链表中没有节点，或者链表中只有一个节点，此时无法进行交换。
     * <p>
     * 如果链表中至少有两个节点，则在两两交换链表中的节点之后，原始链表的头节点变成新的链表的第二个节点，原始链表的第二个节点变成新的链表的头节点。链表中的其余节点的两两交换可以递归地实现。在对链表中的其余节点递归地两两交换之后，更新节点之间的指针关系，即可完成整个链表的两两交换。
     * <p>
     * 用 head 表示原始链表的头节点，新的链表的第二个节点，用 newHead 表示新的链表的头节点，原始链表的第二个节点，则原始链表中的其余节点的头节点是 newHead.next。令 head.next = swapPairs(newHead.next)，表示将其余节点进行两两交换，交换后的新的头节点为 head 的下一个节点。然后令 newHead.next = head，即完成了所有节点的交换。最后返回新的链表的头节点 newHead
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(head.next.next);
        newHead.next = head;
        return newHead;
    }


    /**
     * 创建哑结点 dummyHead，令 dummyHead.next = head。令 temp 表示当前到达的节点，初始时 temp = dummyHead。每次需要交换 temp 后面的两个节点。
     * <p>
     * 具体而言，交换之前的节点关系是 temp -> node1 -> node2，交换之后的节点关系要变成 temp -> node2 -> node1，因此需要进行如下操作。
     * temp.next = node2
     * node1.next = node2.next
     * node2.next = node1
     *
     * @param head
     * @return
     */
    public ListNode swapPairs1(ListNode head) {
        //相隔1的快慢指针，每次前进2
        ListNode preHead = new ListNode(-1);
        preHead.next = head;
        ListNode prev = preHead;

        while (prev.next != null && prev.next.next != null) {
            ListNode left = prev.next;
            ListNode right = prev.next.next;
            //交换指针
            left.next = right.next;
            right.next = left;
            //移动到原right，即现left地方，prev是下一个left的前一
            prev.next = right;
            prev = left;
        }
        return preHead.next;
    }
}
