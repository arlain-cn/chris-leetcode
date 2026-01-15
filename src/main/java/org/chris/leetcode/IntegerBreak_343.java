package org.chris.leetcode;

public class IntegerBreak_343 {
    /**
     * 343. 整数拆分
     *
     * @param args
     */
    public static void main(String[] args) {
        IntegerBreak_343 integerBreak343 = new IntegerBreak_343();
        System.out.println(integerBreak343.integerBreak(10));

    }

    /**
     * 方法一：动态规划
     * 1. 阶段划分
     * 以正整数 i 为阶段，从小到大依次考虑每个 i。
     * 2. 定义状态
     * 设 dp[i] 表示将正整数 i 至少拆分成两个正整数之和后，所得的最大乘积。
     * 3. 状态转移方程
     * 对于每个 i≥2，枚举第一个拆分出的正整数 j，其中 1≤j<i。此时有两种选择：
     * 将 i 拆分为 j 和 i−j，如果 i−j 不再继续拆分，则乘积为 j*(i−j)；
     * 将 i 拆分为 j 和 i−j，如果 i−j 继续拆分，则乘积为 j*dp[i−j]；
     * 最终 dp[i] 取上述两种情况的最大值，即：dp[i]= max{max(j*(i−j), j*dp[i−j])},  1≤j<i
     * 4. 初始条件
     * dp[0]=0,dp[1]=0，因为 0 和 1 无法拆分。
     * 5. 最终结果
     * 最终答案为 dp[n]，即将正整数 n 拆分为至少两个正整数之和后所得的最大乘积。
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        if (n < 2) {
            return 0;
        }
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }
}
