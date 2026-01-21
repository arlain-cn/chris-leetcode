package org.chris.leetcode.linkedlist;

import org.chris.leetcode.dto.GetIntersectionNode_160_TestData;
import org.chris.leetcode.dto.ListNode;

public class AddTwoNumbers_2 {

    public static void main(String[] args) {
        AddTwoNumbers_2 addTwoNumbers2 = new AddTwoNumbers_2();
        ListNode list1 = GetIntersectionNode_160_TestData.createNormalByArr(new int[]{1, 2, 4});
        ListNode list2 = GetIntersectionNode_160_TestData.createNormalByArr(new int[]{1, 3, 4});
        System.out.println(addTwoNumbers2.addTwoNumbers(list1, list2).toListString());
    }

    /**
     * 由于输入的两个链表都是逆序存储数字的位数的，因此两个链表中同一位置的数字可以直接相加
     * 此外，如果链表遍历结束后，有 carry>0，还需要在答案链表的后面附加一个节点，节点的值为 carry
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        //虚拟哨兵节点，方便处理节点头逻辑
        ListNode preHead = new ListNode(-1);
        ListNode res = preHead;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            res.next = new ListNode(sum);
            l1 = l1.next;
            l2 = l2.next;
            res = res.next;
        }
        //只剩下一个list有值
        while (l1 != null) {
            int sum = l1.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            res.next = new ListNode(sum);
            l1 = l1.next;
            res = res.next;
        }

        while (l2 != null) {
            int sum = l2.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            res.next = new ListNode(sum);
            l2 = l2.next;
            res = res.next;
        }
        //处理最后一个进位
        if (carry > 0) {
            res.next = new ListNode(carry);
        }
        return preHead.next;
    }

    /**
     * 简化迭代逻辑
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode preHead = new ListNode(-1);
        ListNode ptr = preHead;

        while (l1 != null || l2 != null || carry > 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            ptr.next = new ListNode(sum % 10);
            ptr = ptr.next;
            carry = sum / 10;
        }
        return preHead.next;
    }

    public ListNode addTwoNumbersFac(ListNode l1, ListNode l2) {
        return addTwo(l1, l2, 0);
    }

    public ListNode addTwo(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }
        int sum = carry;
        if (l1 != null) {
            sum += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            sum += l2.val;
            l2 = l2.next;
        }
        ListNode node = new ListNode(sum % 10);
        node.next = addTwo(l1, l2, sum / 10);
        return node;
    }
}
