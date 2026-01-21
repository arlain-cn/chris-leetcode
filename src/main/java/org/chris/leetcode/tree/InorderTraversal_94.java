package org.chris.leetcode.tree;


import org.chris.leetcode._dto.InorderTraversal_94_TestData;
import org.chris.leetcode._dto.TreeNode;

import java.util.*;

/**
 * 94. 二叉树的中序遍历
 */
public class InorderTraversal_94 {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

    public static void main(String[] args) {
        InorderTraversal_94 inorderTraversal94 = new InorderTraversal_94();
        TreeNode root = InorderTraversal_94_TestData.createCompleteBinaryTree();
        List<Integer> actual = inorderTraversal94.inorderTraversal2(root);
        System.out.println(actual.toString());
    }

    /**
     * 方法三：Morris 中序遍历
     * 思路与算法
     *
     * Morris 遍历算法是另一种遍历二叉树的方法，它能将非递归的中序遍历空间复杂度降为 O(1)。
     *
     * Morris 遍历算法整体步骤如下（假设当前遍历到的节点为 x）：
     *
     * 1. 如果 x 无左孩子，先将 x 的值加入答案数组，再访问 x 的右孩子，即 x=x.right。
     * 2. 如果 x 有左孩子，则找到 x 左子树上最右的节点（即左子树中序遍历的最后一个节点，x 在中序遍历中的前驱节点），我们记为 predecessor。根据 predecessor 的右孩子是否为空，进行如下操作。
     *      如果 predecessor 的右孩子为空，则将其右孩子指向 x，然后访问 x 的左孩子，即 x=x.left。
     *      如果 predecessor 的右孩子不为空，则此时其右孩子指向 x，说明我们已经遍历完 x 的左子树，我们将 predecessor 的右孩子置空，将 x 的值加入答案数组，然后访问 x 的右孩子，即 x=x.right。
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal_morris(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode predecessor =null;
        while(root != null){
            if(root.left !=null){
                predecessor = root.left;
                while(predecessor.right != null && predecessor.right != root){
                    predecessor = predecessor.right;
                }

                if(predecessor.right == null){
                    predecessor.right=root;
                    root=root.left;
                }else{
                    ans.add(root.val);
                    predecessor.right=null;
                    root=root.right;
                }
            }else{
                ans.add(root.val);
                root = root.right;
            }
        }
        return ans;
    }

    /**
     * 方法二：迭代
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
     * 首先我们需要了解什么是二叉树的中序遍历：按照访问左子树——根节点——右子树的方式遍历这棵树，而在访问左子树或者右子树的时候我们按照同样的方式遍历，直到遍历完整棵树。因此整个遍历过程天然具有递归的性质，我们可以直接用递归函数来模拟这一过程。
     * <p>
     * 定义 inorder(root) 表示当前遍历到 root 节点的答案，那么按照定义，我们只要递归调用 inorder(root.left) 来遍历 root 节点的左子树，然后将 root 节点的值加入答案，再递归调用inorder(root.right) 来遍历 root 节点的右子树即可，递归终止的条件为碰到空节点。
     *
     * @param root
     * @return
     * @see 二叉树的遍历框架
     * void traverse(TreeNode root) {
     * if (root == null) {
     * return;
     * }
     * // 前序位置
     * traverse(root.left);
     * // 中序位置
     * traverse(root.right);
     * // 后序位置
     * }
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        traversal(root, ans);
        return ans;
    }

    private void traversal(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        traversal(root.left, ans);
        ans.add(root.val);
        traversal(root.right, ans);
    }
}
