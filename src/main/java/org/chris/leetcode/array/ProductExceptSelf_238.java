package org.chris.leetcode.array;

import java.util.Arrays;

public class ProductExceptSelf_238 {

    public static void main(String[] args) {
        ProductExceptSelf_238 productExceptSelf238 = new ProductExceptSelf_238();
        System.out.println(Arrays.toString(productExceptSelf238.productExceptSelf(new int[]{1, 2, 3, 4})));
    }


    /**
     * 方法一：左右乘积列表
     * <p>
     * 我们不必将所有数字的乘积除以给定索引处的数字得到相应的答案，而是利用索引左侧所有数字的乘积和右侧所有数字的乘积（即前缀与后缀）相乘得到答案。对于给定索引 i，我们将使用它左边所有数字的乘积乘以右边所有数字的乘积。下面让我们更加具体的描述这个算法。
     * <p>
     * 算法：
     * 1、初始化两个空数组 L 和 R。对于给定索引 i，L[i] 代表的是 i 左侧所有数字的乘积，R[i] 代表的是 i 右侧所有数字的乘积。
     * 2、我们需要用两个循环来填充 L 和 R 数组的值。对于数组 L，L[0] 应该是 1，因为第一个元素的左边没有元素。对于其他元素：L[i] = L[i-1] * nums[i-1]。
     * 3、同理，对于数组 R，R[length-1] 应为 1。length 指的是输入数组的大小。其他元素：R[i] = R[i+1] * nums[i+1]。
     * 4、当 R 和 L 数组填充完成，我们只需要在输入数组上迭代，且索引 i 处的值为：L[i] * R[i]。
     * <p>
     * 然而这样的解决方法有一个问题，就是如果输入数组中出现 0，那么这个方法就失效了。而且在问题中说明了不允许使用除法运算。这增加了这个问题的难度。
     * <p>
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf1(int[] nums) {
        int len = nums.length;
        // L 和 R 分别表示左右两侧的乘积列表
        int[] L = new int[len];
        int[] R = new int[len];

        // L[i] 为索引 i 左侧所有元素的乘积
        // 对于索引为 '0' 的元素，因为左侧没有元素，所以 L[0] = 1
        L[0] = 1;
        for (int i = 1; i < len; i++) {
            L[i] = L[i - 1] * nums[i - 1];
        }
        // R[i] 为索引 i 右侧所有元素的乘积
        // 对于索引为 'length-1' 的元素，因为右侧没有元素，所以 R[length-1] = 1
        R[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            R[i] = R[i + 1] * nums[i + 1];
        }
        // 对于索引 i，除 nums[i] 之外其余各元素的乘积就是左侧所有元素的乘积乘以右侧所有元素的乘积
        int[] answer = new int[len];
        for (int i = 0; i < len; i++) {
            answer[i] = L[i] * R[i];
        }
        return answer;
    }

    /**
     * 方法二：空间复杂度 O(1) 的方法
     * 由于输出数组不算在空间复杂度内，那么我们可以将 L 或 R 数组用输出数组来计算。先把输出数组当作 L 数组来计算，然后再动态构造 R 数组得到结果。让我们来看看基于这个思想的算法。
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;

        int[] answer = new int[len];
        // answer[i] 表示索引 i 左侧所有元素的乘积
        // 因为索引为 '0' 的元素左侧没有元素， 所以 answer[0] = 1
        answer[0] = 1;
        for (int i = 1; i < len; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        // R 为右侧所有元素的乘积
        // 刚开始右边没有元素，所以 R = 1
        int R = 1;
        for (int i = len - 1; i >= 0; i--) {
            // 对于索引 i，左边的乘积为 answer[i]，右边的乘积为 R
            answer[i] = R * answer[i];
            // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
            R *= nums[i];
        }

        return answer;
    }
}
