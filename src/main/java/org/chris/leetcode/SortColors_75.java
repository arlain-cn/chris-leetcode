package org.chris.leetcode;

import java.util.Arrays;

public class SortColors_75 {

    /**
     * 示例 1：
     * <p>
     * 输入：nums = [2,0,2,1,1,0]
     * 输出：[0,0,1,1,2,2]
     * 示例 2：
     * <p>
     * 输入：nums = [2,0,1]
     * 输出：[0,1,2]
     *
     * @param args
     */
    public static void main(String[] args) {

        SortColors_75 sortColors75 = new SortColors_75();
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        sortColors75.sortColors2(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{2, 0, 1};
        sortColors75.sortColors2(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{1, 0};
        sortColors75.sortColors2(nums);
        System.out.println(Arrays.toString(nums));
    }


    public void sortColors2(int[] nums) {
        int n = nums.length;
        int ptr0 = 0, ptr1 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                int tmp = nums[ptr1];
                nums[ptr1] = nums[i];
                nums[i] = tmp;
                ptr1++;
            } else if (nums[i] == 0) {
                if (ptr0 == ptr1) {
                    int tmp = nums[ptr0];
                    nums[ptr0] = nums[i];
                    nums[i] = tmp;
                } else if (ptr0 < ptr1) {
                    int tmp = nums[ptr1];
                    nums[ptr1] = nums[i];
                    nums[i] = tmp;

                    tmp = nums[ptr0];
                    nums[ptr0] = nums[ptr1];
                    nums[ptr1] = tmp;

                    //这个语意里，元素会被先迁移对，又被迁移了
                    //int tmp = nums[ptr1];
                    //nums[ptr1] = nums[ptr0];
                    //nums[ptr0] = nums[i];
                    //nums[i] = tmp;
                }
                ptr0++;
                ptr1++;
            }
        }
    }


    /**
     * 方法一：单指针
     * 思路与算法
     * <p>
     * 我们可以考虑对数组进行两次遍历。在第一次遍历中，我们将数组中所有的 0 交换到数组的头部。在第二次遍历中，我们将数组中所有的 1 交换到头部的 0 之后。此时，所有的 2 都出现在数组的尾部，这样我们就完成了排序。
     * <p>
     * 具体地，我们使用一个指针 ptr 表示「头部」的范围，ptr 中存储了一个整数，表示数组 nums 从位置 0 到位置 ptr−1 都属于「头部」。ptr 的初始值为 0，表示还没有数处于「头部」。
     * <p>
     * 在第一次遍历中，我们从左向右遍历整个数组，如果找到了 0，那么就需要将 0 与「头部」位置的元素进行交换，并将「头部」向后扩充一个位置。在遍历结束之后，所有的 0 都被交换到「头部」的范围，并且「头部」只包含 0。
     * <p>
     * 在第二次遍历中，我们从「头部」开始，从左向右遍历整个数组，如果找到了 1，那么就需要将 1 与「头部」位置的元素进行交换，并将「头部」向后扩充一个位置。在遍历结束之后，所有的 1 都被交换到「头部」的范围，并且都在 0 之后，此时 2 只出现在「头部」之外的位置，因此排序完成。
     * <p>
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/sort-colors/solutions/437968/yan-se-fen-lei-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    public void sortColors(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                int v = nums[ptr];
                nums[ptr] = nums[i];
                nums[i] = v;
                ptr++;
            }
        }

        ptr = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] == 2) {
                int v = nums[ptr];
                nums[ptr] = nums[i];
                nums[i] = v;
                ptr--;
            }
        }
    }


}
