package org.chris.leetcode;

public class CombinationSum4_377 {
    /**
     * 377. 组合总和 Ⅳ
     * @param args
     */
    public static void main(String[] args) {
        CombinationSum4_377 combinationSum4377 = new CombinationSum4_377();
        System.out.println(combinationSum4377.combinationSum4(new int[]{1, 2, 3}, 4));
    }

    /**
     *
     * 方法一：动态规划
     * 「完全背包问题求方案数」的变形。本题与「完全背包问题求方案数」不同点在于：方案中不同的物品顺序代表不同方案。
     *
     * 比如「完全背包问题求方案数」中，凑成总和为 4 的方案 [1,3] 算 1 种方案，但是在本题中 [1,3]、[3,1] 算 2 种方案数。
     *
     * 我们需要在考虑某一总和 w 时，需要将 nums 中所有元素都考虑到。对应到循环关系时，即将总和 w 的遍历放到外侧循环，将 nums 数组元素的遍历放到内侧循环，即：
     *  ```java
     *     for w in range(target + 1):
     *         for i in range(1, len(nums) + 1):
     *         xxxx
     * ```
     *
     *
     * 组合总和 Ⅳ - 动态规划解法（求排列数）
     * 1. 阶段划分
     * 按照总和进行阶段划分。
     * <p>
     * 2. 定义状态
     * 定义状态 `dp[w]` 表示为：凑成总和 w 的组合数。
     * <p>
     * 3. 状态转移方程
     * 凑成总和为 w 的组合数 = 「不使用当前 nums[i−1]，只使用之前整数凑成和为 w 的组合数」+「使用当前 nums[i−1] 凑成和为 w−nums[i−1] 的方案数」。即状态转移方程为：`dp[w]=dp[w]+dp[w−nums[i−1]]`。
     * <p>
     * 4. 初始条件
     * 凑成总和 0 的组合数为 1，即 `dp[0]=1`。
     * 5. 最终结果
     * 根据我们之前定义的状态，dp[w] 表示为：凑成总和 w 的组合数。 所以最终结果为 `dp[target]`。
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        // dp[i] 表示凑成目标整数 i 的排列数
        int[] dp = new int[target + 1];

        // 初始化：凑成目标整数 0 的排列数为 1（什么都不选）
        dp[0] = 1;

        // 注意：这里是求排列数，所以必须先遍历背包容量（target），再遍历物品（nums）
        // 这样可以确保在计算 dp[i] 时，不同的物品顺序被视为不同的方案
        // 例如：对于 target=3, nums=[1, 2]
        // 当 i=1 时，选 1 -> dp[1] += dp[0]
        // 当 i=2 时，选 1 -> dp[2] += dp[1] (先1后1), 选 2 -> dp[2] += dp[0] (先2)
        // 当 i=3 时，选 1 -> dp[3] += dp[2] (即 {1,1,1}, {2,1}), 选 2 -> dp[3] += dp[1] (即 {1,2})
        // 从而 {2,1} 和 {1,2} 都会被统计
        for (int i = 0; i <= target; i++) {
            // 遍历每一个可选的数字
            for (int num : nums) {
                // 如果当前背包容量足够放下该数字
                if (i >= num) {
                    // 状态转移：凑成 i 的排列数 += 凑成 i-num 的排列数
                    dp[i] += dp[i - num];
                }
            }
        }

        return dp[target];
    }
}
