package org.chris.leetcode.tree;

import org.chris.leetcode._dto.TreeNode;

public class DiameterOfBinaryTree_543 {


    public static void main(String[] args) {
        // 构建二叉树:
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        DiameterOfBinaryTree_543 diameterOfBinaryTree543 = new DiameterOfBinaryTree_543();
        System.out.println(diameterOfBinaryTree543.diameterOfBinaryTree(root));
    }

    /**
     * 非典型递归法
     **/
    public int diameterOfBinaryTree(TreeNode root) {
        return dfs(root).diameter;
    }

    public TreeInfo dfs(TreeNode root) {
        if (root == null) {
            return new TreeInfo(0, 0);
        }

        TreeInfo left = dfs(root.left);
        TreeInfo right = dfs(root.right);
        //左右子树高度取max再+1才是root高度
        int rootHeight = Math.max(left.height, right.height) + 1;
        //left.height+1+right.height+1-2 效果等同
        int rootDiameter = left.height + right.height;
        //重点在与上一行算出来的是经过root节点的，但是最大直径可能不会经过root
        rootDiameter = Math.max(rootDiameter, Math.max(left.diameter, right.diameter));
        return new TreeInfo(rootHeight, rootDiameter);
    }

    // 定义一个内部类来承载返回信息
    class TreeInfo {
        int height;
        int diameter;

        TreeInfo(int h, int d) {
            height = h;
            diameter = d;
        }
    }

    /**
     * 典型递归法
     **/
    public int ans = 0;

    public int diameterOfBinaryTree2(TreeNode root) {
        depth(root);
        return ans;
    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);

        ans = Math.max(ans, left + right);

        return Math.max(left, right) + 1;
    }
}
