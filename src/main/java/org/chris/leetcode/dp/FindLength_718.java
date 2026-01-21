package org.chris.leetcode.dp;

public class FindLength_718 {

    /**
     * 718. 最长重复子数组
     * <p>
     * 本节介绍与 LCS（最长公共子序列）类似的「最长重复子数组」问题。两者主要区别在于：
     * <p>
     * LCS 求最长公共「子序列」，可不连续；
     * 最长重复子数组要求最长公共「子数组」，必须连续。
     * 两者的状态定义和转移类似，但最长重复子数组只有当前元素相等时才能从左上角转移，否则状态归零。
     *
     * @param args
     */
    public static void main(String[] args) {
        FindLength_718 findLength718 = new FindLength_718();
        System.out.println(findLength718.findLength(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7}));
    }

    /**
     * 方法二：滑动窗口
     * 我们注意到之所以两位置会比较多次，是因为重复子数组在两个数组中的位置可能不同。以 A = [3, 6, 1, 2, 4], B = [7, 1, 2, 9] 为例，它们的最长重复子数组是 [1, 2]，在 A 与 B 中的开始位置不同。
     * <p>
     * 但如果我们知道了开始位置，我们就可以根据它们将 A 和 B 进行「对齐」，即：
     * <p>
     * A = [3, 6, 1, 2, 4]
     * B =    [7, 1, 2, 9]
     * ↑  ↑
     * 此时，最长重复子数组在 A 和 B 中的开始位置相同，我们就可以对这两个数组进行一次遍历，得到子数组的长度，伪代码如下：
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        int size1 = nums1.length;
        int size2 = nums2.length;
        int ans = 0;
        //第一类为 A 不变，B 的首元素与 A 中的某个元素对齐；
        for (int i = 0; i < size1; i++) {
            int len = Math.min(size1 - i, size2);
            int maxLength = maxLength(nums1, nums2, i, 0, len);
            ans = Math.max(ans, maxLength);
        }
        //第二类为 B 不变，A 的首元素与 B 中的某个元素对齐。
        for (int i = 0; i < size2; i++) {
            int len = Math.min(size1, size2 - i);
            int maxLength = maxLength(nums1, nums2, 0, i, len);
            ans = Math.max(ans, maxLength);
        }
        return ans;
    }

    public int maxLength(int[] nums1, int[] nums2, int add1, int add2, int len) {
        int ret = 0, k = 0;
        for (int i = 0; i < len; i++) {
            if (nums1[add1 + i] == nums2[add2 + i]) {
                k++;
            } else {
                k = 0;
            }
            ret = Math.max(ret, k);
        }
        return ret;
    }

    /**
     * 方法一：动态规划
     * 1. 阶段划分:以两个数组的结尾位置 (i,j) 作为阶段。
     * 2. 状态定义:设 dp[i][j] 表示以 nums1 的第 i−1 个元素和 nums2 的第 j−1 个元素结尾的最长公共子数组长度。
     * 3. 状态转移方程:遍历 nums1 和 nums2 的所有元素，状态转移分为两种情况：
     * ----- 如果 nums1[i−1]==nums2[j−1]，则当前元素可以作为公共子数组的延续：dp[i][j]=dp[i−1][j−1]+1。
     * ----- 如果 nums1[i−1]!=nums2[j−1]，则无法延续公共子数组：dp[i][j]=0。
     * 4. 初始条件:dp[0][j]=0，即 nums1 为空时，与 nums2 任意前缀的最长公共子序列长度为 0。dp[i][0]=0，即 nums2 为空时，与 nums1 任意前缀的最长公共子序列长度为 0。
     * 5. 结果表示:在遍历过程中，用 res 记录所有 dp[i][j] 的最大值，最终 res 即为所求答案。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength_dp(int[] nums1, int[] nums2) {
        int size1 = nums1.length;
        int size2 = nums2.length;

        int[][] dp = new int[size1 + 1][size2 + 1];
        int ans = 0;
        for (int i = 1; i <= size1; i++) {
            for (int j = 1; j <= size2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

}
