package org.chris.leetcode.dto;

/**
 * 二叉树中序遍历测试数据构造器
 */
public class InorderTraversal_94_TestData {

    /**
     * 创建空树
     * 中序遍历结果: []
     */
    public static TreeNode createEmptyTree() {
        return null;
    }

    /**
     * 创建单节点树
     * 结构: 1
     * 中序遍历结果: [1]
     */
    public static TreeNode createSingleNodeTree() {
        return new TreeNode(1);
    }

    /**
     * 创建只有右子树的线性树
     * 结构: 1
     *        \
     *         2
     *          \
     *           3
     * 中序遍历结果: [1, 2, 3]
     */
    public static TreeNode createRightLinearTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        return root;
    }

    /**
     * 创建只有左子树的线性树
     * 结构:     3
     *          /
     *         2
     *        /
     *       1
     * 中序遍历结果: [1, 2, 3]
     */
    public static TreeNode createLeftLinearTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        return root;
    }

    /**
     * 创建完全二叉树
     * 结构:   1
     *       /   \
     *      2     3
     *     / \   /
     *    4   5 6
     * 中序遍历结果: [4, 2, 5, 1, 6, 3]
     */
    public static TreeNode createCompleteBinaryTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        return root;
    }

    /**
     * 创建不平衡树
     * 结构:       1
     *           /   \
     *          2     3
     *         /     / \
     *        4     5   6
     *       /     /
     *      7     8
     * 中序遍历结果: [7, 4, 2, 1, 8, 5, 3, 6]
     */
    public static TreeNode createUnbalancedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);
        root.right.left.left = new TreeNode(8);
        return root;
    }

    /**
     * 创建复杂树
     * 结构:         1
     *             /   \
     *            2     3
     *           / \     \
     *          4   5     6
     *             /     / \
     *            7     8   9
     * 中序遍历结果: [4, 2, 7, 5, 1, 3, 8, 6, 9]
     */
    public static TreeNode createComplexTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.right.left = new TreeNode(7);
        root.right.right.left = new TreeNode(8);
        root.right.right.right = new TreeNode(9);
        return root;
    }
}