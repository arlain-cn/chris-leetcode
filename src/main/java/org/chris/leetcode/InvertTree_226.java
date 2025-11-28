package org.chris.leetcode;

import org.chris.leetcode.dto.InorderTraversal_94_TestData;
import org.chris.leetcode.dto.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class InvertTree_226 {

    public static void main(String[] args) {
        InvertTree_226 invertTree226 = new InvertTree_226();
        TreeNode root = InorderTraversal_94_TestData.createCompleteBinaryTree();
        invertTree226.invertTreeDfs(root);


        LevelOrder_100 levelOrder100 = new LevelOrder_100();
        System.out.println(levelOrder100.levelOrderFac(root));
    }

    /**
     * 深度优先搜索，迭代法遍历
     *
     * @param root
     * @return
     */
    public TreeNode invertTreeDfs(TreeNode root) {
        if (root == null) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                while (root.left != null) {
                    stack.push(root.left);
                    root = root.left;
                }
            }
            TreeNode node = stack.pop();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            //由于stack的先进先出特性，最后一个pop的还是原 root
            root = node.right;
        }

        return root;
    }

    /**
     * 广度优先搜索，层序遍历
     *
     * @param root
     * @return
     */
    public TreeNode invertTreeBfs(TreeNode root) {
        if (root == null) {
            return root;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public TreeNode invertTreeFac(TreeNode root) {
        invertTree_Fac(root);
        return root;
    }

    public void invertTree_Fac(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        invertTree_Fac(root.left);
        invertTree_Fac(root.right);
    }


    /**
     * 官方递归写法
     *
     * @param root
     * @return
     */
    public TreeNode invertTreeFac2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTreeFac2(root.left);
        TreeNode right = invertTreeFac2(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

}
