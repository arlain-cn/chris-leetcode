package org.chris.leetcode.dp;

import java.util.Arrays;
import java.util.List;

public class WordBreak_139 {

    /**
     * 139. 单词拆分
     * <p>
     * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
     * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     *
     * @param args
     */
    public static void main(String[] args) {
        WordBreak_139 wordBreak139 = new WordBreak_139();
        System.out.println(wordBreak139.wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(wordBreak139.wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(wordBreak139.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }

    /**
     * 单词拆分 - 动态规划解法
     * 思路 1：动态规划
     * 1. 阶段划分
     * 按照单词结尾位置进行阶段划分。
     * <p>
     * 2. 定义状态
     * > s 能否拆分为单词表的单词，可以分解为：
     * - 前 i 个字符构成的字符串，能否分解为单词。
     * - 剩余字符串，能否分解为单词。
     * 定义状态 dp[i] 表示：长度为 i 的字符串 s[0:i] 能否拆分成单词，如果为 True 则表示可以拆分，如果为 False 则表示不能拆分。
     * <p>
     * 3. 状态转移方程
     * 如果 s[0:j] 可以拆分为单词（即 dp[j]==True），并且字符串 s[j:i] 出现在字典中，则 dp[i] = True。
     * 如果 s[0:j] 不可以拆分为单词（即 dp[j]==False），或者字符串 s[j:i] 没有出现在字典中，则 dp[i] = False。
     * 4. 初始条件
     * 长度为 0 的字符串 s[0:i] 可以拆分为单词，即 dp[0]=True。
     * 5. 最终结果
     * 根据我们之前定义的状态，dp[i] 表示：长度为 i 的字符串 s[0:i] 能否拆分成单词。则最终结果为 dp[size]，size 为字符串长度。
     * <p>
     * 思路 1：复杂度分析
     * 时间复杂度：O(n^2)，其中 n 为字符串 s 的长度。
     * 空间复杂度：O(n)。
     *
     * @param s        String，非空字符串
     * @param wordDict List<String>，非空单词列表
     * @return boolean，是否可以被空格拆分为一个或多个在字典中出现的单词
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int size = s.length();
        // dp[i] 表示字符串 s 的前 i 个字符 s[0...i-1] 是否可以被空格拆分为字典中的单词
        boolean[] dp = new boolean[size + 1];

        // 初始化：空字符串可以被拆分（视为逻辑上的真值，作为递推起点）
        dp[0] = true;

        // 遍历背包容量：从 1 到 size，表示子串的长度
        for (int i = 1; i <= size; i++) {
            // 遍历物品：从 0 到 i，切分点 j
            // 将 s[0...i-1] 分割为 s[0...j-1] 和 s[j...i-1]
            // 如果 dp[j] 为 true 且 s[j...i-1] 在字典中，则 dp[i] 为 true
            for (int j = 0; j < i; j++) {
                // s.substring(j, i) 对应 python 的 s[j:i]
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    // 只要找到一种合法的切分方式，就可以确定 dp[i] 为 true，跳出内层循环
                    break;
                }
            }
        }

        return dp[size];
    }
}
