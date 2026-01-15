package org.chris.leetcode;

import org.chris.leetcode.dto.TreeConstructUtils;
import org.chris.leetcode.dto.TreeNode;

public class TreeConstructTest {
    public static void main(String[] args) {
        // 示例：从数组 [10,5,-3,3,2,null,11,3,-2,null,1] 构造树
        Integer[] arr = {10, 5, -3, 3, 2, null, 11, 3, -2, null, 1};

        System.out.println("使用 TreeConstructUtils 工具类构造二叉树:");
        System.out.println("输入数组: " + java.util.Arrays.toString(arr));

        TreeNode root = TreeConstructUtils.constructTree(arr);

        System.out.println("构造完成！树的层序遍历结果:");
        TreeConstructUtils.levelorderPrint(root);

        // 也可以直接从字符串构造
        String treeStr = "[10,5,-3,3,2,null,11,3,-2,null,1]";
        TreeNode root2 = TreeConstructUtils.constructTreeFromString(treeStr);

        System.out.println("\n从字符串构造的树: " + treeStr);
        TreeConstructUtils.levelorderPrint(root2);
    }
}