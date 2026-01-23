package org.chris.leetcode;

import org.chris.leetcode._template.PackageTemplate;

public class TwoDCostPackTest {

    public static void main(String[] args) {
        PackageTemplate pt = new PackageTemplate();

        System.out.println("=== 二维费用背包问题测试 - twoDCostPackMethod1 ===");

        // 测试案例 1: 基本二维费用背包问题
        // 物品: 重量[2,3,4], 体积[1,2,3], 价值[3,4,5]
        // 背包: 最大重量10, 最大体积7
        int[] weight1 = {2, 3, 4};
        int[] volume1 = {1, 2, 3};
        int[] value1 = {3, 4, 5};
        int W1 = 10; // 背包最大承重
        int V1 = 7;  // 背包最大体积

        int result1 = pt.twoDCostPackMethod1(weight1, volume1, value1, W1, V1);
        System.out.println("测试案例 1 - 结果: " + result1);
        System.out.println("预期: 选择所有物品，总重量9<=10，总体积6<=7，总价值12");
        System.out.println();

        // 测试案例 2: 容量限制案例
        // 物品: 重量[1,1,1], 体积[3,4,5], 价值[10,20,30]
        // 背包: 最大重量5, 最大体积6
        int[] weight2 = {1, 1, 1};
        int[] volume2 = {3, 4, 5};
        int[] value2 = {10, 20, 30};
        int W2 = 5;
        int V2 = 6;

        int result2 = pt.twoDCostPackMethod1(weight2, volume2, value2, W2, V2);
        System.out.println("测试案例 2 - 结果: " + result2);
        System.out.println("预期: 只能选择第1个物品(重量1,体积3,价值10)或第2个物品(重量1,体积4,价值20)，最优选择第2个物品，价值20");
        System.out.println();

        // 测试案例 3: 重量限制案例
        // 物品: 重量[6,7,8], 体积[1,1,1], 价值[10,20,30]
        // 背包: 最大重量10, 最大体积5
        int[] weight3 = {6, 7, 8};
        int[] volume3 = {1, 1, 1};
        int[] value3 = {10, 20, 30};
        int W3 = 10;
        int V3 = 5;

        int result3 = pt.twoDCostPackMethod1(weight3, volume3, value3, W3, V3);
        System.out.println("测试案例 3 - 结果: " + result3);
        System.out.println("预期: 只能选择第1个物品(重量6,体积1,价值10)，因为其他物品重量超过限制，价值10");
        System.out.println();

        // 测试案例 4: 空物品
        int[] weight4 = {};
        int[] volume4 = {};
        int[] value4 = {};
        int W4 = 10;
        int V4 = 10;

        int result4 = pt.twoDCostPackMethod1(weight4, volume4, value4, W4, V4);
        System.out.println("测试案例 4 - 空物品 - 结果: " + result4);
        System.out.println("预期: 0 (没有物品可选)");
        System.out.println();

        // 测试案例 5: 零容量背包
        int[] weight5 = {1, 2};
        int[] volume5 = {1, 2};
        int[] value5 = {10, 20};
        int W5 = 0;
        int V5 = 0;

        int result5 = pt.twoDCostPackMethod1(weight5, volume5, value5, W5, V5);
        System.out.println("测试案例 5 - 零容量背包 - 结果: " + result5);
        System.out.println("预期: 0 (背包容量为0，无法放入任何物品)");
        System.out.println();

        // 测试案例 6: 边界情况 - 选择一个最优物品
        // 物品: 重量[2,3,4], 体积[2,3,4], 价值[1,5,3]
        // 背包: 最大重量5, 最大体积5
        int[] weight6 = {2, 3, 4};
        int[] volume6 = {2, 3, 4};
        int[] value6 = {1, 5, 3};
        int W6 = 5;
        int V6 = 5;

        int result6 = pt.twoDCostPackMethod1(weight6, volume6, value6, W6, V6);
        System.out.println("测试案例 6 - 边界情况 - 结果: " + result6);
        System.out.println("预期: 选择第2个物品(重量3,体积3,价值5)，因为其他组合都不符合条件，价值5");
        System.out.println();

        // 测试案例 7: 可以选择多个物品的案例
        // 物品: 重量[1,2], 体积[1,2], 价值[10,20]
        // 背包: 最大重量3, 最大体积3
        int[] weight7 = {1, 2};
        int[] volume7 = {1, 2};
        int[] value7 = {10, 20};
        int W7 = 3;
        int V7 = 3;

        int result7 = pt.twoDCostPackMethod1(weight7, volume7, value7, W7, V7);
        System.out.println("测试案例 7 - 多物品选择 - 结果: " + result7);
        System.out.println("预期: 选择两个物品，总重量3,总体积3,总价值30");
        System.out.println();

        System.out.println("=== 二维费用背包测试完成 ===");
    }
}