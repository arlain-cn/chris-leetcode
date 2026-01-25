package org.chris.leetcode._template;

public class PackageTemplateTester {

    public static void main(String[] args) {
        PackageTemplate template = new PackageTemplate();

        System.out.println("测试 zeroOnePackMaxProfitNumbers1 方法:\n");

        // 测试案例 1: 重量 [2, 1, 3], 价值 [4, 2, 3], 背包容量 4
        System.out.println("测试案例 1:");
        System.out.println("重量: [2, 1, 3], 价值: [4, 2, 3], 背包容量: 4");
        int[] weight1 = {2, 1, 3};
        int[] value1 = {4, 2, 3};
        int W1 = 4;
        int result1 = template.zeroOnePackMaxProfitNumbers1(weight1, value1, W1);
        System.out.println("最大价值方案数: " + result1);
        System.out.println("验证结果: " + (result1 > 0 ? "通过" : "失败"));
        System.out.println();

        // 测试案例 2: 只有一个物品
        System.out.println("测试案例 2:");
        System.out.println("重量: [2], 价值: [5], 背包容量: 5");
        int[] weight2 = {2};
        int[] value2 = {5};
        int W2 = 5;
        int result2 = template.zeroOnePackMaxProfitNumbers1(weight2, value2, W2);
        System.out.println("最大价值方案数: " + result2);
        System.out.println("验证结果: " + (result2 > 0 ? "通过" : "失败"));
        System.out.println();

        // 测试案例 3: 背包容量为0
        System.out.println("测试案例 3:");
        System.out.println("重量: [1, 2, 3], 价值: [4, 5, 6], 背包容量: 0");
        int[] weight3 = {1, 2, 3};
        int[] value3 = {4, 5, 6};
        int W3 = 0;
        int result3 = template.zeroOnePackMaxProfitNumbers1(weight3, value3, W3);
        System.out.println("最大价值方案数: " + result3);
        System.out.println("验证结果: " + (result3 == 1 ? "通过" : "失败"));
        System.out.println();

        // 测试案例 4: 所有物品重量都大于背包容量
        System.out.println("测试案例 4:");
        System.out.println("重量: [5, 6, 7], 价值: [1, 2, 3], 背包容量: 4");
        int[] weight4 = {5, 6, 7};
        int[] value4 = {1, 2, 3};
        int W4 = 4;
        int result4 = template.zeroOnePackMaxProfitNumbers1(weight4, value4, W4);
        System.out.println("最大价值方案数: " + result4);
        System.out.println("验证结果: " + (result4 == 1 ? "通过" : "失败"));
        System.out.println();

        // 测试案例 5: 多个相同最大价值方案
        System.out.println("测试案例 5:");
        System.out.println("重量: [1, 1, 2], 价值: [2, 2, 3], 背包容量: 2");
        int[] weight5 = {1, 1, 2};
        int[] value5 = {2, 2, 3};
        int W5 = 2;
        int result5 = template.zeroOnePackMaxProfitNumbers1(weight5, value5, W5);
        System.out.println("最大价值方案数: " + result5);
        System.out.println("验证结果: " + (result5 > 0 ? "通过" : "失败"));
        System.out.println();

        // 测试案例 6: 空数组
        System.out.println("测试案例 6:");
        System.out.println("重量: [], 价值: [], 背包容量: 5");
        int[] weight6 = {};
        int[] value6 = {};
        int W6 = 5;
        int result6 = template.zeroOnePackMaxProfitNumbers1(weight6, value6, W6);
        System.out.println("最大价值方案数: " + result6);
        System.out.println("验证结果: " + (result6 == 1 ? "通过" : "失败"));
        System.out.println();

        // 测试案例 7: 单个物品刚好能装下
        System.out.println("测试案例 7:");
        System.out.println("重量: [5], 价值: [10], 背包容量: 5");
        int[] weight7 = {5};
        int[] value7 = {10};
        int W7 = 5;
        int result7 = template.zeroOnePackMaxProfitNumbers1(weight7, value7, W7);
        System.out.println("最大价值方案数: " + result7);
        System.out.println("验证结果: " + (result7 > 0 ? "通过" : "失败"));
        System.out.println();

        System.out.println("所有测试案例完成！");
    }
}