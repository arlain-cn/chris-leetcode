package org.chris.leetcode.dp;

public class CanPartition_416 {
    /**
     * 416. 分割等和子集
     *
     * @param args
     */
    public static void main(String[] args) {
        CanPartition_416 canPartition416 = new CanPartition_416();
        int[] nums = new int[]{1, 5, 11, 5};
        System.out.println(canPartition416.canPartition3(nums));
        System.out.println(canPartition416.canPartition2(nums));
    }

    /**
     * 方法一：动态规划，0-1背包
     * 本题实质是：能否从数组中选出若干元素，使其和恰好等于整个数组元素和的一半。
     * <p>
     * 这实际上就是典型的「0-1 背包问题」：
     * <p>
     * 1. 设数组元素和为 sum，目标为 target= sum/2 ，即背包容量。
     * 2. 数组中的每个元素 nums[i] 视为一件物品，其重量和价值均为 nums[i]。
     * 3. 每种物品只能选择一次。
     * 4. 如果能恰好装满容量为 target 的背包，则最大价值也为 target。
     * 因此，问题转化为：给定物品数组 nums，每件物品重量和价值均为 nums[i]，背包容量为 target，每件物品最多选一次，问能否恰好装满背包。
     *
     * @param nums
     * @return
     */

    public boolean canPartition3(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        int sum = 0;
        for (int x : nums) {
            sum += x;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        int length = nums.length;
        int[][] dp = new int[length + 1][target + 1];
        for (int i = 1; i <= length; i++) {
            //w从0开始
            for (int w = 0; w <= target; w++) {
                //i-1表示第i个元素
                if (w < nums[i - 1]) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - nums[i - 1]] + nums[i - 1]);
                }
            }
        }
        return dp[length][target] == target;
    }

    /**
     * 0-1 背包问题的滚动数组优化
     *
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        int sum = 0;
        for (int x : nums) {
            sum += x;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];

        for (int i = 1; i <= nums.length; i++) {
            // 必须逆序遍历容量，防止状态被提前覆盖
            for (int w = target; w >= nums[i-1]; w--) {
                if (w >= nums[i - 1]) {
                    // 状态转移：不选第 i 件物品 or 选第 i 件物品
                    // dp[w] = max(不选, 选)
                    // 解释：
                    // dp[w]：不选第 i 件物品，价值不变
                    // dp[w - weight[i]] + value[i]：选第 i 件物品，容量减少，相应加上价值
                    dp[w] = Math.max(dp[w], dp[w - nums[i - 1]] + nums[i - 1]);
                }
            }
        }
        return dp[target] == target;
    }

    public boolean canPartition1(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        int sum = 0;
        for (int x : nums) {
            sum += x;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int w = target; w >= nums[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - nums[i]] + nums[i]);
            }
        }
        return dp[target] == target;
    }


    public boolean canPartition0(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        int sum = 0;
        for (int x : nums) {
            sum += x;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        int ret = zeroOnePackMethod2(nums, nums, target);
        return ret == target;
    }

    public int zeroOnePackMethod2(int[] weight, int[] value, int W) {
        int size = weight.length;
        // dp[w] 表示容量为 w 时背包可获得的最大价值
        int[] dp = new int[W + 1];

        // 遍历每一件物品
        for (int i = 0; i < size; i++) {
            // 必须逆序遍历容量，防止状态被提前覆盖
            for (int w = W; w >= weight[i]; w--) {
                // 状态转移：不选第 i 件物品 or 选第 i 件物品
                // dp[w] = max(不选, 选)
                dp[w] = Math.max(dp[w], dp[w - weight[i]] + value[i]);
                // 解释：
                // dp[w]：不选第 i 件物品，价值不变
                // dp[w - weight[i]] + value[i]：选第 i 件物品，容量减少，相应加上价值
            }
        }

        return dp[W];
    }
}
