package org.chris.leetcode;

public class CoinChange_518 {
    /**
     * 518. 零钱兑换 II
     * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     * 假设每一种面额的硬币有无限个。
     *
     * @param args
     */
    public static void main(String[] args) {
        CoinChange_518 coinChange518 = new CoinChange_518();
        System.out.println(coinChange518.change(5, new int[]{1, 2, 5}));
        System.out.println(coinChange518.change(3, new int[]{2}));
        System.out.println(coinChange518.combinationSum4(new int[]{1, 2, 3}, 4));
    }

    /**
     * 方法一：动态规划
     * 这道题可以转换为：有 n 种不同的硬币，coins[i] 表示第 i 种硬币的面额，每种硬币可以无限次使用。请问凑成总金额为 amount 的背包，一共有多少种方案？
     * <p>
     * 这就变成了完全背包问题。「322. 零钱兑换」中计算的是凑成总金额的最少硬币个数，而这道题计算的是凑成总金额的方案数。
     * 1. 阶段划分
     * 按照当前背包的载重上限进行阶段划分。
     * <p>
     * 2. 定义状态
     * 定义状态 `dp[i]` 表示为：凑成总金额为 `i` 的方案总数。
     * <p>
     * 3. 状态转移方程
     * 凑成总金额为 `i` 的方案数 = 「不使用当前 coin，只使用之前硬币凑成金额 i 的方案数」+「使用当前 coin 凑成金额 i−coin 的方案数」。即状态转移方程为：`dp[i]=dp[i]+dp[i−coin]`。
     * <p>
     * 4. 初始条件
     * 凑成总金额为 `0` 的方案数为 1，即 `dp[0]=1`。
     * 5. 最终结果
     * 根据我们之前定义的状态，dp[i] 表示为：凑成总金额为 i 的方案总数。 所以最终结果为`dp[amount]`。
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        // dp[i] 表示凑成金额 i 的组合数
        int[] dp = new int[amount + 1];
        // 初始化：凑成金额 0 的组合数为 1（什么都不选）
        dp[0] = 1;
        //先遍历硬币，再看不同的容量
        for (int coin : coins) {
            // 遍历每一种可能的金额（背包容量）
            // 从 coin 开始遍历，因为小于 coin 的金额无法使用当前硬币
            // 正序遍历，因为这是完全背包问题（硬币可重复使用）
            for (int i = coin; i <= amount; i++) {
                // 状态转移：凑成金额 i 的组合数 += 凑成金额 i-coin 的组合数
                // 意味着在凑成 i-coin 的基础上，再加一枚当前硬币 coin
                dp[i] = dp[i] + dp[i - coin];
            }
        }
        // 返回凑成目标金额 amount 的组合数
        return dp[amount];
    }


    /**
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