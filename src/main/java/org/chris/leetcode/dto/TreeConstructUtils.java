package org.chris.leetcode.dto;

import java.util.*;

public class TreeConstructUtils {

    /**
     * 从数组构造二叉树
     * 数组格式如：[10,5,-3,3,2,null,11,3,-2,null,1]
     * 使用层序遍历的方式构建树
     *
     * @param arr 包含树节点值的数组，null 表示空节点
     * @return 构造的二叉树根节点
     */
    public static TreeNode constructTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode current = queue.poll();

            // 处理左子节点
            if (i < arr.length && arr[i] != null) {
                current.left = new TreeNode(arr[i]);
                queue.offer(current.left);
            }
            i++;

            // 处理右子节点
            if (i < arr.length && arr[i] != null) {
                current.right = new TreeNode(arr[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }

    /**
     * 从字符串构造二叉树
     * 字符串格式如："10,5,-3,3,2,null,11,3,-2,null,1" 或 "[10,5,-3,3,2,null,11,3,-2,null,1]"
     *
     * @param str 表示树节点值的字符串
     * @return 构造的二叉树根节点
     */
    public static TreeNode constructTreeFromString(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }

        // 去掉方括号
        if (str.startsWith("[") && str.endsWith("]")) {
            str = str.substring(1, str.length() - 1);
        }

        String[] parts = str.split(",");
        Integer[] arr = new Integer[parts.length];

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i].trim();
            if (part.equals("null")) {
                arr[i] = null;
            } else {
                try {
                    arr[i] = Integer.parseInt(part);
                } catch (NumberFormatException e) {
                    arr[i] = null;
                }
            }
        }

        return constructTree(arr);
    }

    /**
     * 将二叉树转换为数组格式（层序遍历）
     * 用于验证构建的树是否正确
     *
     * @param root 二叉树根节点
     * @return 层序遍历的数组表示
     */
    public static Integer[] treeToArray(TreeNode root) {
        if (root == null) {
            return new Integer[0];
        }

        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean hasNonNull = true; // 用于跟踪是否还有非空节点
        while (!queue.isEmpty() && hasNonNull) {
            int size = queue.size();
            hasNonNull = false;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (node == null) {
                    result.add(null);
                    queue.offer(null);
                    queue.offer(null);
                } else {
                    result.add(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);

                    if (node.left != null || node.right != null) {
                        hasNonNull = true;
                    }
                }
            }
        }

        // 移除末尾的 null 值
        while (result.size() > 0 && result.get(result.size() - 1) == null) {
            result.remove(result.size() - 1);
        }

        return result.toArray(new Integer[0]);
    }

    /**
     * 前序遍历打印树（用于调试）
     *
     * @param root 二叉树根节点
     */
    public static void preorderPrint(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }

        System.out.print(root.val + " ");
        preorderPrint(root.left);
        preorderPrint(root.right);
    }

    /**
     * 中序遍历打印树（用于调试）
     *
     * @param root 二叉树根节点
     */
    public static void inorderPrint(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }

        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    /**
     * 后序遍历打印树（用于调试）
     *
     * @param root 二叉树根节点
     */
    public static void postorderPrint(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }

        postorderPrint(root.left);
        postorderPrint(root.right);
        System.out.print(root.val + " ");
    }

    /**
     * 层序遍历打印树（用于调试）
     *
     * @param root 二叉树根节点
     */
    public static void levelorderPrint(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    System.out.print(node.val + " ");
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    System.out.print("null ");
                }
            }
            System.out.println(); // 换行表示一层结束
        }
    }

    // 测试方法
    public static void main(String[] args) {
        // 测试用例 1: [10,5,-3,3,2,null,11,3,-2,null,1]
        Integer[] arr1 = {10, 5, -3, 3, 2, null, 11, 3, -2, null, 1};
        TreeNode root1 = constructTree(arr1);

        System.out.println("原始数组: " + Arrays.toString(arr1));
        System.out.println("构建的树（层序遍历）:");
        levelorderPrint(root1);

        Integer[] result1 = treeToArray(root1);
        System.out.println("重构数组: " + Arrays.toString(result1));
        System.out.println("数组匹配: " + Arrays.equals(arr1, result1));
        System.out.println();

        // 测试字符串构造方法
        String str1 = "[10,5,-3,3,2,null,11,3,-2,null,1]";
        TreeNode rootFromStr = constructTreeFromString(str1);
        System.out.println("从字符串 '" + str1 + "' 构造的树:");
        levelorderPrint(rootFromStr);

        // 测试用例 2: [3,9,20,null,null,15,7]
        Integer[] arr2 = {3, 9, 20, null, null, 15, 7};
        TreeNode root2 = constructTree(arr2);

        System.out.println("\n原始数组: " + Arrays.toString(arr2));
        System.out.println("构建的树（层序遍历）:");
        levelorderPrint(root2);

        Integer[] result2 = treeToArray(root2);
        System.out.println("重构数组: " + Arrays.toString(result2));
        System.out.println();

        // 测试用例 3: 单节点树
        Integer[] arr3 = {1};
        TreeNode root3 = constructTree(arr3);

        System.out.println("原始数组: " + Arrays.toString(arr3));
        System.out.println("构建的树（层序遍历）:");
        levelorderPrint(root3);
    }
}