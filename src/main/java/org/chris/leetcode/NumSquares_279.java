package org.chris.leetcode;

public class NumSquares_279 {

    /**
     * 279. 完全平方数
     *
     * @param args
     */
    public static void main(String[] args) {
        NumSquares_279 numSquares279 = new NumSquares_279();
        System.out.println(numSquares279.numSquares(12));
        System.out.println(numSquares279.numSquares0(12));
        System.out.println(numSquares279.numSquares(13));
        System.out.println(numSquares279.numSquares0(13));
    }


    /**
     * 1. 阶段划分
     * 对于从 1 到 n 的每个数字，计算其所需的最少完全平方数
     * 2. 定义状态
     * dp[i] 表示组成数字 i 所需的最少完全平方数个数
     * 3. 状态转移方程
     * 状态转移方程：dp[i] = min(dp[i - j*j]) + 1 (其中 j*j <= i)
     * 4. 初始条件
     * 初始化 dp 数组，其中 dp[0] = 0 (0 可以用 0 个完全平方数组成)
     * 5. 最终结果
     * 返回组成 n 所需的最少完全平方数个数
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        // dp[i] 表示组成数字 i 所需的最少完全平方数个数
        // 初始化 dp 数组，其中 dp[0] = 0 (0 可以用 0 个完全平方数组成)
        int[] dp = new int[n + 1];

        // 状态转移方程：dp[i] = min(dp[i - j*j]) + 1 (其中 j*j <= i)
        // 对于从 1 到 n 的每个数字，计算其所需的最少完全平方数
        for (int i = 1; i <= n; i++) {
            // 初始化为最大值，以便找到最小值
            int min = Integer.MAX_VALUE;
            // 尝试所有小于等于 i 的完全平方数
            // j*j 代表一个完全平方数
            for (int j = 1; j * j <= i; j++) {
                // 通过检查所有可能性来找到最小值
                // dp[i - j*j] 给出剩余值所需的最少完全平方数个数
                // 我们取所有可能的 j 值中能得到最小 dp[i-j*j] 的那个，然后加 1
                min = Math.min(min, dp[i - j * j] + 1);
            }
            // 根据状态转移方程更新 dp[i]
            // 状态转移方程: dp[i] = min(dp[i - j*j]) + 1
            // 其中 j*j 是一个小于等于 i 的完全平方数
            dp[i] = min;
        }
        // 返回组成 n 所需的最少完全平方数个数
        return dp[n];
    }

    /**
     * 方法二：数学
     * 一个数学定理可以帮助解决本题：「四平方和定理」。
     * 定理的细化
     * 四平方和定理：对任意正整数 n，存在非负整数 a,b,c,d使得
     * n=a^2+b^2+c^2+d^2.
     * 勒让德三平方和定理：当且仅当 n不能写成  n=4^k×(8m+7) 的形式时，n可以表示为最多三个整数的平方和（其中 k,m为非负整数）。换言之，若 n=4^k(8m+7)，则 n必须用四个平方数表示。
     * 应用：最少完全平方数个数
     * 利用上述定理，我们可以在常数时间内判断任意 n所需的最少平方数个数（答案只可能是 1、2、3 或 4）。具体步骤如下：
     * 1、判断是否为完全平方数：若 n是某个整数的平方，则答案为 1。
     * 2、判断是否可表示为两个平方数之和：枚举 a从 1 到 ，检查 n−a^2是否为完全平方数。若是，则答案为 2。
     * 3、判断是否满足三平方和条件：若 n不能写成 n=4^k(8m+7)的形式，则答案为 3；否则答案为 4。
     * 4、其中，检查 n=4^k(8m+7)的方法：不断除以 4 直到 n不能被 4 整除（即移除因子 4^k），然后判断余下的数除以 8 是否余 7。
     *
     * @param n
     * @return
     */
    public int numSquares0(int n) {
        if (isPerfectSquare(n)) {
            return 1;
        }
        if (checkAnswer4(n)) {
            return 4;
        }
        for (int i = 1; i * i <= n; i++) {
            int j = n - i * i;
            if (isPerfectSquare(j)) {
                return 2;
            }
        }
        return 3;
    }

    // 判断是否为完全平方数
    public boolean isPerfectSquare(int n) {
        int x = (int) Math.sqrt(n);
        return x * x == n;
    }

    // 判断是否能表示为 4^k*(8m+7)
    public boolean checkAnswer4(int n) {
        while (n % 4 == 0) {
            n /= 4;
        }
        return n % 8 == 7;
    }
}
