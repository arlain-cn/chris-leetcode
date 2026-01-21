package org.chris.leetcode.dp;

public class Fib_509 {

    /**
     * 509. 斐波那契数
     *
     * @param args
     */
    public static void main(String[] args) {
        Fib_509 fib509 = new Fib_509();
        System.out.println(fib509.fib_fac(10));
        System.out.println(fib509.fib(10));
    }

    /**
     * 空间优化的DP
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int pre_2 = 0, pre_1 = 1;
        int ans = 0;
        for (int i = 2; i < n + 1; i++) {
            ans = pre_1 + pre_2;
            pre_2 = pre_1;
            pre_1 = ans;
        }
        return ans;
    }

    /**
     * DP
     * 1. 阶段划分
     * 我们可以按照整数顺序进行阶段划分，将其划分为整数0∼n。
     * 2. 定义状态
     * 定义状态 dp[i] 为：第 i 个斐波那契数。
     * 3. 状态转移方程
     * 根据题目中所给的斐波那契数列的定义 f(n)=f(n−1)+f(n−2)，则直接得出状态转移方程为 dp[i]=dp[i−1]+dp[i−2]。
     * 4. 初始条件
     * 根据题目中所给的初始条件 f(0)=0,f(1)=1 确定动态规划的初始条件，即 dp[0]=0,dp[1]=1。
     * 5. 最终结果
     * 根据状态定义，最终结果为 dp[n]，即第 n 个斐波那契数为 dp[n]。
     *
     * @param n
     * @return
     */
    public int fib_dp(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int fib_fac(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib_fac(n - 1) + fib_fac(n - 2);
    }
}
