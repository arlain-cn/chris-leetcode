package org.chris.leetcode.design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache_146 {

    /**
     * 146. LRU 缓存
     * 实现 LRUCache 类：
     * 1、LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
     * 2、int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * 3、void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
     * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
     * 方法一：哈希表 + 双向链表
     * <p>
     * 实现本题的两种操作，需要用到一个哈希表和一个双向链表。
     *
     * @param args
     */
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        /**
         * 输入
         * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
         * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
         * 输出
         * [null, null, null, 1, null, -1, null, -1, 3, 4]
         */

        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
        System.out.println("-----------------------");
        /**
         * ["LRUCache","put","put","put","put","get","get"]
         * [[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
         * 输出
         * [null,null,null,null,null,-1,3]
         */
        LRUCache lruCache2 = new LRUCache(2);
        lruCache2.put(2, 1);
        lruCache2.put(1, 1);

        lruCache2.put(2, 3);
        lruCache2.put(4, 1);
        System.out.println(lruCache2.get(1));
        System.out.println(lruCache2.get(2));
    }

    static class LRUCache {
        class DLinkedNode {
            public int key;
            public int val;
            public DLinkedNode next;
            public DLinkedNode prev;

            public DLinkedNode() {
            }

            public DLinkedNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }


        public int capacity;
        public int size;
        private DLinkedNode dummyHead;
        private DLinkedNode dummyTail;
        private Map<Integer, DLinkedNode> cache = new HashMap<>((int) (capacity / 0.75));

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.dummyHead = new DLinkedNode();
            this.dummyTail = new DLinkedNode();
            this.dummyHead.next = this.dummyTail;
            this.dummyTail.prev = dummyHead;
            this.size = 0;
        }

        public int get(int key) {
            DLinkedNode curNode = cache.get(key);
            if (curNode == null) {
                return -1;
            }
            removeCurNode(curNode);
            moveCurrToHead(curNode);
            return curNode.val;
        }

        /**
         * “最近最少使用” = “最久未被访问”：每次访问（get或put）都会将该条目标记为“最近使用”，因此未被访问的条目会逐渐“老化”。
         * put的时候往前添加
         *
         * @param key
         * @param value
         */
        public void put(int key, int value) {
            DLinkedNode curNode = cache.get(key);
            if (curNode == null) {
                curNode = new DLinkedNode(key, value);
                if (this.size >= capacity) {
                    DLinkedNode curPrev = removeTailNode();
                    cache.remove(curPrev.key);
                } else {
                    size++;
                }
                moveCurrToHead(curNode);
                cache.put(key, curNode);
            } else {
                removeCurNode(curNode);
                moveCurrToHead(curNode);
                curNode.val = value;
            }
        }

        /**
         * 移除尾节点
         *
         * @return
         */
        private DLinkedNode removeTailNode() {
            DLinkedNode curPrev = this.dummyTail.prev;
            DLinkedNode curPrePrev = curPrev.prev;
            curPrePrev.next = this.dummyTail;
            this.dummyTail.prev = curPrePrev;
            return curPrev;
        }

        /**
         * 把当前节点移动到头节点
         *
         * @param curNode
         */
        private void moveCurrToHead(DLinkedNode curNode) {
            DLinkedNode oriHeadNext = this.dummyHead.next;
            this.dummyHead.next = curNode;
            curNode.prev = this.dummyHead;
            curNode.next = oriHeadNext;
            oriHeadNext.prev = curNode;
        }

        /**
         * 移除当前节点
         *
         * @param curNode
         */
        private void removeCurNode(DLinkedNode curNode) {
            DLinkedNode oriTailNext = curNode.next;
            oriTailNext.prev = curNode.prev;
            curNode.prev.next = oriTailNext;
        }
    }


}
