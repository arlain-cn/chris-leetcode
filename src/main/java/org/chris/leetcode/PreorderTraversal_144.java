package org.chris.leetcode;

import org.chris.leetcode.dto.InorderTraversal_94_TestData;
import org.chris.leetcode.dto.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PreorderTraversal_144 {


    public static void main(String[] args) {
        PreorderTraversal_144 preorderTraversal144 = new PreorderTraversal_144();
        TreeNode root = InorderTraversal_94_TestData.createCompleteBinaryTree();
        System.out.println(preorderTraversal144.preorderTraversal(root));
    }

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


    public List<Integer> preorderTraversalFac(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorderFac(root, res);
        return res;
    }

    public void preorderFac(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorderFac(root.left, res);
        preorderFac(root.right, res);
    }
}
