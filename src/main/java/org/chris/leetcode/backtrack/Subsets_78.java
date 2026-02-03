package org.chris.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Subsets_78 {
    /**
     * 78. 子集
     * <p>
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * <p>
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     *
     * @param args
     */
    public static void main(String[] args) {
        Subsets_78 subsets78 = new Subsets_78();
        System.out.println(subsets78.subsets(new int[]{1, 2, 3}));
    }

    // 存放所有符合条件结果的集合
    List<List<Integer>> res = new ArrayList<>();
    // 存放当前递归路径下的结果
    List<Integer> path = new ArrayList<>();

    /**
     * 回溯法求解子集问题
     *
     * @param nums int[]，输入数组
     * @return List<List<Integer>>，所有子集的列表
     */
    public List<List<Integer>> subsets(int[] nums) {
        // 清空结果集和路径，确保多次调用时状态正确
        res.clear();
        path.clear();
        backtrackingSubsets(nums, 0);
        return res;
    }


    /**
     * 回溯函数，递归枚举所有子集
     * 1. 明确所有选择：对于数组的每个位置，都可以选择是否将该元素加入当前子集。决策树的每一层对应一个元素的选择与否。
     * 2. 明确终止条件：
     *     - 当递归遍历到数组末尾（即所有元素都被考虑过）时，递归终止。
     * 3. 将思路转化为代码实现：
     *     一、定义回溯函数：
     *         1️⃣ `backtracking(nums, index)`，其中 nums 是原始数组，index 表示当前递归到的元素下标。全局变量 `res` 用于存储所有子集结果，`path` 用于存储当前子集路径。
     *         2️⃣ 该函数的含义是：从 `index` 开始，依次尝试将后续元素加入子集，递归搜索所有可能的组合。
     *     二、编写回溯主体逻辑（选择、递归、回溯）：`index` 开始，依次枚举每个可选元素。对于每个元素：
     *         1️⃣ 去重约束：每次递归都从 `index` 开始，避免重复选择已考虑过的元素，保证子集不重复（如 {1,2} 和 {2,1} 视为同一子集）。
     *         2️⃣ 选择：将当前元素加入 `path`。
     *         3️⃣ 递归：递归进入下一层，继续选择下一个元素。
     *         4️⃣ 回溯：递归返回后，移除刚刚加入的元素，恢复现场，尝试其他分支。
     *
     * ```java
     * # 从当前下标开始，依次尝试选择每个元素
     * for i in range(index, len(nums)):
     *     path.append(nums[i])        # 选择当前元素，加入子集
     *     backtracking(i + 1)         # 递归，继续选择下一个元素
     *     path.pop()                  # 撤销选择，回溯到上一步
     * ```
     *     三、明确递归终止条件及结果处理：
     *         - 每次进入回溯函数时，都将当前 path 加入结果集 res，因为子集问题需要收集所有状态（包括中间状态和叶子节点）。
     *         - 当 `index≥len(nums)` 时，递归自然终止，无需额外处理。
     * 简而言之，回溯法通过「选择 - 递归 - 回溯」三步，系统地枚举所有子集，并通过合理的约束避免重复。
     * @param nums  int[]，输入数组
     * @param index int，当前递归到的元素下标
     */
    private void backtrackingSubsets(int[] nums, int index) {
        // 每次进入回溯函数，都将当前路径（子集）加入结果集
        res.add(new ArrayList<>(path));

        // 递归终止条件：index 超过数组长度时返回
        if (index >= nums.length) {
            return;
        }

        // 从当前下标开始，依次尝试选择每个元素
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]); // 选择当前元素，加入子集
            backtrackingSubsets(nums, i + 1); // 递归，继续选择下一个元素
            path.remove(path.size() - 1); // 撤销选择，回溯到上一步
        }
    }
}
