package org.chris.leetcode.tree;

import org.chris.leetcode._dto.InorderTraversal_94_TestData;
import org.chris.leetcode._dto.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Flatten_114 {

    /**
     * 114. 二叉树展开为链表
     *
     * @param args
     */
    public static void main(String[] args) {
        Flatten_114 flatten114 = new Flatten_114();
        TreeNode root = InorderTraversal_94_TestData.createCompleteBinaryTree();
        flatten114.flatten(root);
        System.out.println(flatten114.preorderTraversal(root));

    }

    /**
     * 方法三：寻找前驱节点
     * 前两种方法都借助前序遍历，前序遍历过程中需要使用栈存储节点。有没有空间复杂度是 O(1) 的做法呢？
     * <p>
     * 注意到前序遍历访问各节点的顺序是根节点、左子树、右子树。如果一个节点的左子节点为空，则该节点不需要进行展开操作。如果一个节点的左子节点不为空，则该节点的左子树中的最后一个节点被访问之后，该节点的右子节点被访问。该节点的左子树中最后一个被访问的节点是左子树中的最右边的节点，也是该节点的前驱节点。因此，问题转化成寻找当前节点的前驱节点。
     * <p>
     * 具体做法是，对于当前节点，如果其左子节点不为空，则在其左子树中找到最右边的节点，作为前驱节点，将当前节点的右子节点赋给前驱节点的右子节点，然后将当前节点的左子节点赋给当前节点的右子节点，并将当前节点的左子节点设为空。对当前节点处理结束后，继续处理链表中的下一个节点，直到所有节点都处理结束。
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        //因为无返回值，方法外还需要root去遍历，不破坏root的关系
        TreeNode curr = root;
        while (curr != null) {
            //对于当前节点，如果其左子节点不为空，
            if (curr.left != null) {
                TreeNode next = curr.right;
                //则在其左子树中找到最右边的节点，作为前驱节点，
                TreeNode predecessor = curr.left;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                //将当前节点的右子节点赋给前驱节点的右子节点，
                predecessor.right = next;
                //然后将当前节点的左子节点赋给当前节点的右子节点，
                curr.right = curr.left;
                //并将当前节点的左子节点设为空。
                curr.left = null;
            }
            curr = curr.right;
        }


    }

    /**
     * 方法二：前序遍历和展开同步进行
     * 使用方法一的前序遍历，由于将节点展开之后会破坏二叉树的结构而丢失子节点的信息，因此前序遍历和展开为单链表分成了两步。能不能在不丢失子节点的信息的情况下，将前序遍历和展开为单链表同时进行？
     * <p>
     * 之所以会在破坏二叉树的结构之后丢失子节点的信息，是因为在对左子树进行遍历时，没有存储右子节点的信息，在遍历完左子树之后才获得右子节点的信息。只要对前序遍历进行修改，在遍历左子树之前就获得左右子节点的信息，并存入栈内，子节点的信息就不会丢失，就可以将前序遍历和展开为单链表同时进行。
     * <p>
     * 该做法不适用于递归实现的前序遍历，只适用于迭代实现的前序遍历。
     *
     * @param root
     */
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (prev != null) {
                //除了root节点，后续节点都会走到这个逻辑，把前序节点的right指向当前节点
                //前序节点的右节点清空，此时prev的节点关系已经访问过了
                prev.left = null;
                prev.right = curr;
            }
            TreeNode right = curr.right;
            //先放右节点，后弹出
            if (right != null) {
                stack.push(right);
            }
            TreeNode left = curr.left;
            //后放右节点，先弹出
            if (left != null) {
                stack.push(left);
            }
            prev = curr;
        }
    }

    /**
     * 方法一：前序遍历
     * root节点在展开的“链表”里仍是头节点，所以不用重新设值
     *
     * @param root
     */
    public void flatten1(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                res.add(curr);
                curr = curr.left;
            }

            TreeNode node = stack.pop();
            curr = node.right;
        }
        root.left = null;
        curr = root;
        for (int i = 1; i < res.size(); i++) {
            TreeNode node = res.get(i);
            node.left = null;
            curr.right = node;
            curr = curr.right;
        }
    }

    /**
     * 遍历打印元素
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                res.add(root.val);
                root = root.left;
            }
            TreeNode node = stack.pop();
            root = node.right;

        }
        return res;
    }
}
