package org.chris.leetcode.test;

import org.chris.leetcode._template.PackageTemplate;

/**
 * 专门用于测试 twoDCostPackMethod2 方法的测试类
 * 二维费用背包是同时受重量和体积两个约束的背包问题，twoDCostPackMethod2 是滚动数组优化版本
 */
public class TwoDCostPackMethod2Test {

    public static void main(String[] args) {
        PackageTemplate pt = new PackageTemplate();

        System.out.println("=== TwoDCostPackMethod2 测试案例 ===\n");

        // 测试案例 1: 基本二维费用背包问题
        System.out.println("【测试案例 1: 基本二维费用背包】");
        System.out.println("物品: 重量[2,3,4], 体积[1,2,3], 价值[3,4,5]");
        System.out.println("背包限制: 重量<=10, 体积<=7");

        int[] weight1 = {2, 3, 4};
        int[] volume1 = {1, 2, 3};
        int[] value1 = {3, 4, 5};
        int W1 = 10; // 背包最大承重
        int V1 = 7;  // 背包最大体积

        int result1 = pt.twoDCostPackMethod2(weight1, volume1, value1, W1, V1);
        System.out.println("结果: " + result1);
        System.out.println("预期: 12 (选择所有物品，总重量9<=10，总体积6<=7，总价值3+4+5=12)");
        System.out.println();

        // 测试案例 2: 重量或体积限制的案例
        System.out.println("【测试案例 2: 受限选择案例】");
        System.out.println("物品: 重量[1,1,1], 体积[3,4,5], 价值[10,20,30]");
        System.out.println("背包限制: 重量<=5, 体积<=6");

        int[] weight2 = {1, 1, 1};
        int[] volume2 = {3, 4, 5};
        int[] value2 = {10, 20, 30};
        int W2 = 5;
        int V2 = 6;

        int result2 = pt.twoDCostPackMethod2(weight2, volume2, value2, W2, V2);
        System.out.println("结果: " + result2);
        System.out.println("预期: 30 (可选择第1个物品(重量1,体积3,价值10)或第2个物品(重量1,体积4,价值20)或第3个物品(重量1,体积5,价值30)，最优选择第3个物品)");
        System.out.println();

        // 测试案例 3: 空物品案例
        System.out.println("【测试案例 3: 空物品案例】");
        System.out.println("物品: []");
        System.out.println("背包限制: 重量<=10, 体积<=10");

        int[] weight3 = {};
        int[] volume3 = {};
        int[] value3 = {};
        int W3 = 10;
        int V3 = 10;

        int result3 = pt.twoDCostPackMethod2(weight3, volume3, value3, W3, V3);
        System.out.println("结果: " + result3);
        System.out.println("预期: 0 (没有物品可选)");
        System.out.println();

        // 测试案例 4: 零容量背包案例
        System.out.println("【测试案例 4: 零容量背包案例】");
        System.out.println("物品: 重量[1,2], 体积[1,2], 价值[10,20]");
        System.out.println("背包限制: 重量<=0, 体积<=0");

        int[] weight4 = {1, 2};
        int[] volume4 = {1, 2};
        int[] value4 = {10, 20};
        int W4 = 0;
        int V4 = 0;

        int result4 = pt.twoDCostPackMethod2(weight4, volume4, value4, W4, V4);
        System.out.println("结果: " + result4);
        System.out.println("预期: 0 (背包容量为0，无法放入任何物品)");
        System.out.println();

        // 测试案例 5: 单物品案例
        System.out.println("【测试案例 5: 单物品案例】");
        System.out.println("物品: 重量[5], 体积[3], 价值[15]");
        System.out.println("背包限制: 重量<=5, 体积<=3");

        int[] weight5 = {5};
        int[] volume5 = {3};
        int[] value5 = {15};
        int W5 = 5;
        int V5 = 3;

        int result5 = pt.twoDCostPackMethod2(weight5, volume5, value5, W5, V5);
        System.out.println("结果: " + result5);
        System.out.println("预期: 15 (可以放入唯一的物品，获得价值15)");
        System.out.println();

        // 测试案例 6: 多物品选择优化案例
        System.out.println("【测试案例 6: 多物品选择优化案例】");
        System.out.println("物品: 重量[1,2,2], 体积[1,1,2], 价值[10,15,20]");
        System.out.println("背包限制: 重量<=4, 体积<=3");

        int[] weight6 = {1, 2, 2};
        int[] volume6 = {1, 1, 2};
        int[] value6 = {10, 15, 20};
        int W6 = 4;
        int V6 = 3;

        int result6 = pt.twoDCostPackMethod2(weight6, volume6, value6, W6, V6);
        System.out.println("结果: " + result6);
        System.out.println("预期: 35 (可以选择第1个和第3个物品，重量3体积3，总价值30；或者第2个和第3个物品，重量4体积3，总价值35)");
        System.out.println();

        System.out.println("=== TwoDCostPackMethod2 测试完成 ===");
    }
}