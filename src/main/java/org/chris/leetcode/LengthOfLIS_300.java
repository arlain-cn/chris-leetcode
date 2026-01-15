package org.chris.leetcode;

import java.util.Arrays;

public class LengthOfLIS_300 {

    /**
     * 300. 最长递增子序列
     *
     * @param args
     */
    public static void main(String[] args) {
        LengthOfLIS_300 lengthOfLIS300 = new LengthOfLIS_300();
        System.out.println(lengthOfLIS300.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
//        System.out.println(lengthOfLIS300.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
    }

    /**
     * 方法二：贪心 + 二分查找
     * 一、核心思想：
     * 1. tails[k] 表示长度为 k+1 的 LIS 的最小末尾元素（无闲置索引，动态扩展）
     * 2. 对于每个元素 x，使用二分查找在 tails 中找到第一个 ≥x 的位置
     * 3. 如果 x 比所有元素都大，则追加到 tails 末尾，形成新的 LIS 长度
     * 4. 否则，用较小的 x 更新 tails[k]，优化同长度 LIS 的末尾元素（变得更小）
     * <p>
     * 二、算法步骤
     * 1. 初始化 tails 为空数组。
     * 2. 遍历数组 nums 中的每个元素 x：
     * ----- 使用二分查找在 tails 中找到第一个 ≥x 的位置 k。
     * ----- 如果 k==len(tails)，说明 x 比所有元素都大，追加到 tails 末尾。
     * ----- 否则，用 x 更新 tails[k]，优化同长度 LIS 的末尾元素。
     * 3. 返回 tails 的长度，即为最终 LIS 的长度。
     * <p>
     * 三、以输入序列 [0,8,4,12,2] 为例：
     * 第一步插入 0，d=[0]；
     * 第二步插入 8，d=[0,8]；
     * 第三步插入 4，d=[0,4]；
     * 第四步插入 12，d=[0,4,12]；
     * 第五步插入 2，d=[0,2,12]。
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[] d = new int[len + 1];
        int ans = 1;
        d[1] = nums[0];
        for (int i = 1; i < len; i++) {
            if (d[ans] < nums[i]) {
                ans++;
                d[ans] = nums[i];
            } else {
                //如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 1
                int l = 1, r = ans, pos = 1;
                while (r >= l) {
                    int mid = l + (r - l) / 2;
                    if (d[mid] < nums[i]) {
                        l = mid + 1;
                        //找到nums[i]刚好大于的一个数
                        pos = l;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos] = nums[i];
            }
        }
        return ans;
    }

    /**
     * 1. 阶段划分: 以子序列的结尾位置 i 作为阶段。
     * 2. 定义状态: 设 dp[i] 表示以 nums[i] 结尾的最长递增子序列的长度。
     * 3. 状态转移方程: 对于每个位置 i，我们需要考虑所有在 i 之前的元素 j（0≤j<i）。
     * ------ 如果 nums[j]<nums[i]，说明 nums[i] 可以接在以 nums[j] 结尾的递增子序列后面，从而形成更长的递增子序列。此时，更新 dp[i] 为 dp[i]=max(dp[i],dp[j]+1)。
     * ------- 如果 nums[j]≥nums[i]，则 nums[i] 不能接在 nums[j] 后面，无需更新。
     * 因此，状态转移方程为：dp[i]=max(dp[i],dp[j]+1)，其中 0≤j<i 且 nums[j]<nums[i]。
     * 4. 初始条件: 每个元素自身都可以作为长度为 1 的递增子序列，即 dp[i]=1。
     * 5. 最终结果: 最终答案为 dp 数组中的最大值，即 max(dp)，表示整个数组的最长递增子序列长度。
     *
     * @param nums
     * @return
     */

    public int lengthOfLIS_dp(int[] nums) {
        int len = nums.length;
        //4. 初始条件: 每个元素自身都可以作为长度为 1 的递增子序列，即 dp[i]=1。
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int ans = 1;
        //dp[i]=max(dp[i],dp[j]+1)，其中 0≤j<i 且 nums[j]<nums[i]。
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public int lengthOfLIS_dp2(int[] nums) {
        int len = nums.length;
        //4. 初始条件: 每个元素自身都可以作为长度为 1 的递增子序列，即 dp[i]=1。
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        //dp[i]=max(dp[i],dp[j]+1)，其中 0≤j<i 且 nums[j]<nums[i]。
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int ans = 1;
        for (int x : dp) {
            ans = Math.max(ans, x);
        }
        return ans;
    }
}
