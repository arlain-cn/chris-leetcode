package org.chris.leetcode;

import org.chris.leetcode.dto.GetIntersectionNode_160_TestData;
import org.chris.leetcode.dto.ListNode;

public class MergeTwoLists_21 {
    public static void main(String[] args) {
        MergeTwoLists_21 mergeTwoLists21 = new MergeTwoLists_21();
        ListNode list1 = GetIntersectionNode_160_TestData.createNormalByArr(new int[]{1, 2, 4});
        ListNode list2 = GetIntersectionNode_160_TestData.createNormalByArr(new int[]{1, 3, 4});

        ListNode ans = mergeTwoLists21.mergeTwoLists(list1, list2);
        System.out.println(ans.toListString());
    }

    /**
     * list1[0]<list2[0]:
     * list1[0]+merge(list1[1:],list2)
     * <p>
     * otherwise
     * list2[0]+merge(list1,list2[1:])
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    /**
     * 首先，我们设定一个哨兵节点 prehead ，这可以在最后让我们比较容易地返回合并后的链表。我们维护一个 prev 指针，我们需要做的是调整它的 next 指针
     * 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        //首先，我们设定一个哨兵节点 prehead ，这可以在最后让我们比较容易地返回合并后的链表。我们维护一个 prev 指针，我们需要做的是调整它的 next 指针
        ListNode prevHead = new ListNode(-1);
        ListNode prev = prevHead;

        ListNode ptr1 = list1;
        ListNode ptr2 = list2;
        while (ptr1 != null && ptr2 != null) {
            if (ptr1.val < ptr2.val) {
                prev.next = ptr1;
                prev = prev.next;
                ptr1 = ptr1.next;
            } else {
                prev.next = ptr2;
                prev = prev.next;
                ptr2 = ptr2.next;
            }
        }
        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = ptr1 == null ? ptr2 : ptr1;
        return prevHead.next;
    }

    /**
     * @param list1
     * @param list2
     * @return
     * @see 太繁琐了:可以搞一个前置虚拟节点，避免head节点设置难题；当只有一个list有值或只剩下一个有值时，根据链表特性直接next指向即可
     */
    public ListNode mergeTwoLists0(ListNode list1, ListNode list2) {
        ListNode ptr1 = list1;
        ListNode ptr2 = list2;
        ListNode ans = null;
        ListNode ptr = null;
        while (ptr1 != null && ptr2 != null) {
            if (ptr1.val < ptr2.val) {
                if (ans == null) {
                    ptr = ptr1;
                    ans = ptr;
                    ptr1 = ptr1.next;
                } else {
                    ptr.next = ptr1;
                    ptr = ptr.next;
                    ptr1 = ptr1.next;
                }
            } else {
                if (ans == null) {
                    ptr = ptr2;
                    ans = ptr;
                    ptr2 = ptr2.next;
                } else {
                    ptr.next = ptr2;
                    ptr = ptr.next;
                    ptr2 = ptr2.next;
                }
            }
        }

        while (ptr1 != null) {
            if (ans == null) {
                ptr = ptr1;
                ans = ptr;
                ptr1 = ptr1.next;
            } else {
                ptr.next = ptr1;
                ptr = ptr.next;
                ptr1 = ptr1.next;
            }
        }

        while (ptr2 != null) {
            if (ans == null) {
                ptr = ptr2;
                ans = ptr;
                ptr2 = ptr2.next;
            } else {
                ptr.next = ptr2;
                ptr = ptr.next;
                ptr2 = ptr2.next;
            }
        }
        return ans;
    }
}
