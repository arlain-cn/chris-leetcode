package org.chris.leetcode.dp;

public class UniquePaths_62 {

    /**
     * 62. 不同路径
     *
     * @param args
     */
    public static void main(String[] args) {
        UniquePaths_62 uniquePaths62 = new UniquePaths_62();
        System.out.println(uniquePaths62.uniquePaths(3, 7));
        System.out.println(uniquePaths62.uniquePaths(3, 2));
        System.out.println(uniquePaths62.uniquePaths(7, 3));
        System.out.println(uniquePaths62.uniquePaths(3, 3));
    }

    /**
     * 思路 1：动态规划
     * 1. 阶段划分
     * 按照路径的结尾位置（行位置、列位置组成的二维坐标）进行阶段划分。
     * 2. 定义状态
     * 定义状态 `dp[i][j]` 为：从左上角到达位置 (i,j) 的路径数量。
     * 3. 状态转移方程
     * 因为我们每次只能向右、或者向下移动一步，因此想要走到 (i,j)，只能从 (i−1,j) 向下走一步走过来；或者从 (i,j−1) 向右走一步走过来。所以可以写出状态转移方程为：`dp[i][j]=dp[i−1][j]+dp[i][j−1]，此时 i>0,j>0。`
     * 4. 初始条件
     * 从左上角走到 (0,0) 只有一种方法，即 `dp[0][0]=1`。
     * 第一行元素只有一条路径（即只能通过前一个元素向右走得到），所以 `dp[0][j]=1`。
     * 同理，第一列元素只有一条路径（即只能通过前一个元素向下走得到），所以 `dp[i][0]=1`。
     * 5. 最终结果
     * 根据状态定义，最终结果为 dp[m−1][n−1]，即从左上角到达右下角 (m−1,n−1) 位置的路径数量为 dp[m−1][n−1]。
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths_dp(int m, int n) {
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }

    /**
     * 方法二：组合数学
     * (m+n−2)!/(m−1)!(n−1)!
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int ans = 1;
        for (int i = 1; i <= m + n - 2; i++) {
            ans *= i;
        }
        for (int i = 1; i <= m - 1; i++) {
            ans /= i;
        }
        for (int i = 1; i <= n - 1; i++) {
            ans /= i;
        }
        return ans;
    }
}
