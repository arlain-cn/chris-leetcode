package org.chris.leetcode;

import java.util.*;

public class CoinChange_322 {
    /**
     * 322. 零钱兑换
     *
     * @param args
     */
    public static void main(String[] args) {
        CoinChange_322 coinChange322 = new CoinChange_322();
        System.out.println(coinChange322.coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(coinChange322.coinChange(new int[]{2}, 3));
        System.out.println(coinChange322.coinChange(new int[]{1}, 0));
    }

    /**
     * 方法二：动态规划
     * <p>
     * 1. 阶段划分
     * 按照当前背包的载重上限进行阶段划分。
     * <p>
     * 2. 定义状态
     * 定义状态 dp[c] 表示为：凑成总金额为c 的最少硬币数量。
     * <p>
     * 3. 状态转移方程
     * dp[c]=min(dp[c],dp[c−coin]+1)
     * <p>
     * 对于每种硬币 coin，当 c≥coin 时，取下面两种情况中的较小值：
     * <p>
     * 不使用当前硬币，只使用之前硬币凑成金额 c 的最少硬币数量，即 dp[c]。
     * 凑成金额 c−coin 的最少硬币数量，再加上当前硬币的数量 1，即 dp[c−coin]+1。
     * 4. 初始条件
     * 凑成总金额为 0 的最少硬币数量为 0，即 dp[0]=0。
     * 默认情况下，在不使用硬币时，都不能恰好凑成总金额为 c，此时将状态值设置为一个极大值（比如 +∞），表示无法凑成。
     * 5. 最终结果
     * 根据我们之前定义的状态，dp[c] 表示为：凑成总金额为 c 的最少硬币数量。则最终结果为 dp[amount]。
     * <p>
     * 如果 dp[amount]!=+∞，则说明： dp[amount] 为凑成金额 amount 的最少硬币数量，则返回 dp[amount]。
     * 如果 dp[amount]=+∞，则说明：无法凑成金额 amount，则返回 −1。
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange_dp(int[] coins, int amount) {
        // 设置一个不可能达到的大值作为无法凑成的标记
        int max = amount + 1;
        // 创建dp数组，dp[i]表示凑成金额i所需的最少硬币数
        int[] dp = new int[max];
        // 初始化dp数组，所有位置填充为max（表示暂时无法凑成）
        Arrays.fill(dp, max);
        // 基础情况：凑成金额0需要0枚硬币
        dp[0] = 0;
        // 遍历所有可能的金额（从1到amount）
        for (int c = 1; c <= amount; c++) {
            // 尝试每种面额的硬币
            for (int i = 0; i < coins.length; i++) {
                // 只有当当前金额大于等于硬币面额时才能使用这枚硬币
                if (c >= coins[i]) {
                    // 状态转移方程：dp[c] = min(dp[c], dp[c - coins[i]] + 1)
                    // 要么不使用当前硬币（保持dp[c]），要么使用当前硬币（dp[c - coins[i]] + 1）
                    dp[c] = Math.min(dp[c], dp[c - coins[i]] + 1);
                }
            }
        }
        // 如果dp[amount]仍然是初始的最大值，说明无法凑成目标金额，返回-1
        // 否则返回凑成目标金额所需的最少硬币数
        return dp[amount] == max ? -1 : dp[amount];
    }

    /**
     * 方法一：广度优先搜索
     * 1. 定义 visited 为标记已访问值的集合变量，queue 为存放值的队列。
     * 2. 将 amount 状态标记为访问，并将其加入队列 queue。
     * 3. 令当前步数加 1，然后将当前队列中的所有值依次出队，并遍历硬币数组：
     * - 如果当前值等于当前硬币值，则说明当前硬币刚好能凑成当前值，则直接返回当前次数。
     * - 如果当前值大于当前硬币值，并且当前值减去当前硬币值的差值没有出现在已访问集合 visited 中，则将差值添加到队列和访问集合中。
     * 4. 重复执行第 3 步，直到队列为空。
     * 5. 如果队列为空，也未能减到 0，则返回 −1。
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        // visited 记录已经访问过的剩余金额，防止重复计算
        Set<Integer> visited = new HashSet<>();
        visited.add(amount);
        // queue 用于 BFS 层次遍历，存储当前剩余需要凑出的金额
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(amount);
        // step 记录当前的层数，即硬币数量
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            // 遍历当前层的所有状态
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                // 尝试每一种硬币
                for (int coin : coins) {
                    // 如果当前剩余金额等于硬币面值，说明正好凑齐，直接返回步数
                    if (cur == coin) {
                        return step;
                    }
                    // 如果当前剩余金额大于硬币面值，且减去硬币后的新金额未被访问过
                    else if (cur > coin && !visited.contains(cur - coin)) {
                        queue.offer(cur - coin);
                        visited.add(cur - coin);
                    }
                }
            }
        }
        // 队列为空仍未凑齐，说明无法兑换
        return -1;
    }
}
