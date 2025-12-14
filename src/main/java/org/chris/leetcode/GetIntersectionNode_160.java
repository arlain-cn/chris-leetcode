package org.chris.leetcode;

import org.chris.leetcode.dto.GetIntersectionNode_160_TestData;
import org.chris.leetcode.dto.ListNode;

import java.util.HashSet;
import java.util.Set;

public class GetIntersectionNode_160 {

    public static void main(String[] args) {
        ListNode[] nodes = GetIntersectionNode_160_TestData.createYShapedIntersecting();
        GetIntersectionNode_160 getIntersectionNode160 = new GetIntersectionNode_160();
        System.out.println(getIntersectionNode160.getIntersectionNode(nodes[0], nodes[1]).val);
        System.out.println(getIntersectionNode160.getIntersectionNode2(nodes[0], nodes[1]).val);
        System.out.println(getIntersectionNode160.getIntersectionNode3(nodes[0], nodes[1]).val);
    }


    /**
     * 使用双指针法查找链表交点（推荐解法）
     * 时间复杂度: O(m+n)
     * 空间复杂度: O(1)
     *
     * @param headA 链表A的头节点
     * @param headB 链表B的头节点
     * @return
     * @return 交点节点，如果没有交点则返回null
     * @see 单链表一旦相交就不会分开了，兄得
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pointerA = headA;
        ListNode pointerB = headB;
        //证明：
        //一、如果存在相交：a的不相交长度为a，相交长度为c；b的不相交长度为b，相交长度为c；
        //1、若a=b，则在双指针的第一轮遍历到a时，遍历结束，返回结果
        //2、若a!=b，则在第一轮遍历不会有结果，且不会同时到末尾节点；会正常开启第二轮遍历，当遍历元素个数为 a+c+b时，两个链表会相交，返回结果；
        //二、不存在相交：a长度为m，b长度为n
        //1、若m=n，则同时遍历到末尾，返回null
        //2、若m=n。则在第二轮的遍历中，m+n位置时，遍历结束返回null
        while (pointerA != pointerB) {
            pointerA = pointerA == null ? headB : pointerA.next;
            pointerB = pointerB == null ? headA : pointerB.next;
        }
        return pointerA;
    }

    /**
     * 使用双指针法查找链表交点（推荐解法）
     * 时间复杂度: O(m+n)
     * 空间复杂度: O(m)
     *
     * @param headA 链表A的头节点
     * @param headB 链表B的头节点
     * @return 交点节点，如果没有交点则返回null
     * @see 单链表一旦相交就不会分开了，兄得
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Set<ListNode> listASet = new HashSet<>();
        ListNode nodeA = headA;
        while (nodeA != null) {
            listASet.add(nodeA);
            nodeA = nodeA.next;
        }

        ListNode nodeB = headB;
        while (nodeB != null) {
            if (listASet.contains(nodeB)) {
                return nodeB;
            } else {
                nodeB = nodeB.next;
            }
        }
        return null;
    }

    /**
     * 时间复杂度: O(m*n)
     * 空间复杂度: O(1)
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        if (headA == headB) {
            return headA;
        }
        ListNode nodeA = headA;
        while (nodeA != null) {
            ListNode nodeB = headB;
            while (nodeB != null) {
                if (nodeA == nodeB) {
                    return nodeA;
                }
                nodeB = nodeB.next;
            }
            nodeA = nodeA.next;
        }
        return null;
    }
}
