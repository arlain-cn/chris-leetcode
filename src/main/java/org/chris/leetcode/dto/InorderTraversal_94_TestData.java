package org.chris.leetcode.dto;

import org.chris.leetcode.LevelOrder_100;

import java.util.List;

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
     * \
     * 2
     * \
     * 3
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
     * /
     * 2
     * /
     * 1
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
     * /   \
     * 2     3
     * / \   /
     * 4   5 6
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
     * /   \
     * 2     3
     * /     / \
     * 4     5   6
     * /     /
     * 7     8
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
     * /   \
     * 2     3
     * / \     \
     * 4   5     6
     * /     / \
     * 7     8   9
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

    /**
     * 根据有序数组创建平衡二叉搜索树
     *
     * @param nums 有序数组
     * @return 二叉搜索树的根节点
     */
    public static TreeNode createBSTFromSortedArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return buildBST(nums, 0, nums.length - 1);
    }

    /**
     * 递归构建二叉搜索树
     *
     * @param nums  有序数组
     * @param left  左边界
     * @param right 右边界
     * @return 以当前范围构建的子树根节点
     */
    private static TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 选择中间元素作为根节点，保证树的平衡性
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        // 递归构建左右子树
        root.left = buildBST(nums, left, mid - 1);
        root.right = buildBST(nums, mid + 1, right);

        return root;
    }

    /**
     * 通过逐个插入值创建二叉搜索树
     *
     * @param values 要插入的值数组
     * @return 二叉搜索树的根节点
     */
    public static TreeNode createBSTFromValues(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }

        TreeNode root = null;
        for (int val : values) {
            root = insertIntoBST(root, val);
        }
        return root;
    }

    /**
     * 向二叉搜索树中插入值
     *
     * @param root 树的根节点
     * @param val  要插入的值
     * @return 插入后的树根节点
     */
    private static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }

    /**
     * 创建预定义的二叉搜索树
     * 结构:       4
     * /   \
     * 2     6
     * / \   / \
     * 1   3 5   7
     * 中序遍历结果: [1, 2, 3, 4, 5, 6, 7]
     *
     * @return 二叉搜索树的根节点
     */
    public static TreeNode createPredefinedBST() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        return root;
    }

    /**
     * 构建 BST
     *
     * @param nums
     * @return
     */
    public static TreeNode createPredefinedBST2(Integer[] nums) {
        TreeNode root = new TreeNode(nums[0]);
        createPredefinedBSTFac2(root, nums, 0);
        return root;
    }

    public static void createPredefinedBSTFac2(TreeNode root, Integer[] nums, int n) {
        if (2 * n >= nums.length) {
            return;
        }
        int lefti = 2 * n + 1;
        if (lefti < nums.length && nums[lefti] != null) {
            root.left = new TreeNode(nums[lefti]);
            createPredefinedBSTFac2(root.left, nums, lefti);
        }
        int righti = 2 * n + 2;
        if (righti < nums.length && nums[righti] != null) {
            root.right = new TreeNode(nums[righti]);
            createPredefinedBSTFac2(root.right, nums, righti);
        }
    }

    public static void main(String[] args) {
        TreeNode root = createPredefinedBST2(new Integer[]{5, 3, 6, 2, 4, null, null, 1});
        LevelOrder_100 levelOrder100 = new LevelOrder_100();
        List<List<Integer>> actual = levelOrder100.levelOrderFac(root);
        System.out.println(actual.toString());
    }
}