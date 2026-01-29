package org.chris.leetcode._template;

import java.util.ArrayList;
import java.util.List;

public class BinarySolution {

    public static void main(String[] args) {
        BinarySolution solution = new BinarySolution();
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = solution.subsets(nums);
        System.out.println("Subsets of [1, 2, 3]:");
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }

    /**
     * 二进制枚举子集
     * 返回集合 S 的所有子集
     *
     * @param nums int[]，输入集合
     * @return List<List<Integer>>，所有子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length; // n 为集合 S 的元素个数
        List<List<Integer>> subSets = new ArrayList<>(); // sub_sets 用于保存所有子集

        // 枚举 0 ~ 2^n - 1 的所有可能，每个 i 表示一种选取方案
        for (int i = 0; i < (1 << n); i++) {
            List<Integer> subSet = new ArrayList<>(); // sub_set 用于保存当前子集
            // 枚举集合 S 的每一个元素
            //todo 1<<n表示所有可能性的数量，下边这个循环是对每种可能性构建子集元素
            for (int j = 0; j < n; j++) {
                // (i >> j) & 1 判断第 j 位是否为 1
                //todo 如果为 1，说明在当前子集方案 i 中选取了 S[j]
                if (((i >> j) & 1) == 1) { // 如果第 j 位为 1，则选取 S[j]
                    subSet.add(nums[j]); // 将选取的元素 S[j] 加入到当前子集 sub_set 中
                }
            }
            subSets.add(subSet); // 将当前子集 sub_set 加入到所有子集数组 sub_sets 中
        }
        return subSets; // 返回所有子集
    }

}
