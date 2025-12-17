package org.chris.leetcode;

import org.chris.leetcode.dto.GetIntersectionNode_160_TestData;
import org.chris.leetcode.dto.ListNode;

import java.util.ArrayList;
import java.util.List;

public class IsPalindrome_234 {

    public static void main(String[] args) {
        IsPalindrome_234 isPalindrome234 = new IsPalindrome_234();
        ListNode head = GetIntersectionNode_160_TestData.createNormalByArr(new int[]{1, 2, 2, 2, 2, 1});
        System.out.println(isPalindrome234.isPalindrome1(head));
        System.out.println(isPalindrome234.isPalindrome2(head));
        System.out.println(isPalindrome234.isPalindrome(head));
    }

    /**
     *
     * 空间优化：用数组存储节点值检查回文的空间复杂度是O(n)，如何用O(1)额外空间解决？（提示：快慢指针找中点 + 反转后半部分）
     * 快慢指针：当快指针到达链表末尾时，慢指针在什么位置？（例如，链表长度为偶数/奇数时的中点位置）
     * 反转与比较：反转后半部分链表后，如何逐节点比较前半部分和反转后的后半部分？（提示：比较到中点即停止）
     * 用小例子模拟：head = [1,2,2,1]，记录快慢指针移动、反转后链表状态及比较过程。
     * <p>
     * 1、找到前半部分链表的尾节点。
     * 2、反转后半部分链表。
     * 3、判断是否回文。
     * 4、恢复链表。
     * 5、返回结果。
     * <p>
     * 执行步骤一，我们可以计算链表节点的数量，然后遍历链表找到前半部分的尾节点。
     *
     * @param head
     * @return
     * @see 我们也可以使用快慢指针在一次遍历中找到,慢指针一次走一步,快指针一次走两步,快慢指针同时出发;当快指针移动到链表的末尾时，慢指针恰好到链表的中间。通过慢指针将链表分为两部分。
     */
    public boolean isPalindrome(ListNode head) {
        ListNode slowPt = head;
        ListNode fastPt = head;

        //我们也可以使用快慢指针在一次遍历中找到,慢指针一次走一步,快指针一次走两步,快慢指针同时出发;当快指针移动到链表的末尾时，慢指针恰好到链表的中间。通过慢指针将链表分为两部分。
        while (fastPt.next != null && fastPt.next.next != null) {
            slowPt = slowPt.next;
            fastPt = fastPt.next.next;
        }
        ListNode firstHalfEnd = slowPt;
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);
        boolean ans = true;
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        while (ans && p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        firstHalfEnd.next = reverseList(secondHalfStart);
        return ans;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode prev = null;
        ListNode curr = head;

        ListNode next = head.next;
        head.next = null;
        while (next != null) {
            prev = curr;
            curr = next;
            next = next.next;
            curr.next = prev;
        }

        return curr;
    }

    /**
     * 方法二：递归
     * currentNode 指针是先到尾节点，由于递归的特性再从后往前进行比较。frontPointer 是递归函数外的指针。若 currentNode.val != frontPointer.val 则返回 false。反之，frontPointer 向前移动并返回 true。
     *
     * @param head
     * @return
     */
    private ListNode frontPointer = null;

    public boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            //如果下个节点不满足，这里返回false
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            //如果下个节点满足，这里比较值
            if (frontPointer.val != currentNode.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }

    /**
     * 方法一：将值复制到数组中后用双指针法
     *
     * @param head
     * @return
     */
    public boolean isPalindrome1(ListNode head) {
        List<Integer> vals = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            vals.add(node.val);
            node = node.next;
        }
        int left = 0, right = vals.size() - 1;
        while (left < right) {
            if (vals.get(left++) != vals.get(right--)) {
                return false;
            }
        }
        return true;
    }
}

