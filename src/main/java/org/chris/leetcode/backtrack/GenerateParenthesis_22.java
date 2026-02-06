package org.chris.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis_22 {

    /**
     *
     * 22. 括号生成
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     *
     * @param args
     */
    public static void main(String[] args) {
        GenerateParenthesis_22 generateParenthesis22 = new GenerateParenthesis_22();
        System.out.println(generateParenthesis22.generateParenthesis(3));
    }

    /**
     * 用于保存所有有效的括号组合
     */
    private List<String> parenthesisRes = new ArrayList<>();
    /**
     * 当前括号组合
     */
    private StringBuilder parenthesisPath = new StringBuilder();

    /**
     * 思路 1：回溯算法
     * 为了生成的括号组合是有效的，回溯的时候，使用一个标记变量 symbol 来表示是否当前组合是否成对匹配。
     *
     * 如果在当前组合中增加一个 (，则令 symbol 加 1，如果增加一个 )，则令 symbol 减 1。**显然只有在 symbol < n 的时候，才能增加 (，在 symbol > 0 的时候，才能增加 )。**
     *
     * `如果最终生成 2×n 的括号组合，并且 symbol == 0，则说明当前组合是有效的，将其加入到最终答案数组中`。
     *
     * 下面我们根据回溯算法`三步走`，写出对应的回溯算法。
     *
     * 1. 明确所有选择：2×n 的括号组合中的每个位置，都可以从 `(` 或者 `)` 中选出。并且，只有在 `symbol < n 的时候，才能选择 (，在 symbol > 0 的时候，才能选择 )`。
     *
     * 2. 明确终止条件：
     *     - 当遍历到决策树的叶子节点时，就终止了。即当前路径搜索到末尾时，递归终止。
     * 将决策树和终止条件翻译成代码：
     *
     *     一、定义回溯函数：
     *         1️⃣backtracking(symbol, index): 函数的传入参数是 symbol（用于表示是否当前组合是否成对匹配），index（当前元素下标），全局变量是 parenthesisPathRes（用于保存所有有效的括号组合），parenthesisPath（当前括号组合），。
     *         2️⃣backtracking(symbol, index) 函数代表的含义是：递归根据 symbol，在 ( 和 ) 中选择第 index 个元素。
     *
     *     二、书写回溯函数主体（给出选择元素、递归搜索、撤销选择部分）。
     *         从当前正在考虑元素，到第 2×n 个元素为止，枚举出所有可选的元素。对于每一个可选元素：
     *         1️⃣约束条件：symbol < n 或者 symbol > 0。
     *         2️⃣选择元素：将其添加到当前括号组合 parenthesisPath 中。
     *         3️⃣递归搜索：在选择该元素的情况下，继续递归选择剩下元素。
     *         4️⃣撤销选择：将该元素从当前括号组合 parenthesisPath 中移除。
     *
     *         if symbol < n:
     *            parenthesisPath.append('(')
     *            backtrack(symbol + 1, index + 1)
     *            parenthesisPath.pop()
     *         if symbol > 0:
     *            parenthesisPath.append(')')
     *            backtrack(symbol - 1, index + 1)
     *            parenthesisPath.pop()
     *     三、明确递归终止条件（给出递归终止条件，以及递归终止时的处理方法）。
     *         1️⃣当遍历到决策树的叶子节点时，就终止了。也就是当 index == 2 * n 时，递归停止。
     *         2️⃣并且在 symbol == 0 时，当前组合才是有效的，此时将其加入到最终答案数组中。
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        parenthesisRes.clear();
        parenthesisPath.setLength(0); // 清空 StringBuilder
        backtrackParenthesis(n,0,0);
        return parenthesisRes;
    }
    /**
     * 回溯函数，生成括号
     *
     * @param n       int，总括号对数
     * @param symbol  int，当前括号平衡度（左括号+1，右括号-1）
     * @param index   int，当前生成的字符数量
     */
    private void backtrackParenthesis(int n, int symbol, int index) {
        if (n * 2 == index) {
            //todo 并且在 symbol == 0 时，当前组合才是有效的，此时将其加入到最终答案数组中。
            if (symbol == 0) {
                parenthesisRes.add(parenthesisPath.toString());
            }
            return;
        }
        // 尝试添加左括号
        if (symbol < n) {
            parenthesisPath.append('(');
            backtrackParenthesis(n, symbol + 1, index + 1);
            parenthesisPath.deleteCharAt(parenthesisPath.length() - 1); // 撤销选择
        }
        // symbol> 0 时尝试添加右括号
        if (symbol > 0) {
            parenthesisPath.append(')');
            backtrackParenthesis(n, symbol - 1, index + 1);
            parenthesisPath.deleteCharAt(parenthesisPath.length() - 1); // 撤销选择
        }
    }
}
