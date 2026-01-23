package org.chris.leetcode;

import org.chris.leetcode._template.PackageTemplate;

public class GroupPackTest2 {

    public static void main(String[] args) {
        PackageTemplate pt = new PackageTemplate();

        System.out.println("=== 分组背包问题测试 - groupPackMethod2 ===");

        // 测试案例 1: 基本分组背包问题
        // 假设有3组物品，背包容量为10
        // 第1组: 物品重量[2,3], 价值[3,4]
        // 第2组: 物品重量[3,4], 价值[4,5]
        // 第3组: 物品重量[4,5], 价值[5,6]
        int[] groupCount1 = {2, 2, 2}; // 每组物品数量
        int[][] weight1 = {{2, 3}, {3, 4}, {4, 5}}; // 每组的物品重量
        int[][] value1 = {{3, 4}, {4, 5}, {5, 6}};  // 每组的物品价值
        int W1 = 10; // 背包容量

        int result1 = pt.groupPackMethod2(groupCount1, weight1, value1, W1);
        System.out.println("测试案例 1 - groupPackMethod2 结果: " + result1);
        System.out.println("预期: 从第1组选择重量2价值3的物品，第2组选择重量3价值4的物品，第3组选择重量5价值6的物品，总重量10，总价值13");
        System.out.println();

        int result1Std = pt.groupPackMethod2(groupCount1, weight1, value1, W1);
        System.out.println("测试案例 1 - groupPackMethod2Standard 结果: " + result1Std);
        System.out.println();

        // 测试案例 2: 只有一组物品的简单案例
        int[] groupCount2 = {3}; // 只有一组，有3个物品
        int[][] weight2 = {{1, 2, 3}}; // 三个物品重量分别为1, 2, 3
        int[][] value2 = {{6, 10, 12}};  // 三个物品价值分别为6, 10, 12
        int W2 = 5; // 背包容量

        int result2 = pt.groupPackMethod2(groupCount2, weight2, value2, W2);
        System.out.println("测试案例 2 - groupPackMethod2 结果: " + result2);
        System.out.println("预期: 选择重量3价值12的物品，因为只能从一组中选一个物品");
        System.out.println();

        int result2Std = pt.groupPackMethod2(groupCount2, weight2, value2, W2);
        System.out.println("测试案例 2 - groupPackMethod2Standard 结果: " + result2Std);
        System.out.println();

        // 测试案例 3: 空背包或没有物品
        int[] groupCount3 = {0}; // 一组但没有物品
        int[][] weight3 = {{}}; // 没有物品
        int[][] value3 = {{}};  // 没有物品
        int W3 = 5; // 背包容量

        int result3 = pt.groupPackMethod2(groupCount3, weight3, value3, W3);
        System.out.println("测试案例 3 - groupPackMethod2 没有物品 - 结果: " + result3);
        System.out.println("预期: 0 (没有物品可选)");
        System.out.println();

        int result3Std = pt.groupPackMethod2(groupCount3, weight3, value3, W3);
        System.out.println("测试案例 3 - groupPackMethod2Standard 没有物品 - 结果: " + result3Std);
        System.out.println();

        // 测试案例 4: 背包容量为0
        int[] groupCount4 = {2};
        int[][] weight4 = {{1, 2}};
        int[][] value4 = {{10, 20}};
        int W4 = 0; // 背包容量为0

        int result4 = pt.groupPackMethod2(groupCount4, weight4, value4, W4);
        System.out.println("测试案例 4 - groupPackMethod2 背包容量为0 - 结果: " + result4);
        System.out.println("预期: 0 (背包容量为0，无法放入任何物品)");
        System.out.println();

        int result4Std = pt.groupPackMethod2(groupCount4, weight4, value4, W4);
        System.out.println("测试案例 4 - groupPackMethod2Standard 背包容量为0 - 结果: " + result4Std);
        System.out.println();

        // 测试案例 5: 一个更复杂的案例
        // 假设有2组物品，背包容量为8
        // 第1组: 物品重量[1,4], 价值[1,5]
        // 第2组: 物品重量[3,5], 价值[4,7]
        int[] groupCount5 = {2, 2};
        int[][] weight5 = {{1, 4}, {3, 5}};
        int[][] value5 = {{1, 5}, {4, 7}};
        int W5 = 8;

        int result5 = pt.groupPackMethod2(groupCount5, weight5, value5, W5);
        System.out.println("测试案例 5 - groupPackMethod2 结果: " + result5);
        System.out.println("预期: 最优选择是第一组选重量4价值5的物品，第二组选重量3价值4的物品，总重量7，总价值9");
        System.out.println();

        int result5Std = pt.groupPackMethod2(groupCount5, weight5, value5, W5);
        System.out.println("测试案例 5 - groupPackMethod2Standard 结果: " + result5Std);
        System.out.println();

        System.out.println("=== 所有测试完成 ===");
    }
}