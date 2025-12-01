# 二叉树中序遍历测试数据说明

本项目为LeetCode第94题"二叉树的中序遍历"提供了丰富的测试数据，帮助验证算法实现的正确性。

## 测试数据类型

1. **空树**: `createEmptyTree()` - 测试边界条件
2. **单节点树**: `createSingleNodeTree()` - 最简单的情况
3. **右线性树**: `createRightLinearTree()` - 只有右子树的链式结构
4. **左线性树**: `createLeftLinearTree()` - 只有左子树的链式结构
5. **完全二叉树**: `createCompleteBinaryTree()` - 标准的完全二叉树结构
6. **不平衡树**: `createUnbalancedTree()` - 左右子树深度差异较大的树
7. **复杂树**: `createComplexTree()` - 包含多种结构特点的复合树

## 使用方法

在测试类中直接调用相应的静态方法获取测试数据：

```java
@Test
void testCompleteBinaryTree() {
    TreeNode root = InorderTraversal_94_TestData.createCompleteBinaryTree();
    List<Integer> expected = Arrays.asList(4, 2, 5, 1, 6, 3);
    List<Integer> actual = solution.inorderTraversal(root);
    assertEquals(expected, actual);
}
```

## 中序遍历预期结果

- 空树: []
- 单节点树(1): [1]
- 右线性树(1->2->3): [1, 2, 3]
- 左线性树(3<-2<-1): [1, 2, 3]
- 完全二叉树(1(2(4,5),3(6))): [4, 2, 5, 1, 6, 3]
- 不平衡树: [7, 4, 2, 1, 8, 5, 3, 6]
- 复杂树: [4, 2, 7, 5, 1, 3, 8, 6, 9]