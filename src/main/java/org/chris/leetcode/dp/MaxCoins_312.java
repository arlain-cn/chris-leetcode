package org.chris.leetcode.dp;

public class MaxCoins_312 {

    /**
     * 312. 戳气球
     *
     * @param args
     */
    public static void main(String[] args) {
        MaxCoins_312 maxCoins312 = new MaxCoins_312();
        System.out.println(maxCoins312.maxCoins(new int[]{3, 1, 5, 8}));//167
        System.out.println(maxCoins312.maxCoins(new int[]{1, 5}));//10
    }

    /**
     * 思路 1：动态规划
     * 根据题意，如果 i−1 或 i+1 超出了数组的边界，那么就当它是一个数字为 1 的气球。我们可以预先在 nums 的首尾位置，添加两个数字为 1 的虚拟气球，这样变成了 n+2 个气球，气球对应编号也变为了 0∼n+1。
     * <p>
     * 对应问题也变成了：给定 n+2 个气球，每个气球上有 1 个数字，代表气球上的硬币数量，当我们戳破气球 nums[i] 时，就能得到对应 nums[i−1]×nums[i]×nums[i+1] 枚硬币。现在要戳破 0∼n+1 之间的所有气球（不包括编号 0 和编号 n+1 的气球），请问最多能获得多少枚硬币？
     * <p>
     * 1. 阶段划分
     * 按照区间长度进行阶段划分。
     * <p>
     * 2. 定义状态
     * 定义状态 dp[i][j] 表示为：戳破所有气球 i 与气球 j 之间的气球（不包含气球 i 和 气球 j），所能获取的最多硬币数。
     * <p>
     * 3. 状态转移方程
     * 假设气球 i 与气球 j 之间最后一个被戳破的气球编号为 k。则 dp[i][j] 取决于由 k 作为分割点分割出的两个区间 (i,k) 与 (k,j) 上所能获取的最多硬币数 + 戳破气球 k 所能获得的硬币数，即状态转移方程为：
     * `dp[i][j]=max{dp[i][k]+dp[k][j]+nums[i]×nums[k]×nums[j]},        i<k<j`
     * <p>
     * 4. 初始条件
     * `dp[i][j]` 表示的是开区间，则 i< j−1。而当 i≥j−1 时，所能获得的硬币数为 0，即 `dp[i][j]=0,i≥j−1`。
     * <p>
     * 5. 最终结果
     * 根据我们之前定义的状态，dp[i][j] 表示为：戳破所有气球 i 与气球 j 之间的气球（不包含气球 i 和 气球 j），所能获取的最多硬币数。所以最终结果为 dp[0][n+1]。
     *
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        int size = nums.length;
        // 创建一个新的数组，首尾各添加 1
        int[] arr = new int[size + 2];
        arr[0] = 1;
        arr[size + 1] = 1;
        for (int i = 1; i <= size; i++) {
            arr[i] = nums[i - 1];
        }
        // dp[i][j] 表示戳破 (i, j) 开区间内的所有气球能得到的最大硬币数
        int[][] dp = new int[size + 2][size + 2];
        // l 表示区间长度（包含 i 和 j），至少为 3（因为 i 和 j 之间至少要有一个气球 k）
        for (int l = 3; l <= size + 2; l++) {
            // 枚举左边界 i
            for (int i = 0; i < size + 2; i++) {
                int j = i + l - 1;
                if (j >= size + 2) {
                    break;
                }
                // 枚举最后一个戳破的气球 k，k 在 (i, j) 之间
                for (int k = i + 1; k < j; k++) {
                    // 状态转移：
                    // dp[i][k]: 戳破 (i, k) 之间的气球
                    // dp[k][j]: 戳破 (k, j) 之间的气球
                    // arr[i] * arr[k] * arr[j]: 最后戳破 k 时获得的硬币（此时 i 和 j 还在）
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + arr[i] * arr[k] * arr[j]);
                }
            }
        }
        return dp[0][size + 1];
    }

    /**
     * 戳气球 - 动态规划
     *
     * @param nums int[]，气球上的数字
     * @return int，能获得的最大硬币数量
     */
    public int maxCoins2(int[] nums) {
        int size = nums.length;
        // 创建一个新的数组，首尾各添加 1
        int[] arr = new int[size + 2];
        arr[0] = 1;
        arr[size + 1] = 1;
        for (int i = 0; i < size; i++) {
            arr[i + 1] = nums[i];
        }

        // dp[i][j] 表示戳破 (i, j) 开区间内的所有气球能得到的最大硬币数
        int[][] dp = new int[size + 2][size + 2];

        // l 表示区间长度（包含 i 和 j），至少为 3（因为 i 和 j 之间至少要有一个气球 k）
        for (int l = 3; l <= size + 2; l++) {
            // 枚举左边界 i
            for (int i = 0; i <= size + 2 - l; i++) {
                int j = i + l - 1;
                // 枚举最后一个戳破的气球 k，k 在 (i, j) 之间
                for (int k = i + 1; k < j; k++) {
                    // 状态转移：
                    // dp[i][k]: 戳破 (i, k) 之间的气球
                    // dp[k][j]: 戳破 (k, j) 之间的气球
                    // arr[i] * arr[k] * arr[j]: 最后戳破 k 时获得的硬币（此时 i 和 j 还在）
                    dp[i][j] = Math.max(dp[i][j],
                            dp[i][k] + dp[k][j] + arr[i] * arr[k] * arr[j]);
                }
            }
        }

        return dp[0][size + 1];
    }
}
