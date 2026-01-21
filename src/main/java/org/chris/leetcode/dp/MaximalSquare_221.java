package org.chris.leetcode.dp;

public class MaximalSquare_221 {

    /**
     * 221. 最大正方形
     *
     * @param args
     */
    public static void main(String[] args) {
        MaximalSquare_221 maximalSquare221 = new MaximalSquare_221();
        char[][] matrix = new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
//        char[][] matrix = new char[][]{{'1', '1', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '1', '1', '1', '1'}};
//        char[][] matrix = new char[][]{{'0', '1'}, {'1', '0'}};
        System.out.println(maximalSquare221.maximalSquare(matrix));
    }

    /**
     * 1. 阶段划分
     * 以正方形的右下角坐标 (i,j) 作为阶段。
     * 2. 定义状态
     * 令 dp[i][j] 表示以 (i,j) 为右下角、且全部为 1 的最大正方形的边长。
     * 3. 状态转移方程
     * >>>>> 仅当 matrix[i][j]==1 时，(i,j) 位置才能作为正方形的右下角：
     * ----- 如果 matrix[i][j]==0，则 dp[i][j]=0；
     * ----- matrix[i][j]==1，则 dp[i][j]=min(dp[i−1][j−1],dp[i−1][j],dp[i][j−1])+1，即取其上方、左方、左上方的最小值加 1。
     * 4. 初始条件
     * 初始时，令所有 dp[i][j]=0，即默认以 (i,j) 为右下角的最大正方形边长为 0。
     * <p>
     * 5. 最终结果
     * 遍历所有 dp[i][j]，取最大值即为只包含 1 的最大正方形的边长，面积为其平方。
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int[][] dp = new int[rowLen][colLen];

        int maxSide = 0;
        //从0开始遍历的时候，不需要初始化dp[i][0]、dp[0][i]了，更快一点
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    }
                }
                maxSide = Math.max(maxSide, dp[i][j]);
            }
        }
        return maxSide * maxSide;
    }

    public int maximalSquare_slow(char[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;

        int[][] dp = new int[rowLen][colLen];

        dp[0][0] = matrix[0][0] - '0';
        int ans = dp[0][0];
        for (int i = 1; i < rowLen; i++) {
            dp[i][0] = matrix[i][0] - '0';
            ans = Math.max(ans, dp[i][0] * dp[i][0]);
        }
        for (int i = 1; i < colLen; i++) {
            dp[0][i] = matrix[0][i] - '0';
            ans = Math.max(ans, dp[0][i] * dp[0][i]);
        }
        for (int i = 1; i < rowLen; i++) {
            for (int j = 1; j < colLen; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                } else {
                    dp[i][j] = 0;
                }
                ans = Math.max(ans, dp[i][j] * dp[i][j]);
            }
        }
        return ans;
    }
}
