package org.chris.leetcode._dto;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public String toListString() {
        String ret = String.valueOf(val);
        ListNode node = next;
        while (node != null) {
            ret += ", " + node.val;
            node = node.next;
        }
        return ret;
    }

    @Override
    public String toString() {
        return "ListNode{" + "val=" + val;
    }
}
