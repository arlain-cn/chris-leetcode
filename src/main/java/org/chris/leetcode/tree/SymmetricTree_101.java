package org.chris.leetcode.tree;

import org.chris.leetcode.dto.TreeNode;

public class SymmetricTree_101 {

    /**
     * 判断二叉树是否对称
     * @param root 二叉树根节点
     * @return 是否对称
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    /**
     * 辅助函数：判断两个子树是否互为镜像
     * @param left 左子树
     * @param right 右子树
     * @return 是否互为镜像
     */
    private boolean isMirror(TreeNode left, TreeNode right) {
        // 如果两个节点都为空，则对称
        if (left == null && right == null) {
            return true;
        }

        // 如果只有一个节点为空，则不对称
        if (left == null || right == null) {
            return false;
        }

        // 两个节点值相等，且子树互为镜像才对称
        return (left.val == right.val)
                && isMirror(left.left, right.right)
                && isMirror(left.right, right.left);
    }
}