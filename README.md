# Chris 的 LeetCode 解题集

这个仓库包含了我对各种 LeetCode 问题的解答。每种解法都使用 Java 实现，并按问题类型进行组织，以便更好地学习和参考。

## 项目结构

```
src/
└── main/
    └── java/
        └── org/
            └── chris/
                └── leetcode/
                    ├── array/                 # 数组问题
                    ├── string/                # 字符串问题
                    ├── tree/                  # 树问题
                    ├── linkedlist/            # 链表问题
                    ├── dp/                    # 动态规划问题
                    ├── graph/                 # 图问题
                    ├── slidingwindow/         # 滑动窗口问题
                    ├── sort_search/           # 排序和搜索问题
                    ├── math_bit/              # 数学和位运算问题
                    ├── greedy/                # 贪心算法问题
                    ├── design/                # 设计问题
                    ├── hashtable/             # 哈希表问题
                    ├── backtrack/             # 回溯问题
                    ├── dto/                   # 数据结构定义
                    └── Collection/            # 常见模式的模板解决方案
```

## 问题分类

### 数组 (`org.chris.leetcode.array`)
- SortColors_75 (颜色分类)
- MergeIntervals_56 (合并区间)
- MaxArea_11 (盛最多水的容器)
- ThreeSum_15 (三数之和)
- TwoSum_1 (两数之和)
- ProductExceptSelf_238 (除自身以外数组的乘积)
- RotatePic_48 (旋转图像)
- 等更多...

### 字符串 (`org.chris.leetcode.string`)
- LengthOfLongestSubstring_3 (无重复字符的最长子串)
- IsValid_20 (有效的括号)
- IsPalindrome_234 (回文链表)
- FindAnagrams_438 (找到字符串中所有字母异位词)
- 等更多...

### 树 (`org.chris.leetcode.tree`)
- BuildTree_105 (从前序与中序遍历序列构造二叉树)
- SymmetricTree_101 (对称二叉树)
- InorderTraversal_94 (二叉树中序遍历)
- InvertTree_226 (翻转二叉树)
- IsValidBST_98 (验证二叉搜索树)
- MaxDepth_104 (二叉树的最大深度)
- DiameterOfBinaryTree_543 (二叉树的直径)
- KthSmallest_230 (二叉搜索树中第K小的数)
- LevelOrder_100 (二叉树的层序遍历)
- PathSum_437 (路径总和 III)
- 等更多...

### 链表 (`org.chris.leetcode.linkedlist`)
- AddTwoNumbers_2 (两数相加)
- CopyRandomList_138 (复制带随机指针的链表)
- DetectCycle_142 (环形链表 II)
- GetIntersectionNode_160 (相交链表)
- HasCycle_141 (环形链表)
- MergeTwoLists_21 (合并两个有序链表)
- ReverseList_206 (反转链表)
- SortList_148 (排序链表)
- 等更多...

### 动态规划 (`org.chris.leetcode.dp`)
- CoinChange_322 (零钱兑换)
- CoinChange_518 (零钱兑换 II)
- CombinationSum4_377 (组合总和 IV)
- Fib_509 (斐波那契数)
- NumSquares_279 (完全平方数)
- MaxSubArray_53 (最大子数组和)
- LengthOfLIS_300 (最长递增子序列)
- 等更多...

### 图 (`org.chris.leetcode.graph`)
- CloneGraph_133 (克隆图)
- NumIslands_200 (岛屿数量)

以及更多按照常见 LeetCode 问题分类的类别，如"LeetCode Hot 100"列表中的问题。

## 关于本项目

这个项目是用 Java 实现的 LeetCode 问题解答集合。每个文件对应一个特定的问题，包含：
- 问题陈述作为注释
- 适用时提供多种解决方案
- 时间和空间复杂度分析
- 解释算法的详细注释

## 模板和常见模式

`Collection/` 目录包含常见算法模式的模板实现：
- 树遍历模板
- 排序算法模板
- 背包问题模板
- 测试数据生成工具

## 数据结构

`dto/` 目录包含常见的数据结构定义：
- ListNode 用于链表问题
- TreeNode 用于二叉树问题
- Node 用于带有随机指针的问题
- 各种问题的测试数据生成工具

## 如何使用

1. 克隆仓库：
   ```bash
   git clone <repository-url>
   ```

2. 导航到项目目录：
   ```bash
   cd chris-leetcode
   ```

3. 编译和运行特定解法：
   ```bash
   javac src/main/java/org/chris/leetcode/[category]/ProblemClass.java
   java -cp src/main/java org.chris.leetcode.[category].ProblemClass
   ```

## 贡献

欢迎 fork 这个仓库并通过添加更多 LeetCode 解答或改进现有解答来贡献代码。

## 许可证

本项目开源并在 [MIT 许可证](LICENSE) 下提供。