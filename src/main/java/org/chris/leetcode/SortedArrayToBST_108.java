package org.chris.leetcode;

import org.chris.leetcode.dto.TreeNode;

public class SortedArrayToBST_108 {

    public static void main(String[] args) {
        SortedArrayToBST_108 sortedArrayToBST108 = new SortedArrayToBST_108();
        TreeNode root = sortedArrayToBST108.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});

        System.out.println(new LevelOrder_100().levelOrderFac(root));
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }


    //直观地看，我们可以选择中间数字作为二叉搜索树的根节点，这样分给左右子树的数字个数相同或只相差 1，可以使得树保持平衡。如果数组长度是奇数，则根节点的选择是唯一的，如果数组长度是偶数，则可以选择中间位置左边的数字作为根节点或者选择中间位置右边的数字作为根节点，选择不同的数字作为根节点则创建的平衡二叉搜索树也是不同的。
    //确定平衡二叉搜索树的根节点之后，其余的数字分别位于平衡二叉搜索树的左子树和右子树中，左子树和右子树分别也是平衡二叉搜索树，因此可以通过递归的方式创建平衡二叉搜索树。
    //本题的核心在于根节点位置的选择
    public TreeNode dfs(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        //根节点位于中间左边（偶数个数时）
        //int mid =(left+right)/2;
        //根节点位于中间右边（偶数个数时）
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs(nums, left, mid - 1);
        root.right = dfs(nums, mid + 1, right);
        return root;
    }

}
