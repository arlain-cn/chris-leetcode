package org.chris.leetcode;

import org.chris.leetcode.dto.InorderTraversal_94_TestData;
import org.chris.leetcode.dto.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 二叉树中序遍历算法单元测试
 */
public class InorderTraversal_94Test {

    private InorderTraversal_94 solution;

    @BeforeEach
    void setUp() {
        solution = new InorderTraversal_94();
    }

    @Test
    void testEmptyTree() {
        TreeNode root = InorderTraversal_94_TestData.createEmptyTree();
        List<Integer> expected = Collections.emptyList();
        List<Integer> actual = solution.inorderTraversal(root);
        assertEquals(expected, actual, "空树的中序遍历应该返回空列表");
    }

    @Test
    void testSingleNodeTree() {
        TreeNode root = InorderTraversal_94_TestData.createSingleNodeTree();
        List<Integer> expected = Arrays.asList(1);
        List<Integer> actual = solution.inorderTraversal(root);
        assertEquals(expected, actual, "单节点树的中序遍历应该返回包含该节点值的列表");
    }

    @Test
    void testRightLinearTree() {
        TreeNode root = InorderTraversal_94_TestData.createRightLinearTree();
        List<Integer> expected = Arrays.asList(1, 2, 3);
        List<Integer> actual = solution.inorderTraversal(root);
        assertEquals(expected, actual, "只有右子树的线性树中序遍历结果应该是[1, 2, 3]");
    }

    @Test
    void testLeftLinearTree() {
        TreeNode root = InorderTraversal_94_TestData.createLeftLinearTree();
        List<Integer> expected = Arrays.asList(1, 2, 3);
        List<Integer> actual = solution.inorderTraversal(root);
        assertEquals(expected, actual, "只有左子树的线性树中序遍历结果应该是[1, 2, 3]");
    }

    @Test
    void testCompleteBinaryTree() {
        TreeNode root = InorderTraversal_94_TestData.createCompleteBinaryTree();
        List<Integer> expected = Arrays.asList(4, 2, 5, 1, 6, 3);
        List<Integer> actual = solution.inorderTraversal(root);
        assertEquals(expected, actual, "完全二叉树的中序遍历结果应该是[4, 2, 5, 1, 6, 3]");
    }

    @Test
    void testUnbalancedTree() {
        TreeNode root = InorderTraversal_94_TestData.createUnbalancedTree();
        List<Integer> expected = Arrays.asList(7, 4, 2, 1, 8, 5, 3, 6);
        List<Integer> actual = solution.inorderTraversal(root);
        assertEquals(expected, actual, "不平衡树的中序遍历结果应该是[7, 4, 2, 1, 8, 5, 3, 6]");
    }

    @Test
    void testComplexTree() {
        TreeNode root = InorderTraversal_94_TestData.createComplexTree();
        List<Integer> expected = Arrays.asList(4, 2, 7, 5, 1, 3, 8, 6, 9);
        List<Integer> actual = solution.inorderTraversal(root);
        assertEquals(expected, actual, "复杂树的中序遍历结果应该是[4, 2, 7, 5, 1, 3, 8, 6, 9]");
    }
}