package org.chris.leetcode;

public class FirstMissingPositive_41 {

    public static void main(String[] args) {
        FirstMissingPositive_41 firstMissingPositive41 = new FirstMissingPositive_41();
//        System.out.println(firstMissingPositive41.firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
        System.out.println(firstMissingPositive41.firstMissingPositive(new int[]{1, 1}));
        ;
    }

    /**
     * LeetCode 41题（缺失的第一个正数）需要在O(n)时间复杂度和常数空间复杂度内解决。思考以下问题：
     * <p>
     * 缺失正数的范围：最小可能值是多少？最大可能值是多少？（例如，数组长度为n时，缺失数在1~n+1之间）
     * 关键观察：如何利用数组索引本身作为哈希表？哪些元素可以安全忽略（如负数、0或大于n的数）？
     * 尝试设计：如果将每个正数x放到索引x-1的位置，会发生什么？如何处理重复元素？
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        //缺实际上，对于一个长度为 N 的数组，其中没有出现的最小正整数只能在 [1,N+1] 中。这是因为如果 [1,N] 都出现了，那么答案是 N+1，否则答案是 [1,N] 中没有出现的最小正整数。
        //这样一来，我们将所有在 [1,N] 范围内的数放入哈希表，也可以得到最终的答案。而给定的数组恰好长度为 N，这让我们有了一种将数组设计成哈希表的思路
        int len = nums.length;
        int maxPositive = len + 1;
        /**
         * 我们将数组中所有小于等于 0 的数修改为 N+1；
         */
        for (int i = 0; i < len; i++) {
            if (nums[i]<=0){
                nums[i] = maxPositive;
            }
        }
        /**
         * 我们遍历数组中的每一个数 x，它可能已经被打了标记，因此原本对应的数为 ∣x∣，其中 ∣∣ 为绝对值符号。如果 ∣x∣∈[1,N]，那么我们给数组中的第 ∣x∣−1 个位置的数添加一个负号。注意如果它已经有负号，不需要重复添加；
         */
        for (int i = 0; i < len; i++) {
            int ele = Math.abs(nums[i]);
            if (ele <= len) {
                //原值取反
                nums[ele - 1] = -Math.abs(nums[ele-1]);
            }
        }
        /**
         * 在遍历完成之后，如果数组中的每一个数都是负数，那么答案是 N+1，否则答案是第一个正数的位置加 1。
         */
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return maxPositive;
    }
}
