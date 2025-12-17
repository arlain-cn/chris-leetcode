package org.chris.leetcode;

import java.util.*;

public class ThreeSum_15 {

    /**
     * 15. 三数之和
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
     *
     * @param args
     */
    public static void main(String[] args) {
        ThreeSum_15 threeSum15 = new ThreeSum_15();
        System.out.println(threeSum15.threeSum3(new int[]{1, 1, 1}));
        System.out.println(threeSum15.threeSum3(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    /**
     * 暴力解法（三重循环）的时间复杂度为O(n³)，当n较大时（如10³级）会超时，如何优化？
     * 排序后，固定第一个数（i），剩余部分用双指针（left = i+1, right = n-1）如何高效找两个数使和为 -nums[i]？（提示：若当前和 > 0 则 right--，若 < 0 则 left++）
     * 如何避免重复三元组？（例如，当nums[i] == nums[i-1]时跳过i，当nums[left] == nums[left+1]时跳过left）
     *
     * @param nums
     * @return
     */

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 3) {
            return ans;
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            int targetSum = -1 * nums[i];
            while (right > left) {
                if (targetSum > nums[left] + nums[right]) {
                    left++;
                    // 匹配不上也没必要防重 nums[left] == nums[left - 1]
                } else if (targetSum < nums[left] + nums[right]) {
                    right--;
                } else {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    right--;
                    left++;
                    //防重，对于已排序的数组，左指针不变，右指针左移，两元素之和不变，证明右指针移动前后值相同，反之亦然
                    while (right > left && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (right > left && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }
        return ans;
    }

    public List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 3) {
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            int two_sum = 0 - nums[i];
            while (right > left) {
                if (two_sum > nums[left] + nums[right]) {
                    // 匹配不上也没必要防重 nums[left] == nums[left - 1]
                    left++;
                } else if (two_sum < nums[left] + nums[right]) {
                    right--;
                } else {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //防重，对于已排序的数组，右指针不变，左指针左移，两元素之和不变，证明左指针移动前后值相同，反之亦然
                    left++;
                    while (right > left && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    //防重，对于已排序的数组，左指针不变，右指针左移，两元素之和不变，证明右指针移动前后值相同，反之亦然
                    right--;
                    while (right > left && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }
        return ans;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) {
            return res;
        }
        Set<String> set = new HashSet<>();
        //i != j、i != k 且 j != k
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (i == j || i == k || j == k) {
                        continue;
                    }
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        int[] elements = new int[]{nums[i], nums[j], nums[k]};
                        Arrays.sort(elements);
                        String setKey = elements[0] + "-" + elements[1] + "-" + elements[2];
                        if (!set.contains(setKey)) {
                            set.add(setKey);
                            res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        }
                    }
                }
            }
        }
        return res;
    }
}
