package org.chris.leetcode.dp;

public class NumRollsToTarget_1155 {

    /**
     * 1155. 掷骰子等于目标和的方法数
     *
     * @param args
     */
    public static void main(String[] args) {
        NumRollsToTarget_1155 numRollsToTarget1155 = new NumRollsToTarget_1155();
//        System.out.println(numRollsToTarget1155.numRollsToTarget(1, 6, 3));
//        System.out.println(numRollsToTarget1155.numRollsToTarget(2, 6, 7));
        System.out.println(numRollsToTarget1155.numRollsToTarget(30, 30, 500));
    }

    /**
     * 思路 1：动态规划
     * 我们可以将这道题转换为「分组背包问题」中求方案总数的问题。将每个骰子看做是一组物品，骰子每一个面上的数值当做是每组物品中的一个物品。这样问题就转换为：用 n 个骰子（n 组物品）进行投掷，投掷出总和（总价值）为 target 的方案数。
     * <p>
     * 1. 阶段划分
     * 按照总价值target 进行阶段划分。
     * <p>
     * 2. 定义状态
     * 定义状态 `dp[w]` 表示为：用 n 个骰子（n 组物品）进行投掷，投掷出总和（总价值）为 w 的方案数。
     * <p>
     * 3. 状态转移方程
     * 用 n 个骰子（n 组物品）进行投掷，投掷出总和（总价值）为 w 的方案数，等于用 n 个骰子（n 组物品）进行投掷，投掷出总和（总价值）为 `w−d` 的方案数累积值，其中 d 为当前骰子掷出的价值，即：`dp[w]=dp[w]+dp[w−d]`。
     * <p>
     * 4. 初始条件
     * 用 n 个骰子（n 组物品）进行投掷，投掷出总和（总价值）为 0 的方案数为 1。
     * 5. 最终结果
     * 根据我们之前定义的状态，dp[w] 表示为：用 n 个骰子（n 组物品）进行投掷，投掷出总和（总价值）为 w 的方案数。则最终结果为 `dp[target]`。
     *
     * @param n
     * @param k
     * @param target
     * @return
     */
    public int numRollsToTarget(int n, int k, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        int MOD = 1000000007;

        // 枚举前 i 组物品（即前 i 个骰子）
        for (int i = 1; i <= n; i++) {
            // 逆序枚举背包装载重量（即当前目标和）
            for (int w = target; w >= 0; w--) {
                //但是在 掷骰子问题 （或分组背包的某些变体）中，逻辑完全不同：
                //- 我们必须投第 i 个骰子。
                //- 第 i 轮的状态 dp[w] （ i 个骰子和为 w ）完全是由上一轮 i-1 个骰子的状态（ dp[w-1], dp[w-2]... ）加上当前骰子的点数推导出来的。
                //- 旧的 dp[w] 代表的是 i-1 个骰子和为 w 的方案数 。这对于计算 i 个骰子和为 w 是没有直接累加意义的（你不能说“我不投第 i 个骰子，直接沿用 i-1 个骰子的结果”）。
                dp[w] = 0; // 清空当前状态，准备计算新一轮（i个骰子）的值
                // 枚举第 i - 1 组物品能取个数（即当前骰子投出的点数）
                for (int d = 1; d <= k; d++) {
                    if (w >= d) {
                        dp[w] = (dp[w] + dp[w - d]) % MOD;
                    }
                }
            }
        }
        return dp[target];
    }
}
