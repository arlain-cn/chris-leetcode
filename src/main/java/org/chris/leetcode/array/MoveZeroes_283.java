package org.chris.leetcode.array;

import java.util.Arrays;

public class MoveZeroes_283 {

    public static void main(String[] args) {
        MoveZeroes_283 moveZeroes283 = new MoveZeroes_283();

        int[] nums = new int[]{0, 1, 0, 3, 12};
        moveZeroes283.moveZeroes3(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0};
        moveZeroes283.moveZeroes3(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0, 0, 1};
        moveZeroes283.moveZeroes3(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 小白思路，先统计非零的，把非零的放前面，放完其他的都放0
     */

    public void moveZeroes3(int[] nums) {
        int left = 0, right = 0;

        for (; right < nums.length; right++) {
            if (nums[right] != 0) {
                nums[left] = nums[right];
                left++;
            }
        }
        for (;left < nums.length; left++) {
            nums[left] = 0;
        }
    }

    /**
     * 使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
     * <p>
     * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
     * <p>
     * 注意到以下性质：
     * <p>
     * @see 左指针左边均为非零数,右指针左边直到左指针处均为零(就是左边一串非0，中间一串0，右边是未处理的)
     * <p>
     * 因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
     * <p>
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/move-zeroes/solutions/489622/yi-dong-ling-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        int left = 0, right = 0;
        for (; right < nums.length; right++) {
            if (nums[right] != 0) {
                int tmp = nums[right];
                nums[right] = nums[left];
                nums[left] = tmp;
                left++;
            }
        }

//        while (right < nums.length) {
//            if (nums[right] != 0) {
//                int tmp = nums[right];
//                nums[right] = nums[left];
//                nums[left] = tmp;
//                left++;
//            }
//            right++;
//        }
    }

    /**
     * 对于 0 0 开头的无解
     *
     * @param nums
     */
    public void moveZeroes_x(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int k = i, j = i + 1;

                for (; j < nums.length; ) {
                    int tmp = nums[k];
                    nums[k] = nums[j];
                    nums[j] = tmp;
                    k++;
                    j++;
                }
            }
        }
    }
}
