package org.chris.leetcode.Collection;

import org.chris.leetcode.dto.InorderTraversal_94_TestData;
import org.chris.leetcode.dto.TreeNode;

import java.util.*;

public class TreeFactorialPreMidPostTemplate {

    /**
     * 二叉树的前中后序，递归DFS典型写法
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeFactorialPreMidPostTemplate template = new TreeFactorialPreMidPostTemplate();
        TreeNode root = InorderTraversal_94_TestData.createCompleteBinaryTree();
        List<Integer> ans = new ArrayList<>();
        template.factorial_pre(root, ans);
        System.out.println(ans);
        ans.clear();
        template.factorial_mid(root, ans);
        System.out.println(ans);
        ans.clear();
        template.factorial_post(root, ans);
        System.out.println(ans);

        ans.clear();
        template.factorial_level(root, ans);
        System.out.println(ans);
        ans.clear();
        System.out.println(template.levelOrder(root));
    }

    /**
     * 二叉树的前序，递归DFS典型写法
     *
     * @param root
     * @param ans
     */
    public void factorial_pre(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        ans.add(root.val);
        factorial_pre(root.left, ans);
        factorial_pre(root.right, ans);
    }

    /**
     * 二叉树的前序，迭代
     *
     * @param root
     * @param root
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return res;
    }

    /**
     * 二叉树的中序，递归DFS典型写法
     *
     * @param root
     * @param ans
     */
    public void factorial_mid(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        factorial_mid(root.left, ans);
        ans.add(root.val);
        factorial_mid(root.right, ans);
    }

    /**
     * 二叉树的中序：迭代
     * 思路与算法
     * <p>
     * 方法一的递归函数我们也可以用迭代的方式实现，两种方式是等价的，区别在于递归的时候隐式地维护了一个栈，而我们在迭代的时候需要显式地将这个栈模拟出来，其他都相同，具体实现可以看下面的代码。
     * <p>
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            TreeNode node = stack.pop();
            ans.add(node.val);
            root = node.right;
        }
        return ans;
    }

    /**
     * 二叉树的后序，递归DFS典型写法
     *
     * @param root
     * @param ans
     */
    public void factorial_post(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        factorial_post(root.left, ans);
        factorial_post(root.right, ans);
        ans.add(root.val);
    }

    /**
     * 一、后序遍历的时候，如果当前节点没有右节点或者右节点已处理，则处理当前节点，把 prev 指向当前，root 清空（因为后序遍历，当前节点最后处理了，掌握的下一层信息已经处理过了，不掌握上一层的信息，）
     * 二、前序遍历需要把当前处理的root放入stack，再将root赋值为处理的root.right
     *
     * @param root
     * @return
     */

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }


    /**
     * 层序遍历-迭代
     *
     * @param root
     * @param ans
     */
    public void factorial_level(TreeNode root, List<Integer> ans) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            ans.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }


    /**
     * 层序遍历（带高度）-迭代
     *
     * @return
     */
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
