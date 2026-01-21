package org.chris.leetcode.tree;

import org.chris.leetcode.dto.TreeConstructUtils;
import org.chris.leetcode.dto.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class lowestCommonAncestor_236 {

    /**
     * 236. 二叉树的最近公共祖先
     *
     * @param args
     */
    public static void main(String[] args) {
        //[3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
        lowestCommonAncestor_236 lowestCommonAncestor236 = new lowestCommonAncestor_236();
        TreeNode root = TreeConstructUtils.constructTree(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});

        TreeNode res = lowestCommonAncestor236.lowestCommonAncestor(root, root.left, root.right);
        System.out.println(res);
    }

    /**
     * 方法二：存储父节点
     * 我们可以用哈希表存储所有节点的父节点，然后我们就可以利用节点的父节点信息从 p 结点开始不断往上跳，并记录已经访问过的节点，再从 q 节点开始不断往上跳，如果碰到已经访问过的节点，那么这个节点就是我们要找的最近公共祖先。
     * <p>
     * 1、从根节点开始遍历整棵二叉树，用哈希表记录每个节点的父节点指针。
     * 2、从 p 节点开始不断往它的祖先移动，并用数据结构记录已经访问过的祖先节点。
     * 3、同样，我们再从 q 节点开始不断往它的祖先移动，如果有祖先已经被访问过，即意味着这是 p 和 q 的深度最深的公共祖先，即 LCA 节点。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    private Map<TreeNode, TreeNode> parent = new HashMap<>();
    private Set<TreeNode> visited = new HashSet<>();

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        dfsParent(root);
        //需要从p开始，因为p可能是结果
        while (p != null) {
            visited.add(p);
            p = parent.get(p);
        }
        //需要从q开始，因为q可能是结果
        while (q != null) {
            if (visited.contains(q)) {
                return q;
            }
            q = parent.get(q);
        }
        return null;
    }

    private void dfsParent(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            parent.put(root.left, root);
            dfsParent(root.left);
        }
        if (root.right != null) {
            parent.put(root.right, root);
            dfsParent(root.right);
        }
    }

    /**
     * 方法一：递归
     * 我们递归遍历整棵二叉树，定义 f(x)表示 x 节点的子树中是否包含 p 节点或 q 节点，如果包含为 true，否则为 false。那么符合条件的最近公共祖先 x 一定满足如下条件：
     * <p>
     * (f(lson) && f(rson)) ∣∣ ((x = p ∣∣ x = q) && (f(lson) ∣∣ f(rson)))
     * <p>
     * 1、其中 lson 和 rson 分别代表 x 节点的左孩子和右孩子。初看可能会感觉条件判断有点复杂，我们来一条条看，f(lson) && f(rson)说明左子树和右子树均包含 p 节点或 q 节点，
     * 如果左子树包含的是 p 节点，那么右子树只能包含 q 节点，反之亦然，因为 p 节点和 q 节点都是不同且唯一的节点，因此如果满足这个判断条件即可说明 x 就是我们要找的最近公共祖先。
     * <p>
     * 2、再来看第二条判断条件，这个判断条件即是考虑了 x 恰好是 p 节点或 q 节点且它的左子树或右子树有一个包含了另一个节点的情况，因此如果满足这个判断条件亦可说明 x 就是我们要找的最近公共祖先。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */

    private TreeNode ans;

    public TreeNode lowestCommonAncestor_dfs(TreeNode root, TreeNode p, TreeNode q) {
        ans = null;
        dfs(root, p, q);
        return ans;
    }

    /**
     * 定义 dfs()为表示 x 节点的子树中是否包含 p 节点或 q 节点，如果包含为 true，否则为 false
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }

        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        //root 为 “最近公共祖先”
        if (lson && rson || ((root == p || root == q) && (lson || rson))) {
            ans = root;
            return true;
        }
        //
        return lson || rson || (root == p || root == q);
    }

    /**
     * 精简方法
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        if (left == null) return right;
        if (right == null) return left;
        return null;
    }
}
