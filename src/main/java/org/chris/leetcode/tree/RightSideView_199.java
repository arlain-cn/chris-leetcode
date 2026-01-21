package org.chris.leetcode.tree;

import org.chris.leetcode._dto.InorderTraversal_94_TestData;
import org.chris.leetcode._dto.TreeNode;

import java.util.*;

public class RightSideView_199 {

    /**
     * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     *
     * @param args
     */
    public static void main(String[] args) {
        RightSideView_199 rightSideView199 = new RightSideView_199();
        TreeNode root = InorderTraversal_94_TestData.createStdPredefinedBST2(new Integer[]{5, 3, 6, 2, 4, null, null, 1});
        System.out.println(rightSideView199.rightSideViewBfs(root));
        System.out.println(rightSideView199.rightSideView(root));
        System.out.println(rightSideView199.levalBsf(root));

    }

    /**
     * 方法一：深度优先搜索
     * 思路
     * <p>
     * 我们对树进行深度优先搜索，在搜索过程中，我们总是先访问右子树。那么对于每一层来说，我们在这层见到的第一个结点一定是最右边的结点。
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        //depth node MAP
        Map<Integer, TreeNode> depthNodeMap = new HashMap<>();
        Deque<TreeNode> stack = new LinkedList<>();
        Deque<Integer> depthStack = new LinkedList<>();

        stack.push(root);
        depthStack.push(0);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            Integer depth = depthStack.pop();
            if (!depthNodeMap.containsKey(depth)) {
                depthNodeMap.put(depth, node);
            }
            if (node.left != null) {
                stack.push(node.left);
                depthStack.push(depth + 1);
            }
            if (node.right != null) {
                stack.push(node.right);
                depthStack.push(depth + 1);
            }
        }

        for (int i = 0; i < depthNodeMap.size(); i++) {
            res.add(depthNodeMap.get(i).val);
        }
        return res;
    }


    /**
     * 从右侧面看，每层可见的节点是哪一层的最右边节点？（例如，树 [1,2,3,null,5,null,4] 的右视图为 [1,3,4]）
     * 如何通过遍历顺序确保每层只取最右节点？（提示：层序遍历BFS中，每层最后一个节点即为最右节点）
     * 用小例子手动模拟BFS过程：从根开始，每层记录最后一个访问的节点。
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideViewBfs(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode levelFinal = null;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                levelFinal = node;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans.add(levelFinal.val);

        }
        return ans;
    }

    public List<List<Integer>> levalBsf(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> le = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                le.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans.add(le);
        }
        return ans;
    }

}
