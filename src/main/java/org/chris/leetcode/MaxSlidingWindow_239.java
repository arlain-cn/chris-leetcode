package org.chris.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxSlidingWindow_239 {

    public static void main(String[] args) {
        MaxSlidingWindow_239 maxSlidingWindow239 = new MaxSlidingWindow_239();
//        System.out.println(Arrays.toString(maxSlidingWindow239.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
//        System.out.println(Arrays.toString(maxSlidingWindow239.maxSlidingWindow(new int[]{1}, 1)));
//        System.out.println(Arrays.toString(maxSlidingWindow239.maxSlidingWindow(new int[]{9, 10, 9, -7, -4, -8, 2, -6}, 5)));
        System.out.println(Arrays.toString(maxSlidingWindow239.maxSlidingWindow(new int[]{1, -1}, 1)));
    }

    /**
     * Deque 接口:Java集合框架的Deque接口提供了双端队列(Deque)的功能。它继承了Queue接口。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        int[] ans = new int[nums.length - k + 1];


//        todo
        return ans;
    }

    /**
     * PriorityQueue 的底层数据结构
     * PriorityQueue 是一种基于 堆（Heap） 实现的优先级队列，底层采用 数组（Object[]） 存储数据，并通过 二叉堆（Binary Heap） 来维护优先级关系。默认情况下，PriorityQueue 使用 最小堆（Min Heap），即堆顶元素是队列中优先级最高的元素。
     * <p>
     * 方法一：优先队列
     * 思路与算法
     * <p>
     * 对于「最大值」，我们可以想到一种非常合适的数据结构，那就是优先队列（堆），其中的大根堆可以帮助我们实时维护一系列元素中的最大值。
     * <p>
     * 对于本题而言，初始时，我们将数组 nums 的前 k 个元素放入优先队列中。每当我们向右移动窗口时，我们就可以把一个新的元素放入优先队列中，此时堆顶的元素就是堆中所有元素的最大值。然而这个最大值可能并不在滑动窗口中，在这种情况下，这个值在数组 nums 中的位置出现在滑动窗口左边界的左侧。因此，当我们后续继续向右移动窗口时，这个值就永远不可能出现在滑动窗口中了，我们可以将其永久地从优先队列中移除。
     * <p>
     * 我们不断地移除堆顶的元素，直到其确实出现在滑动窗口中。此时，堆顶元素就是滑动窗口中的最大值。为了方便判断堆顶元素与滑动窗口的位置关系，我们可以在优先队列中存储二元组 (num,index)，表示元素 num 在数组中的下标为 index。
     * <p>
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return pair2[0] - pair1[0] == 0 ? pair2[1] - pair1[1] : pair2[0] - pair1[0];
            }
        });

        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});
        }

        int[] ans = new int[nums.length - k + 1];
        ans[0] = pq.peek()[0];

        for (int i = k; i < nums.length; i++) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] < i - k + 1) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }

        return ans;
    }
}
