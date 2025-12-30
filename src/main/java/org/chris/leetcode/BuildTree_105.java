package org.chris.leetcode;

import org.chris.leetcode.dto.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BuildTree_105 {

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        BuildTree_105 buildTree105 = new BuildTree_105();
        TreeNode res = buildTree105.buildTree(preorder, inorder);
        System.out.println(res);
    }

    /**
     * 方法一：递归（优化）
     * 优化1:在中序遍历中对根节点进行定位时，一种简单的方法是直接扫描整个中序遍历的结果并找出根节点，但这样做的时间复杂度较高。我们可以考虑使用哈希表来帮助我们快速地定位根节点。对于哈希映射中的每个键值对，键表示一个元素（节点的值），值表示其在中序遍历中的出现位置。在构造二叉树的过程之前，我们可以对中序遍历的列表进行一遍扫描，就可以构造出这个哈希映射。在此后构造二叉树的过程中，我们就只需要 O(1) 的时间对根节点进行定位了。
     * 优化2:避免数组拷贝
     *
     * @param preorder
     * @param inorder
     * @return
     */
    private Map<Integer, Integer> inOrderIndexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        inOrderIndexMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            inOrderIndexMap.put(inorder[i], i);
        }
        return buildTreeFac(preorder, inorder, 0, len - 1, 0, len - 1);
    }

    public TreeNode buildTreeFac(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            //这个判断是递归的终止条件：当 preorder_left > preorder_right 时，表示当前子树在先序序列中没有有效元素（即子树为空），应返回 null。
            //思考关键点：边界如何产生？例如，中序序列中根节点在开头（左子树为空），此时左子树的先序边界 preorder_left 会大于 preorder_right。
            return null;
        }
        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = inOrderIndexMap.get(preorder[preorder_root]);
        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = buildTreeFac(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = buildTreeFac(preorder, inorder, preorder_root + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    /**
     * 方法一：递归
     * LeetCode 105题的关键在于利用先序和中序遍历的特性。思考以下问题：
     * <p>
     * 根节点定位：先序遍历的第一个元素是整棵树的根节点
     * 子树划分：在中序遍历中，根节点将序列分为左子树和右子树，如何确定左子树的大小？（提示：中序中根节点的索引）
     * 序列提取：如何从先序序列中提取左子树和右子树的子序列？（提示：基于左子树大小）
     * 递归边界：当子序列为空时，如何处理？
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }

        int len = preorder.length;
        TreeNode root = new TreeNode(preorder[0]);
        int leftNum = 0;
        for (int i = 0; i < len; i++) {
            if (root.val == inorder[i]) {
                leftNum = i;
                break;
            }
        }
        int[] leftInorderSubTree = Arrays.copyOfRange(inorder, 0, leftNum);
        int[] rightInorderSubTree = Arrays.copyOfRange(inorder, leftNum + 1, len);

        int[] leftPreOrderSubTree = Arrays.copyOfRange(preorder, 1, leftNum + 1);
        int[] rightPreOrderSubTree = Arrays.copyOfRange(preorder, leftNum + 1, len);

        root.left = buildTree(leftPreOrderSubTree, leftInorderSubTree);
        root.right = buildTree(rightPreOrderSubTree, rightInorderSubTree);
        return root;
    }
}
