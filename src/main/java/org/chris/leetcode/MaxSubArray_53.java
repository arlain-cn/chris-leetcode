package org.chris.leetcode;

public class MaxSubArray_53 {
    public static void main(String[] args) {
        MaxSubArray_53 maxSubArray53 = new MaxSubArray_53();
        System.out.println(maxSubArray53.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(maxSubArray53.maxSubArray(new int[]{1,-1,1}));
//        System.out.println(maxSubArray53.maxSubArrayDynTmp(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }


    /**
     * 主方法：求解最大子数组和
     *
     * @param nums 输入数组
     * @return 最大子数组和
     */
    public int maxSubArray(int[] nums) {
        // 处理空数组或null情况
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 调用分治递归函数
        return divideAndConquer(nums, 0, nums.length - 1);
    }

    public int divideAndConquer(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        // 计算中间点（防止整数溢出）
        int mid = left + (right - left) / 2;
        // 1. 递归求解左半部分的最大子数组和
        int leftMaxSum = divideAndConquer(nums, left, mid);
        // 2. 递归求解右半部分的最大子数组和
        int rightMaxSum = divideAndConquer(nums, mid + 1, right);
        // 3. 计算跨越中点的最大子数组和
        int crossMaxSum = crossMax(nums, left, mid, right);

        return Math.max(Math.max(leftMaxSum, rightMaxSum), crossMaxSum);
    }

    public int crossMax(int[] nums, int left, int mid, int right) {
        // 计算左半部分（从中点向左）的最大后缀和
        int leftCrossMax = Integer.MIN_VALUE;
        int leftCross = 0;
        // 从中点向左扫描
        for (int i = mid; i >= left; i--) {
            leftCross += nums[i];
            leftCrossMax = Math.max(leftCrossMax, leftCross);
        }

        // 计算右半部分（从中点向右）的最大前缀和
        int rightCrossMax = Integer.MIN_VALUE;
        int rightCross = 0;
        // 从中点+1向右扫描
        for (int i = mid + 1; i <= right; i++) {
            rightCross += nums[i];
            rightCrossMax = Math.max(rightCrossMax, rightCross);
        }

        // 返回左右两部分的合并和
        return leftCrossMax + rightCrossMax;
    }


    /**
     * 动态规划
     * 假设 nums 数组的长度是 n，下标从 0 到 n−1。
     * 我们用 f(i) 代表以第 i 个数结尾的「连续子数组的最大和」，那么很显然我们要求的答案就是：
     * max 0≤i≤n−1{f(i)}
     * <p>
     * 因此我们只需要求出每个位置的 f(i)，然后返回 f 数组中的最大值即可。那么我们如何求 f(i) 呢？我们可以考虑 nums[i] 单独成为一段还是加入 f(i−1) 对应的那一段，这取决于 nums[i] 和 f(i−1)+nums[i] 的大小，我们希望获得一个比较大的，于是可以写出这样的动态规划转移方程：
     * f(i)=max{f(i−1)+nums[i],nums[i]}
     *
     * @param nums
     * @return
     */
    public int maxSubArrayDyn(int[] nums) {
        int maxEndingHere = 0;
        //因为 pre 只代表以当前元素结尾的最佳值，而不一定是全局最佳。比如在上面的例子中，当遍历到最后一个元素时，pre = 5，但全局最大值是之前的 6。如果没有 maxAns 记录历史最大值，就会丢失这个信息。
        int maxSoFar = nums[0];

        for (int num : nums) {
            maxEndingHere = Math.max(maxEndingHere + num, num);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    public int maxSubArray1(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int headSum = 0;
        int tailSum = 0;
        int maxSI = Integer.MIN_VALUE;


        for (int i = 0, j = nums.length - 1; i <= j; i++, j--) {
            headSum += nums[i];
            maxSI = Math.max(maxSI, headSum);
            if (headSum <= 0) {
                headSum = 0;
            }

            if (i != j) {
                tailSum += nums[j];
                maxSI = Math.max(maxSI, tailSum);
                if (tailSum <= 0) {
                    tailSum = 0;
                }
            }

        }
        if (maxSI < 0) {
            return maxSI;
        }
        return Math.max(headSum + tailSum, maxSI);
    }
}
