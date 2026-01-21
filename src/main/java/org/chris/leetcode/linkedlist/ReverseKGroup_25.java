package org.chris.leetcode.linkedlist;

import org.chris.leetcode.dto.GetIntersectionNode_160_TestData;
import org.chris.leetcode.dto.ListNode;

import java.util.ArrayList;
import java.util.List;

public class ReverseKGroup_25 {

    public static void main(String[] args) {
        ReverseKGroup_25 reverseKGroup25 = new ReverseKGroup_25();
        ListNode head = GetIntersectionNode_160_TestData.createNormalByArr(new int[]{1, 2, 3, 4, 5});
        System.out.println(reverseKGroup25.reverseKGroup(head, 1).toListString());

        //ListNode kHead = reverseKGroup25.reverseList(head, 2);
        //System.out.println(kHead.toListString());
//        ListNode[] myReverses = reverseKGroup25.myReverse(head, head.next);
//        System.out.println(myReverses[0] + "; " + myReverses[1]);
    }

    public ListNode reverseKGroup1(ListNode head, int k) {
        ListNode hair = new ListNode(-1, head);
        ListNode prev = hair;
        ListNode blockHead = prev.next;
        ListNode blockTail = null;
        //下一组的头节点为空，循环结束
        while (blockHead != null) {
            // 为了for遍历k次到尾部，从prev开始
            blockTail = prev;
            for (int i = 1; i <= k; i++) {
                blockTail = blockTail.next;
                //最后不能组成一个完整的分组时，最后一段不需要反转
                if (blockTail == null) {
                    return hair.next;
                }
            }
            ListNode[] myReverses = myReverse(blockHead, blockTail);
            blockHead = myReverses[0];
            blockTail = myReverses[1];
            //从prev开始重建链接到新的blockHead
            prev.next = blockHead;
            //下一分组的前置
            prev = blockTail;
            //下一分组的起点头节点
            blockHead = blockTail.next;
            blockTail = null;
        }
        return hair.next;
    }

    /**
     * 递归写法
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        ListNode blockHead = head;
        ListNode blockTail = head;
        for (int i = 1; i < k; i++) {
            blockTail = blockTail.next;
            if (blockTail == null) {
                return head;
            }
        }

        ListNode[] myReverses = myReverse(blockHead, blockTail);
        head = myReverses[0];
        blockTail = myReverses[1];
        blockTail.next = reverseKGroup(blockTail.next, k);
        return head;
    }

    public ListNode[] myReverse(ListNode head, ListNode tail) {
        //prev 默认值设置为原tail.next 时，在拼接原head的next的时候，原head会指向原tail的next，链表尾部直接接续上了
        ListNode prev = tail.next;
        ListNode curr = head;
        //用prev去判断是不是等于tail而不是用curr，是因为tail也是需要处理的
        while (prev != tail) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return new ListNode[]{tail, head};
    }

    public ListNode reverseKGroup0(ListNode head, int k) {
        List<ListNode> groupHeadArr = new ArrayList<>();
        int i = 0;
        ListNode curr = head;
        while (curr != null) {
            if (i % k == 0) {
                groupHeadArr.add(curr);
            }
            i++;
            curr = curr.next;
        }

        ListNode preNewHead = new ListNode(-1);
        ListNode ans = preNewHead;
        for (ListNode ele : groupHeadArr) {
            ListNode kHead = reverseList(ele, k);
            preNewHead.next = kHead;
            preNewHead = ele;
        }
        return ans.next;
    }


    public ListNode reverseList(ListNode head, int k) {
        ListNode prev = null;
        ListNode curr = head;
        int i = 1;
        boolean isMeet = false;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            if (i == k) {
                isMeet = true;
                break;
            }
            curr = next;
            i++;
        }
        if (!isMeet) {
            return reverseList(prev);
        }
        return prev;
    }

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
}
