package org.chris.leetcode.dp;

public class LongestPalindrome_5 {
    /**
     * 5. 最长回文子串
     *
     * @param args
     */
    public static void main(String[] args) {
        LongestPalindrome_5 longestPalindrome5 = new LongestPalindrome_5();
        System.out.println(longestPalindrome5.longestPalindrome("babad"));
        System.out.println(longestPalindrome5.longestPalindrome("cbbd"));
    }

    /**
     * 思路 1：动态规划
     * 1. 阶段划分
     * 按照区间长度进行阶段划分。
     * <p>
     * 2. 定义状态
     * 定义状态 `dp[i][j]` 表示为：字符串 s 在区间 `[i, j]` 范围内是否是一个回文串。
     * <p>
     * 3. 状态转移方程
     * 当子串只有 1 位或 2 位的时候，如果 `s[i] == s[j]`，该子串为回文子串，即：`dp[i][j] = (s[i] == s[j])`。
     * 如果子串大于 2 位，则如果 `s[i+1...j-1]` 是回文串，且 `s[i] == s[j]`，则 s[i...j] 也是回文串，即：`dp[i][j] = (s[i] == s[j]) and dp[i + 1][j - 1]`。
     * 4. 初始条件
     * 初始状态下，`默认字符串 s 的所有子串都不是回文串`。
     * 5. 最终结果
     * 根据我们之前定义的状态，dp[i][j] 表示为：字符串 s 在区间 [i, j] 范围内是否是一个回文串。当判断完 s[i:j] 是否为回文串时，同时判断并更新最长回文子串的起始位置 `max_start` 和最大长度 `max_len`。则最终结果为 `s[max_start,max_start+max_len]`。
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int size = s.length();
        // dp[i][j] 表示 s[i...j] 是否为回文串
        boolean[][] dp = new boolean[size][size];
        int max_start = Integer.MIN_VALUE;
        int max_len = 0;
        for (int i = size - 1; i >= 0; i--) {
            for (int j = i; j < size; j++) {
                if (j - i < 2) {
                    if (s.charAt(i) == s.charAt(j)) {
                        // 如果 s[i] == s[j]，则可能是回文
                        dp[i][j] = true;
                    }
                } else {
                    if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                    }
                    // s[i] != s[j] 时 dp[i][j] 默认为 false，无需显式赋值
                }
                // 如果是回文且长度更长，更新最大值
                if (dp[i][j] && j - i + 1 > max_len) {
                    max_start = i;
                    max_len = j - i + 1;
                }
            }
        }
        return s.substring(max_start, max_start + max_len);
    }
}
