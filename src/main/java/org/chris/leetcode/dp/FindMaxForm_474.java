package org.chris.leetcode.dp;

public class FindMaxForm_474 {

    /**
     * 474. 一和零
     *
     * @param args
     */
    public static void main(String[] args) {
        FindMaxForm_474 findMaxForm474 = new FindMaxForm_474();
        System.out.println(findMaxForm474.findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
    }

    /**
     * 思路 1：动态规划
     * 这道题可以转换为「二维 0-1 背包问题(二维费用背包问题)」来做。
     * 把 0 的个数和 1 的个数视作一个二维背包的容量。每一个字符串都当做是一件物品，其成本为字符串中 1 的数量和 0 的数量，每个字符串的价值为 1。
     * 1. 阶段划分
     * 按照物品的序号、当前背包的载重上限进行阶段划分。
     * 2. 定义状态
     * 定义状态
     * dp[i][j] 表示为：最多有 i 个 0 和 j 个 1 的字符串 strs 的最大子集的大小。
     * 3. 状态转移方程
     * 填满最多由 i 个 0 和 j 个 1 构成的二维背包的最多物品数为下面两种情况中的最大值：
     * 使用之前字符串填满容量为 i−zero_num、j−one_num 的背包的物品数 + 当前字符串价值
     * 选择之前字符串填满容量为 i、j 的物品数。
     * 则状态转移方程为：
     * dp[i][j]=max(dp[i][j],dp[i−zero_num][j−one_num]+1)。
     * 4. 初始条件
     * 无论有多少个 0，多少个 1，只要不选 0，也不选 1，则最大子集的大小为 0。
     * 5. 最终结果
     * 根据我们之前定义的状态，dp[i][j] 表示为：最多有 i 个 0 和 j 个 1 的字符串strs 的最大子集的大小。所以最终结果为 dp[m][n]。
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int size = strs.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= size; i++) {
            int[] zero_one = getZerosOnes(strs[i - 1]);
            for (int w = m; w >= 0; w--) {
                for (int v = n; v >= 0; v--) {
                    if (w >= zero_one[0] && v >= zero_one[1]) {
                        dp[w][v] = Math.max(dp[w][v], dp[w - zero_one[0]][v - zero_one[1]] + 1);
                    }
                }
            }
        }

        return dp[m][n];
    }


    public int[] getZerosOnes(String str) {
        int[] zero_one = new int[2];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                zero_one[0]++;
            }
        }
        zero_one[1] = str.length() - zero_one[0];
        return zero_one;
    }
}
