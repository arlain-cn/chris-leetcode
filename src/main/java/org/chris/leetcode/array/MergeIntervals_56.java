package org.chris.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals_56 {

    public static void main(String[] args) {
        MergeIntervals_56 mergeIntervals56 = new MergeIntervals_56();
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] mergeIntervals = mergeIntervals56.merge(intervals);
        Arrays.stream(mergeIntervals).forEach(obj -> System.out.println(Arrays.toString(obj)));
    }

    /**
     * 排序的作用：为什么必须先按区间起始点排序？（例如，未排序时[[2,6],[1,3]]无法直接合并，排序后变为[[1,3],[2,6]]）
     * 合并条件：遍历时如何判断当前区间与前一个区间重叠？（提示：当前区间起始点 ≤ 前一个区间结束点则合并）
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[][] ans = new int[intervals.length][2];
        int index = 0;
        int[] prev = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] <= prev[1]) {
                // int[] merge = new int[]{Math.min(prev[0], interval[0]), Math.max(prev[1], interval[1])}; 取最小值的时候不需要min了，因为已经排序过了
                int[] merge = new int[]{prev[0], Math.max(prev[1], interval[1])};
                prev = merge;
            } else {
                ans[index++] = prev;
                prev = interval;
            }
        }
        ans[index++] = prev;
        return Arrays.copyOfRange(ans, 0, index);
    }

    public int[][] merge1(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> ans = new ArrayList<>(intervals.length);
        int[] prev = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] <= prev[1]) {
                int[] merge = new int[]{Math.min(prev[0], interval[0]), Math.max(prev[1], interval[1])};
                prev = merge;
            } else {
                ans.add(prev);
                prev = interval;
            }
        }
        ans.add(prev);
        return ans.toArray(new int[ans.size()][2]);
    }
}
