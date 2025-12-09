package org.chris.leetcode;

import org.chris.leetcode.dto.InorderTraversal_94_TestData;
import org.chris.leetcode.dto.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 有效 二叉搜索树定义如下：
 * <p>
 * 节点的左子树只包含 严格小于 当前节点的数。
 * 节点的右子树只包含 严格大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class IsValidBST_98 {
    public static void main(String[] args) {
        TreeNode root = InorderTraversal_94_TestData.createBSTFromSortedArray(new int[]{-10, -3, 0, 5, 9});


        IsValidBST_98 isValidBST98 = new IsValidBST_98();
        System.out.println(isValidBST98.isValidBST2(root));
    }


    /**
     * 这启示我们设计一个递归函数 helper(root, lower, upper) 来递归判断，函数表示考虑以 root 为根的子树，判断子树中所有节点的值是否都在 (l,r) 的范围内（注意是开区间）。如果 root 节点的值 val 不在 (l,r) 的范围内说明不满足条件直接返回，否则我们要继续递归调用检查它的左右子树是否满足，如果都满足才说明这是一棵二叉搜索树。
     * <p>
     * 那么根据二叉搜索树的性质，在递归调用左子树时，我们需要把上界 upper 改为 root.val，即调用 helper(root.left, lower, root.val)，因为左子树里所有节点的值均小于它的根节点的值。同理递归调用右子树时，我们需要把下界 lower 改为 root.val，即调用 helper(root.right, root.val, upper)。
     *
     */
    public boolean isValidBST2(TreeNode root) {
        return isValidBSTFac2(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBSTFac2(TreeNode root, long lower, long upper) {
        if (root == null) {
            return true;
        }
        if (root.val <= lower || root.val >= upper) {
            return false;
        }

        return isValidBSTFac2(root.left, lower, root.val) && isValidBSTFac2(root.right, root.val, upper);
    }

    private long prevVal = Long.MIN_VALUE;

    public boolean isValidBSTFac(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (!isValidBSTFac(root.left)) {
            return false;
        }
        int val = root.val;
        if (val <= prevVal) {
            return false;
        }
        prevVal = val;
        if (!isValidBSTFac(root.right)) {
            return false;
        }
        return true;
    }

    /**
     * 中序遍历时，判断当前节点是否大于中序遍历的前一个节点，如果大于，说明满足 BST，继续遍历；否则直接返回 false。
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        Integer prevVal = null;
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            int val = root.val;
            if (prevVal == null) {
                prevVal = val;
            } else if (val <= prevVal) {
                return false;
            }
            prevVal = val;
            root = root.right;
        }
        return true;
    }
}
