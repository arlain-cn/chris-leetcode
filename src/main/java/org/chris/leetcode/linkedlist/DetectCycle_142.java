package org.chris.leetcode.linkedlist;

import org.chris.leetcode.dto.GetIntersectionNode_160_TestData;
import org.chris.leetcode.dto.ListNode;

import java.util.HashSet;
import java.util.Set;

public class DetectCycle_142 {

    public static void main(String[] args) {
        DetectCycle_142 hasCycle142 = new DetectCycle_142();
        ListNode head1 = GetIntersectionNode_160_TestData.createNormalByArr(new int[]{3, 2, 0, -4});
        ListNode head = GetIntersectionNode_160_TestData.createCycleByArr(new int[]{3, 2, 0, -4}, 1);
        System.out.println(hasCycle142.detectCycle2(head1));
        System.out.println(hasCycle142.detectCycle2(head));
    }

    /**
     * 一开始令快指针 fast 和慢指针 slow 都位于头部，然后快指针每次走 2 步，慢指针每次走 1 步，因此快指针走的步数始终等于快指针的 2 倍。
     * <p>
     * 假设从头到环入口的距离为 a，环长度为 b，相遇的时候 a 在环内走了 x，b 比 a 多走了 n 环（n 为正整数），那么有
     * <p>
     * a 走的距离：a + x
     * b 走的距离：a + x + nb
     * 距离关系：2(a + x) = a + x + nb
     * 可以得到： a + x = nb，也就是说，慢指针再往前走 a，在环内走的总距离就是 nb 即整数圈，慢指针就回到了环入口
     * <p>
     * 而 a 是从头到环入口的距离为 a，所以我们再新建一个指针 ptr，ptr 和 slow 每次同时走 1 步，当 ptr 走了 a 步到环入口的时候，slow 也正好达到环入口，而由于速度一样，它们只有可能在环入口相遇，所以相遇的位置就是环入口位置
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    public ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }

        return null;
    }

    /**
     * 方法一：哈希表
     * 思路与算法
     * <p>
     * 一个非常直观的思路是：我们遍历链表中的每个节点，并将它记录下来；一旦遇到了此前遍历过的节点，就可以判定链表中存在环。借助哈希表可以很方便地实现。
     *
     * @param head
     * @return
     */
    public ListNode detectCycle1(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node != null) {
            if (set.contains(node)) {
                return node;
            }
            set.add(node);
            node = node.next;
        }
        return node;
    }

}
