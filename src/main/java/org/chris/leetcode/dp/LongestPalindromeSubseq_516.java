package org.chris.leetcode.dp;

public class LongestPalindromeSubseq_516 {

    /**
     * 516. 最长回文子序列
     *
     * @param args
     */
    public static void main(String[] args) {
        LongestPalindromeSubseq_516 longestPalindromeSubseq516 = new LongestPalindromeSubseq_516();
        System.out.println(longestPalindromeSubseq516.longestPalindromeSubseq("bbbab"));//4
        System.out.println(longestPalindromeSubseq516.longestPalindromeSubseq("cbbd"));//2
    }

    /**
     * 最长回文子序列 - 动态规划
     * 1. 阶段划分
     * 按照区间长度进行阶段划分。
     * 2. 定义状态
     * 定义状态 dp[i][j] 表示为：字符串 s 在区间 [i,j] 范围内的最长回文子序列长度。
     * 3. 状态转移方程
     * 我们对区间 [i,j] 边界位置上的字符 s[i] 与 s[j] 进行分类讨论：
     * <p>
     * - 如果 s[i]=s[j]，则 dp[i][j] 为区间 [i+1,j−1] 范围内最长回文子序列长度 + 2，即 dp[i][j]=dp[i+1][j−1]+2。
     * - 如果 s[i]!=s[j]，则 dp[i][j] 取决于以下两种情况，取其最大的一种：
     * ----- 1. 加入 s[i] 所能组成的最长回文子序列长度，即：dp[i][j]=dp[i][j−1]。
     * ----- 2. 加入 s[j] 所能组成的最长回文子序列长度，即：dp[i][j]=dp[i−1][j]。
     * <p>
     * - 则状态转移方程为：
     * dp[i][j]=
     * ----- max{dp[i+1][j−1]+2},        s[i]=s[j]
     * ----- max{dp[i][j−1],dp[i−1][j]},         s[i]!=s[j]
     * <p>
     * 4. 初始条件
     * 单个字符的最长回文序列是 1，即 dp[i][i]=1。
     * 5. 最终结果
     * 由于 dp[i][j] 依赖于 dp[i+1][j−1]、dp[i+1][j]、dp[i][j−1]，所以我们应该按照从下到上、从左到右的顺序进行遍历。
     * <p>
     * 根据我们之前定义的状态，dp[i][j] 表示为：字符串 s 在区间 [i,j] 范围内的最长回文子序列长度。所以最终结果为 dp[0][size−1
     *
     * @param s String，输入字符串
     * @return int，最长回文子序列的长度
     */
    public int longestPalindromeSubseq(String s) {
        int size = s.length();
        // dp[i][j] 表示 s[i...j] 范围内的最长回文子序列长度
        int[][] dp = new int[size][size];
        // 初始化：单个字符本身是回文，长度为 1
        for (int i = 0; i < size; i++) {
            dp[i][i] = 1;
        }
        // 逆序遍历 i，保证计算 dp[i][j] 时，dp[i+1][...] 已经计算过
        for (int i = size - 1; i >= 0; i--) {
            // 正序遍历 j，保证计算 dp[i][j] 时，dp[...][j-1] 已经计算过
            for (int j = i + 1; j < size; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // 如果首尾字符相同，则回文长度 + 2
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    // 如果首尾字符不同，则取去掉首字符或去掉尾字符后的最大值
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][size - 1];
    }

}
