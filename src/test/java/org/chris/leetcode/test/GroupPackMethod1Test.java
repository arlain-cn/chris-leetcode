package org.chris.leetcode.test;

import org.chris.leetcode._template.PackageTemplate;

/**
 * 专门用于测试 groupPackMethod1 方法的测试类
 * 分组背包问题是这样一类问题：物品被分成若干组，每组中的物品相互冲突，最多选一个
 */
public class GroupPackMethod1Test {

    public static void main(String[] args) {
        PackageTemplate pt = new PackageTemplate();

        System.out.println("=== GroupPackMethod1 测试案例 ===\n");

        // 测试案例 1: 经典分组背包问题
        System.out.println("【测试案例 1: 经典分组背包】");
        System.out.println("3组物品，背包容量10:");
        System.out.println("- 第1组: 物品1(重量2,价值3), 物品2(重量3,价值4)");
        System.out.println("- 第2组: 物品1(重量3,价值4), 物品2(重量4,价值5)");
        System.out.println("- 第3组: 物品1(重量4,价值5), 物品2(重量5,价值6)");

        int[] groupCount1 = {2, 2, 2};
        int[][] weight1 = {{2, 3}, {3, 4}, {4, 5}};
        int[][] value1 = {{3, 4}, {4, 5}, {5, 6}};
        int W1 = 10;

        int result1 = pt.groupPackMethod1(groupCount1, weight1, value1, W1);
        System.out.println("结果: " + result1);
        System.out.println("最优解: 第1组选重量2价值3，第2组选重量3价值4，第3组选重量5价值6，总重量10，总价值12");
        System.out.println("(注：第3组选重量5价值6，比第1组选重量3价值4 + 第2组选重量4价值5更优)");
        System.out.println();

        // 测试案例 2: 每组仅选一个物品
        System.out.println("【测试案例 2: 每组仅选一个物品的约束】");
        System.out.println("1组3个物品，背包容量5:");
        System.out.println("- 第1组: 物品1(重量1,价值6), 物品2(重量2,价值10), 物品3(重量3,价值12)");

        int[] groupCount2 = {3};
        int[][] weight2 = {{1, 2, 3}};
        int[][] value2 = {{6, 10, 12}};
        int W2 = 5;

        int result2 = pt.groupPackMethod1(groupCount2, weight2, value2, W2);
        System.out.println("结果: " + result2);
        System.out.println("最优解: 选重量3价值12的物品 (注意：虽然重量1+2=3总价值16>12，但每组只能选一个)");
        System.out.println();

        // 测试案例 3: 空背包情况
        System.out.println("【测试案例 3: 空背包情况】");
        System.out.println("背包容量为0:");

        int[] groupCount3 = {2};
        int[][] weight3 = {{1, 2}};
        int[][] value3 = {{10, 20}};
        int W3 = 0;

        int result3 = pt.groupPackMethod1(groupCount3, weight3, value3, W3);
        System.out.println("结果: " + result3);
        System.out.println("最优解: 0 (背包容量为0，无法放入任何物品)");
        System.out.println();

        // 测试案例 4: 没有物品情况
        System.out.println("【测试案例 4: 没有物品情况】");
        System.out.println("每组都没有物品:");

        int[] groupCount4 = {0}; // 一组但没有物品
        int[][] weight4 = {{}};
        int[][] value4 = {{}};
        int W4 = 10;

        int result4 = pt.groupPackMethod1(groupCount4, weight4, value4, W4);
        System.out.println("结果: " + result4);
        System.out.println("最优解: 0 (没有物品可选)");
        System.out.println();

        // 测试案例 5: 复杂组合情况
        System.out.println("【测试案例 5: 复杂组合情况】");
        System.out.println("2组物品，背包容量8:");
        System.out.println("- 第1组: 物品1(重量1,价值1), 物品2(重量4,价值5)");
        System.out.println("- 第2组: 物品1(重量3,价值4), 物品2(重量5,价值7)");

        int[] groupCount5 = {2, 2};
        int[][] weight5 = {{1, 4}, {3, 5}};
        int[][] value5 = {{1, 5}, {4, 7}};
        int W5 = 8;

        int result5 = pt.groupPackMethod1(groupCount5, weight5, value5, W5);
        System.out.println("结果: " + result5);
        System.out.println("最优解: 第1组选重量4价值5，第2组选重量3价值4，总重量7，总价值9");
        System.out.println("验证: 重量4+3=7≤8，价值5+4=9；其他组合价值不会更高");
        System.out.println();

        System.out.println("=== 测试完成 ===");
    }
}