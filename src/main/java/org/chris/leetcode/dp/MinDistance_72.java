package org.chris.leetcode.dp;

public class MinDistance_72 {
    /**
     * 72. 编辑距离
     *
     * @param args
     */
    public static void main(String[] args) {
        MinDistance_72 minDistance72 = new MinDistance_72();
        System.out.println(minDistance72.minDistance("horse", "ros"));
    }

    /**
     * 方法一：动态规划
     * 1. 阶段划分
     * 以两个字符串的结尾位置作为阶段进行划分。
     * <p>
     * 2. 定义状态
     * 设 dp[i][j] 表示将 word1 的前 i 个字符（记为 str1）转换为 word2 的前 j 个字符（记为 str2）所需的最少操作次数。
     * <p>
     * 3. 状态转移方程
     * 如果当前字符相同（word1[i−1]==word2[j−1]），则无需操作：dp[i][j]=dp[i−1][j−1]。
     * 如果当前字符不同（word1[i−1]！=word2[j−1]），则有三种操作可选，取最小值：
     * 替换：dp[i−1][j−1]+1
     * 插入：dp[i][j−1]+1
     * 删除：dp[i−1][j]+1
     * 因此，状态转移方程为：
     * 4. 初始条件
     * dp[0][j]=j：将空串变为 word2 的前 j 个字符，需要插入 j 次。
     * dp[i][0]=i：将word1 的前 i 个字符变为空串，需要删除i 次。
     * 5. 最终结果
     * 最终答案为 dp[size1][size2]
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int size1 = word1.length();
        int size2 = word2.length();

        int[][] dp = new int[size1 + 1][size2 + 1];
        for (int i = 0; i <= size1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= size2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= size1; i++) {
            for (int j = 1; j <= size2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // dp[i][j - 1]+1 代表插入：dp[i][j - 1] 已经对齐后，word2再加一个字符，则word1末尾插入一个字符即可
                    // dp[i - 1][j] 代表新增：dp[i - 1][j]已经对齐后，word1又有一个新增自负，则word1末尾删除一个字符即可（当然也可以word2插入，）
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        return dp[size1][size2];
    }
}
