package org.chris.leetcode.dp;

public class MinPathSum_64 {
    /**
     * 64. 最小路径和
     *
     * @param args
     */
    public static void main(String[] args) {
        MinPathSum_64 minPathSum64 = new MinPathSum_64();
        System.out.println(minPathSum64.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
    }

    /**
     * 1. 阶段划分
     * 以路径终点的位置（即二维坐标 (i,j)）作为阶段。
     * <p>
     * 2. 定义状态
     * 设 dp[i][j] 表示从左上角 (0,0) 出发，到达 (i,j) 的最小路径和。
     * <p>
     * 3. 状态转移方程
     * - 当前位置 (i,j) 只能从左边 (i,j−1) 或上方 (i−1,j) 转移过来。为了保证路径和最小，应选择这两者中较小的路径和，再加上当前格子的值 grid[i][j]。
     * - 则状态转移方程为：dp[i][j]=min(dp[i][j−1],dp[i−1][j])+grid[i][j]。
     * <p>
     * 4. 初始条件
     * - 当 i=0,j=0 时，dp[0][0]=grid[0][0]。
     * - 当 i>0,j=0 时，只能从上方到达，dp[i][0]=dp[i−1][0]+grid[i][0]。
     * - 当 i=0,j>0 时，只能从左侧到达，dp[0][j]=dp[0][j−1]+grid[0][j]。
     * 5. 最终结果
     * 最终答案为 dp[rows−1][cols−1]，即从左上角到右下角的最小路径和。
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int rowLen = grid.length;
        int colLen = grid[0].length;
        int[][] dp = new int[rowLen][colLen];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rowLen; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < colLen; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < rowLen; i++) {
            for (int j = 1; j < colLen; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[rowLen - 1][colLen - 1];
    }
}
