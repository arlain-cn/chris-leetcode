# CLAUDE.md

本文件为 Claude Code (claude.ai/code) 在此代码仓库中工作时提供指导。

## 代码库概览

这是一个 LeetCode 练习仓库，包含按问题编号和主题组织的算法问题解答。代码库遵循模块化方法，具有针对不同算法模式的模板，如动态规划（背包问题）、树算法、链表等。

## 架构与结构

- **包组织**：问题组织在 `org.chris.leetcode` 下，可重用模板在 `org.chris.leetcode.Collection`
- **命名约定**：类名为 `{问题名称}_{问题编号}.java`（例如 `MaxSlidingWindow_239.java`）
- **解决方案模式**：许多类包含多种解决方法，并附有详细中文注释解释方法
- **模板类**：`PackageTemplate.java` 包含 0-1 背包、完全背包和多重背包问题的可重用算法模板
- **DTO 层**：数据传输对象如 `TreeNode`、`ListNode`、`Node` 在 dto 包中

## 开发命令

### 编译与执行
```bash
# 编译特定 Java 文件
javac -d . /path/to/file.java

# 运行类（从 src/main/java 目录）
java org.chris.leetcode.ClassName

# 编译包中的所有 Java 文件
javac org/chris/leetcode/*.java
javac org/chris/leetcode/Collection/*.java
```

### 问题解决工作流程
1. 文件通常包含用于测试的 main 方法
2. 解决方案通常包含带详细解释的多种方法
3. 许多实现包括解释算法概念的详细注释

## 主要特性

### 算法模板
- **背包问题**：`PackageTemplate.java` 中的全面实现实现，包含 0-1、完全和多重背包算法
- **动态规划**：广泛覆盖 2D 和空间优化版本
- **树算法**：二叉树操作、遍历模式
- **链表操作**：各种链表问题解决方案

### 文档风格
- 广泛使用中文注释进行算法解释
- 详细的时间/空间复杂度分析
- 逐步的问题解决方法文档
- 通常为同一问题提供多种方法

## 常见模式

### 动态规划模板结构
1. 阶段划分 (阶段划分)
2. 状态定义 (定义状态)
3. 状态转移方程 (状态转移方程)
4. 初始条件 (初始条件)
5. 最终结果 (最终结果)

### 测试方法
- Main 方法通常包含带样本输入的测试用例
- 预期行为通常在注释中描述
- 单个类中通常包含多个测试用例

## 约定
- 回复全部用中文
- 测试案例相关的代码全部放到目录：org.chris.leetcode