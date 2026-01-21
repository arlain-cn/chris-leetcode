package org.chris.leetcode.tree;

import org.chris.leetcode.dto.InorderTraversal_94_TestData;
import org.chris.leetcode.dto.TreeNode;

import java.util.*;

public class LevelOrder_100 {

    public static void main(String[] args) {
        LevelOrder_100 levelOrder100 = new LevelOrder_100();
        TreeNode root = InorderTraversal_94_TestData.createCompleteBinaryTree();
        List<List<Integer>> actual = levelOrder100.levelOrderFac(root);
        System.out.println(actual.toString());

    }

    public List<List<Integer>> levelOrderFac(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<List<Integer>> ans = new ArrayList<>();
        levelOrder_factorial(root, ans, 1);
        return ans;
    }

    /**
     * @see 一个新思路,递归的每一层可以传递层数
     * @param root
     * @param ans
     * @param level
     */
    public void levelOrder_factorial(TreeNode root, List<List<Integer>> ans, int level) {
        if (root == null) {
            return;
        }
        if (ans.size() < level) {
            List<Integer> levelElements = new ArrayList<>();
            ans.add(levelElements);
        }
        ans.get(level - 1).add(root.val);
        levelOrder_factorial(root.left, ans, level + 1);
        levelOrder_factorial(root.right, ans, level + 1);
    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<List<Integer>> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> la = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                la.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans.add(la);
        }
        return ans;
    }
}
