package org.chris.leetcode;

import org.chris.leetcode.dto.InorderTraversal_94_TestData;
import org.chris.leetcode.dto.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PostorderTraversal_145 {

    public static void main(String[] args) {
        PostorderTraversal_145 postorderTraversal145 = new PostorderTraversal_145();
        TreeNode root = InorderTraversal_94_TestData.createCompleteBinaryTree();
        System.out.println(postorderTraversal145.postorderTraversal(root));
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


    public List<Integer> postorderTraversalFac(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            postorderFac(root, res);
            return res;
    }


    public void postorderFac(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postorderFac(root.left, res);
        postorderFac(root.right, res);
        res.add(root.val);
    }
}
