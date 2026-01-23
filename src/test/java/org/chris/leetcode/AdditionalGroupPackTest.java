package org.chris.leetcode;

import org.chris.leetcode._template.PackageTemplate;

public class AdditionalGroupPackTest {

    public static void main(String[] args) {
        PackageTemplate pt = new PackageTemplate();

        System.out.println("=== 分组背包问题补充测试 - groupPackMethod1 ===");

        // 测试案例 1: 每组只有一个物品的特殊情况
        // 3组物品，每组只有1个物品
        // 第1组: 重量3，价值10
        // 第2组: 重量4，价值15
        // 第3组: 重量2，价值8
        // 背包容量为7
        int[] groupCount1 = {1, 1, 1};
        int[][] weight1 = {{3}, {4}, {2}};
        int[][] value1 = {{10}, {15}, {8}};
        int W1 = 7;

        int result1 = pt.groupPackMethod1(groupCount1, weight1, value1, W1);
        System.out.println("测试案例 1 - 每组一个物品 - 结果: " + result1);
        System.out.println("预期: 选择第1组和第3组物品，重量5价值18，或者只选第2组物品重量4价值15，最优为18");
        System.out.println();

        // 测试案例 2: 某组物品重量都超过背包容量
        int[] groupCount2 = {2, 2};
        int[][] weight2 = {{8, 9}, {2, 3}}; // 第一组物品重量都超过背包容量5
        int[][] value2 = {{10, 20}, {5, 8}};
        int W2 = 5;

        int result2 = pt.groupPackMethod1(groupCount2, weight2, value2, W2);
        System.out.println("测试案例 2 - 某组超重 - 结果: " + result2);
        System.out.println("预期: 只能从第二组中选择，最优是重量3价值8的物品");
        System.out.println();

        // 测试案例 3: 物品价值为0的边界情况
        int[] groupCount3 = {2, 2};
        int[][] weight3 = {{1, 2}, {3, 4}};
        int[][] value3 = {{0, 5}, {0, 10}}; // 第一组的第一个物品和第二组的第一个物品价值为0
        int W3 = 6;

        int result3 = pt.groupPackMethod1(groupCount3, weight3, value3, W3);
        System.out.println("测试案例 3 - 零价值物品 - 结果: " + result3);
        System.out.println("预期: 应该选择价值更高的物品");
        System.out.println();

        // 测试案例 4: 大容量背包，选择策略不同
        int[] groupCount4 = {3, 2, 2};
        int[][] weight4 = {{1, 3, 5}, {2, 4}, {1, 3}};
        int[][] value4 = {{2, 6, 10}, {4, 8}, {3, 7}};
        int W4 = 10;

        int result4 = pt.groupPackMethod1(groupCount4, weight4, value4, W4);
        System.out.println("测试案例 4 - 大容量背包 - 结果: " + result4);
        System.out.println("预期: 会根据性价比选择最优组合");
        System.out.println();

        // 测试案例 5: 精确匹配背包容量
        int[] groupCount5 = {2, 2};
        int[][] weight5 = {{5, 7}, {3, 5}};
        int[][] value5 = {{10, 15}, {6, 10}};
        int W5 = 8; // 可以选第一组的重量5或第二组的重量3+第一组的重量5

        int result5 = pt.groupPackMethod1(groupCount5, weight5, value5, W5);
        System.out.println("测试案例 5 - 精确匹配 - 结果: " + result5);
        System.out.println("预期: 需要比较不同选择的价值");
        System.out.println();

        // 测试案例 6: 所有物品都选不了的情况
        int[] groupCount6 = {2, 2};
        int[][] weight6 = {{10, 15}, {12, 20}};
        int[][] value6 = {{100, 200}, {150, 300}};
        int W6 = 5; // 所有物品都太重

        int result6 = pt.groupPackMethod1(groupCount6, weight6, value6, W6);
        System.out.println("测试案例 6 - 全部超重 - 结果: " + result6);
        System.out.println("预期: 0 (无法选择任何物品)");
        System.out.println();

        System.out.println("=== 补充测试完成 ===");
    }
}