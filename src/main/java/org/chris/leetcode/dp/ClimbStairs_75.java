package org.chris.leetcode.dp;

public class ClimbStairs_75 {

    /**
     * 70. 爬楼梯
     *
     * @param args
     */
    public static void main(String[] args) {
        ClimbStairs_75 climbStairs75 = new ClimbStairs_75();
        System.out.println(climbStairs75.climbStairs(3));
    }

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int prePre = 1, pre = 2;
        int ans = 0;
        for (int i = 3; i <= n; i++) {
            ans = prePre + pre;
            prePre = pre;
            pre = ans;
        }
        return ans;
    }

    /**
     * 1. 阶段划分
     * 我们按照台阶的阶层阶段划分，将其划分为 0∼n 阶。
     * <p>
     * 2. 定义状态
     * 定义状态 dp[i] 为：爬到第 i 阶台阶的方案数。
     * <p>
     * 3. 状态转移方程
     * 根据题目大意，每次只能爬 1 或 2 个台阶。则第 i 阶楼梯只能从第 i−1 阶向上爬 1 阶上来，或者从第 i−2 阶向上爬 2 阶上来。所以可以推出状态转移方程为 dp[i]=dp[i−1]+dp[i−2]。
     * <p>
     * 4. 初始条件
     * 第 0 层台阶方案数：可以看做 1 种方法（从 0 阶向上爬 0 阶），即 dp[1]=1。
     * 第 1 层台阶方案数：1 种方法（从 0 阶向上爬 1 阶），即 dp[1]=1。
     * 第 2 层台阶方案数：2 中方法（从 0 阶向上爬 2 阶，或者从 1 阶向上爬 1 阶）。
     * 5. 最终结果
     * 根据状态定义，最终结果为 dp[n]，即爬到第 n 阶台阶（即楼顶）的方案数为 dp[n]。
     *
     * @param n
     * @return
     */
    public int climbStairs_dp(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    public int climbStairs_fac(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs_fac(n - 1) + climbStairs_fac(n - 2);
    }

}
