package org.chris.leetcode.tree;

import org.chris.leetcode._dto.SymmetricTree_101_TestData;
import org.chris.leetcode._dto.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class IsSymmetric_101a {

    /**
     * 对称二叉树（LeetCode 101）问题要求判断给定的二叉树是否是轴对称的。
     * 根据我对该问题的理解，一个二叉树是对称的，当且仅当它的左子树和右子树互为镜像。具体来说：
     * 1. 根节点的值相等
     * 2. 左子树的左子树与右子树的右子树互为镜像
     * 3. 左子树的右子树与右子树的左子树互为镜像
     * 现在让我分析解题思路。
     * <p>
     * ⏺ 基于对称二叉树问题的要求，我可以提供以下解决方案：
     * 解题思路
     * 有两种主要方法可以解决这个问题：
     * <p>
     * <p>
     * <p>
     *
     * @param args
     */
    public static void main(String[] args) {
        IsSymmetric_101a isSymmetric101a = new IsSymmetric_101a();

        //构建对称二叉树
        TreeNode root = SymmetricTree_101_TestData.createSymmetricTree();
        System.out.println(isSymmetric101a.isSymmetric(root));
        //构建非对称二叉树
        root = SymmetricTree_101_TestData.createAsymmetricTree();
        System.out.println(isSymmetric101a.isSymmetric(root));
        //构建单节点二叉树
        root = SymmetricTree_101_TestData.createSingleNodeTree();
        System.out.println(isSymmetric101a.isSymmetric(root));
        //构建空树
        root = SymmetricTree_101_TestData.createEmptyTree();
        System.out.println(isSymmetric101a.isSymmetric(root));
    }

    /**
     * 方法二：迭代法
     * 使用队列或栈来模拟递归过程：
     * 1. 将根节点的左右子节点加入队列
     * 2. 每次从队列中取出两个节点进行比较
     * 3. 如果两个节点都为空则继续，如果只有一个为空或者值不相等则返回false
     * 4. 将第一个节点的左子节点与第二个节点的右子节点入队，将第一个节点的右子节点与第二个节点的左子节点入队
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root.left);
        stack.push(root.right);
        while (!stack.isEmpty()) {
            TreeNode left = stack.pop();
            TreeNode right = stack.pop();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            stack.push(left.left);
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);
        }
        return true;
    }

    /**
     * 方法一：递归法
     * 1. 创建一个辅助函数来比较两个子树是否互为镜像
     * 2. 镜像的条件：
     * - 两个节点都为空，则对称
     * - 一个节点为空而另一个不为空，则不对称
     * - 两个节点值相等，且第一个节点的左子树与第二个节点的右子树对称，第一个节点的右子树与第二个节点的左子树对称
     *
     * @param root
     * @return
     */
    public boolean isSymmetricFac(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }

        return left.val == right.val && isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }
}
