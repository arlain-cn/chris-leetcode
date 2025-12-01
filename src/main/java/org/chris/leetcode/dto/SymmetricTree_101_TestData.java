package org.chris.leetcode.dto;

public class SymmetricTree_101_TestData {

    /**
     * 构建对称二叉树
     *       1
     *      / \
     *     2   2
     *    / \ / \
     *   3  4 4  3
     */
    public static TreeNode createSymmetricTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        return root;
    }

    /**
     * 构建非对称二叉树
     *       1
     *      / \
     *     2   2
     *      \   \
     *       3   3
     */
    public static TreeNode createAsymmetricTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        return root;
    }

    /**
     * 构建单节点二叉树
     */
    public static TreeNode createSingleNodeTree() {
        return new TreeNode(1);
    }

    /**
     * 构建空树
     */
    public static TreeNode createEmptyTree() {
        return null;
    }
}