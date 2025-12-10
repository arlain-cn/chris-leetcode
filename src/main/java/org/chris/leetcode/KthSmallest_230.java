package org.chris.leetcode;

import org.chris.leetcode.dto.InorderTraversal_94_TestData;
import org.chris.leetcode.dto.TreeNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class KthSmallest_230 {

    /**
     * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。
     *
     * @param args
     */
    public static void main(String[] args) {
//        TreeNode root = InorderTraversal_94_TestData.createBSTFromSortedArray(new int[]{5, 3, 6, 2, 4, 1});
//        TreeNode root = InorderTraversal_94_TestData.createBSTFromSortedArray(new int[]{-10, -3, 0, 5, 9});
        TreeNode root = InorderTraversal_94_TestData.createPredefinedBST2(new Integer[]{5, 3, 6, 2, 4, null, null, 1});

        KthSmallest_230 kthSmallest230 = new KthSmallest_230();
        System.out.println(kthSmallest230.kthSmallest3(root, 3));
        kthSmallest230.kthSmallestFac(root, 3);
        System.out.println(kthSmallest230.ans);
    }

    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        int i = 0;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();

            if (++i == k) {
                return root.val;
            }
            root = root.right;
        }
        return 0;
    }

    private int i = 0;
    private int ans = 0;

    public void kthSmallestFac(TreeNode root, int k) {
        if (root == null) return;
        kthSmallestFac(root.left, k);
        if (++i == k) {
            ans = root.val;
            return;
        }
        kthSmallestFac(root.right, k);
    }

    /**
     * 在方法一中，我们之所以需要中序遍历前 k 个元素，是因为我们不知道子树的结点数量，不得不通过遍历子树的方式来获知。
     * <p>
     * 因此，我们可以记录下以每个结点为根结点的子树的结点数，并在查找第 k 小的值时，使用如下方法搜索：
     * <p>
     * 令 node 等于根结点，开始搜索。
     * <p>
     * 对当前结点 node 进行如下操作：
     * <p>
     * 如果 node 的左子树的结点数 left 小于 k−1，则第 k 小的元素一定在 node 的右子树中，令 node 等于其的右子结点，k 等于 k−left−1，并继续搜索；
     * 如果 node 的左子树的结点数 left 等于 k−1，则第 k 小的元素即为 node ，结束搜索并返回 node 即可；
     * 如果 node 的左子树的结点数 left 大于 k−1，则第 k 小的元素一定在 node 的左子树中，令 node 等于其左子结点，并继续搜索。
     * 在实现中，我们既可以将以每个结点为根结点的子树的结点数存储在结点中，也可以将其记录在哈希表中。
     *
     */

    public int kthSmallest3(TreeNode root, int k) {
        MyBst myBst = new MyBst(root);
        return myBst.kthSmallest(k);
    }

    class MyBst {
        TreeNode root;
        Map<TreeNode, Integer> nodeNum;

        public MyBst(TreeNode root) {
            this.root = root;
            this.nodeNum = new HashMap<TreeNode, Integer>();
            countNodeNum(root);
        }

        /**
         * 递归统计以node为根结点的子树的结点数
         *
         * @param node
         * @return
         */
        private int countNodeNum(TreeNode node) {
            if (node == null) {
                return 0;
            }

            int num = 1 + countNodeNum(node.left) + countNodeNum(node.right);
            nodeNum.put(node, num);
            return num;
        }


        /**
         * 返回二叉搜索树中第k小的元素
         *
         * @param k
         * @return
         */
        public int kthSmallest(int k) {
            TreeNode node = root;
            while (node != null) {
                int leftNum = nodeNum.getOrDefault(node.left, 0);
                if (leftNum < k - 1) {
                    k = k - leftNum - 1;
                    node = node.right;
                } else if (leftNum == k - 1) {
                    break;
                } else {
                    node = node.left;
                }
            }
            return node.val;
        }

    }
}
