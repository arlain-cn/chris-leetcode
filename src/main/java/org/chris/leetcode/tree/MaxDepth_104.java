package org.chris.leetcode.tree;

import org.chris.leetcode._dto.InorderTraversal_94_TestData;
import org.chris.leetcode._dto.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MaxDepth_104 {


    public static void main(String[] args) {
        MaxDepth_104 maxDepth104 = new MaxDepth_104();
        TreeNode root = InorderTraversal_94_TestData.createCompleteBinaryTree();
        System.out.println(maxDepth104.maxDepth_dfs(root));
    }

    public int maxDepth_dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftH = maxDepth_dfs(root.left);
        int rightH = maxDepth_dfs(root.right);
        return Math.max(leftH, rightH) + 1;
    }

    public int maxDepth_bfs(TreeNode root) {
        if (root == null) return 0;

        int deepth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            deepth++;
            int lc = queue.size();
            for (int i = 0; i < lc; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return deepth;
    }
}
