package org.chris.leetcode;

public class LongestCommonSubsequence_1143 {

    /**
     * 1143. 最长公共子序列
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     * <p>
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * <p>
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     *
     * @param args
     */
    public static void main(String[] args) {
        LongestCommonSubsequence_1143 longestCommonSubsequence1143 = new LongestCommonSubsequence_1143();
        System.out.println(longestCommonSubsequence1143.longestCommonSubsequence("", ""));
    }

    /**
     * 1. 阶段划分:以两个字符串的结尾下标作为阶段。
     * <p>
     * 2. 状态定义:令 dp[i][j] 表示 text1 的前 i 个字符与 text2 的前 j 个字符的最长公共子序列长度。
     * <p>
     * 3. 状态转移方程:遍历 text1 和 text2 的所有前缀，状态转移分为两种情况：
     * ----- 如果 text1[i−1]==text2[j−1]，说明当前字符相同，则最长公共子序列长度在 dp[i−1][j−1] 基础上加 1，即 dp[i][j]=dp[i−1][j−1]+1。
     * ----- 如果 text1[i−1]!=text2[j−1]，则当前字符不同，dp[i][j] 取 dp[i−1][j] 和 dp[i][j−1] 的较大值，即 dp[i][j]=max(dp[i−1][j],dp[i][j−1])。
     * <p>
     * 4. 初始条件:dp[0][j]=0，即 text1 为空时，与 text2 任意前缀的最长公共子序列长度为 0。dp[i][0]=0，即 text2 为空时，与 text1 任意前缀的最长公共子序列长度为 0。
     * <p>
     * 5. 结果表示:最终答案为 dp[size1][size2]，即 text1 与 text2 的最长公共子序列长度，其中
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int size1 = text1.length();
        int size2 = text2.length();

        int[][] dp = new int[size1 + 1][size2 + 1];

        for (int i = 1; i <= size1; i++) {
            for (int j = 1; j <= size2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[size1][size2];
    }
}
