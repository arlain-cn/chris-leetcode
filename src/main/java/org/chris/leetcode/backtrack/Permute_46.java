package org.chris.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Permute_46 {
    /**
     * 46. 全排列
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     *
     * @param args
     */
    public static void main(String[] args) {
        Permute_46 permute46 = new Permute_46();
        System.out.println(permute46.permute(new int[]{1, 2, 3}));
    }

    // 存放所有符合条件结果的集合
    List<List<Integer>> res = new ArrayList<>();
    // 存放当前递归路径下的结果
    List<Integer> path = new ArrayList<>();

    /**
     * 全排列 - 回溯算法
     *
     * @param nums int[]，选择元素列表
     * @return List<List<Integer>>，所有符合条件的结果
     */
    public List<List<Integer>> permute(int[] nums) {
        res.clear();
        path.clear();
        backtrackingPermute(nums);
        return res;
    }

    /**
     * 回溯函数，递归搜索所有全排列
     * 一、明确所有选择：全排列中每个位置上的元素都可以从剩余可选元素中选出，对此画出决策树，如上图所示。
     * 二、明确终止条件：
     *     1. 当遍历到决策树的叶子节点时，就终止了。即当前路径搜索到末尾时，递归终止。
     * 三、将决策树和终止条件翻译成代码：
     *     1. 定义回溯函数：
     *         1️⃣`backtracking(nums)`: 函数的传入参数是 nums（可选数组列表），全局变量是 res（存放所有符合条件结果的集合数组）和 path（存放当前符合条件的结果）。
     *         2️⃣`backtracking(nums): 函数代表的含义是：递归在 nums 中选择剩下的元素`。
     *     2. 书写回溯函数主体（给出选择元素、递归搜索、撤销选择部分）。
     *         1️⃣从当前正在考虑元素，到数组结束为止，枚举出所有可选的元素。对于每一个可选元素：
     *         2️⃣约束条件：之前已经选择的元素不再重复选用，只能从剩余元素中选择。
     *         3️⃣选择元素：将其添加到当前子集数组 path 中。
     *         4️⃣递归搜索：在选择该元素的情况下，继续递归选择剩下元素。
     *         5️⃣撤销选择：将该元素从当前结果数组 path 中移除。
     *
     * for i in range(len(nums)):          # 枚举可选元素列表
     *     if nums[i] not in path:         # 从当前路径中没有出现的数字中选择
     *         path.append(nums[i])        # 选择元素
     *         backtracking(nums)          # 递归搜索
     *         path.pop()                  # 撤销选择
     *     3. 明确递归终止条件（给出递归终止条件，以及递归终止时的处理方法）。
     *         - 当遍历到决策树的叶子节点时，就终止了。也就是存放当前结果的数组 path 的长度等于给定数组 nums 的长度（即 len(path) == len(nums)）时，递归停止。
     * @param nums int[]，选择元素列表
     */
    public void backtrackingPermute(int[] nums) {
        // 递归终止条件：index 超过数组长度时返回
        // 说明找到了一组符合条件的结果
        if (path.size() == nums.length) {
            // 将当前符合条件的结果放入集合中
            // 注意要拷贝一份 path，避免后续修改影响结果
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 从当前路径中没有出现的数字中选择
            if (path.contains(nums[i])) {
                continue;
            }
            // 选择元素
            path.add(nums[i]);
            // 递归搜索
            backtrackingPermute(nums);
            // 撤销选择
            path.remove(path.size() - 1);
        }
    }
}
