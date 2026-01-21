package org.chris.leetcode.tree;

import org.chris.leetcode.dto.TreeConstructUtils;
import org.chris.leetcode.dto.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class PathSum_437 {

    /**
     * 437. 路径总和 III
     * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     *
     * @param args
     */
    public static void main(String[] args) {
        PathSum_437 pathSum437 = new PathSum_437();
        /**
         *          5
         *        / \
         *       4   8
         *      /   / \
         *     11  13  4
         *    / \     / \
         *   7   2   5   1
         */
        TreeNode root = TreeConstructUtils.constructTree(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1});
        int targetSum = 22;
        System.out.println(pathSum437.pathSum(root, targetSum));
    }

    /**
     * 方法二: 前缀和
     * 1、对于空路径我们也需要保存预先处理一下，此时因为空路径不经过任何节点，因此它的前缀和为 0。
     * <p>
     * 2、假设根节点为 root，我们当前刚好访问节点 node，则此时从根节点 root 到节点 node 的路径（无重复节点）刚好为 root → p1 → p2 → … → pk → node，此时我们可以已经保存了节点 p1,p2,p3,…,pk的前缀和，并且计算出了节点 node 的前缀和。
     * <p>
     * 3、假设当前从根节点 root 到节点 node 的前缀和为 curr，则此时我们在已保存的前缀和查找是否存在前缀和刚好等于 curr−targetSum。假设从根节点 root 到节点 node 的路径中存在节点 pi到根节点 root 的前缀和为 curr−targetSum，则节点 pi+1到 node 的路径上所有节点的和一定为 targetSum。
     * <p>
     * 4、我们利用深度搜索遍历树，当我们退出当前节点时，我们需要及时更新已经保存的前缀和。
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, long targetSum) {
        //k（键）：表示前缀和的值（即从数组开头到当前位置的累计和）; v（值）：表示该前缀和在数组中已出现的次数（用于统计和为 K 的子数组数量）。
        Map<Long, Integer> prefix = new HashMap<>();
        long curr = 0L;
        prefix.put(curr, 1);
        return dfs(root, prefix, curr, targetSum);
    }

    /**
     *
     * <p>
     * p
     */
    public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, long targetSum) {
        if (root == null) {
            return 0;
        }
        curr += root.val;
        //refix.get(curr - targetSum, 0) 为什么能表示路径数量？考虑从根到当前节点的路径，和为curr。如果curr - targetSum在哈希表中，说明存在一个点，从该点到当前点的和为targetSum。
        int ret = prefix.getOrDefault(curr - targetSum, 0);
        //put的时候放的是前缀和curr，不需要减去targetSum
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);
        //put的时候放的是前缀和curr，不需要减去targetSum
        //为什么递归后要prefix.put(curr, prefix.getOrDefault(curr, 0) - 1)？（提示：当前路径结束，需移除该前缀和以避免影响其他分支）递归栈不同分支之间的逻辑隔离
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);
        return ret;
    }


    /**
     * 方法一：深度优先搜索
     * 我们首先想到的解法是穷举所有的可能，我们访问每一个节点 node，检测以 node 为起始节点且向下延深的路径有多少种。我们递归遍历每一个节点的所有可能的路径，然后将这些路径数目加起来即为返回结果。
     * <p>
     * 1、我们首先定义 rootSum(p,val) 表示以节点 p 为起点向下且满足路径总和为 val 的路径数目。我们对二叉树上每个节点 p 求出 rootSum(p,targetSum)，然后对这些路径数目求和即为返回结果。
     * <p>
     * 2、我们对节点 p 求 rootSum(p,targetSum) 时，以当前节点 p 为目标路径的起点递归向下进行搜索。假设当前的节点 p 的值为 val，我们对左子树和右子树进行递归搜索，对节点 p 的左孩子节点 pl求出 rootSum(pl,targetSum−val)，以及对右孩子节点 pr求出 rootSum(pr,targetSum−val)。
     * 节点 p 的 rootSum(p,targetSum) 即等于 rootSum(pl,targetSum−val) 与 rootSum(pr,targetSum−val) 之和，同时我们还需要判断一下当前节点 p 的值是否刚好等于 targetSum。
     * <p>
     * 3、我们采用递归遍历二叉树的每个节点 p，对节点 p 求 rootSum(p,val)，然后将每个节点所有求的值进行相加求和返回。
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSumFac(TreeNode root, long targetSum) {
        int res = 0;
        if (root == null) {
            return res;
        }
        //我们采用递归遍历二叉树的每个节点 p，对节点 p 求 rootSum(p,val)，然后将每个节点所有求的值进行相加求和返回。
        //这里是前序遍历的递归写法，自然也可以替换为前序遍历的迭代写法，核心方法还是rootSum
        res = rootSum(root, targetSum);
        res += pathSumFac(root.left, targetSum);
        res += pathSumFac(root.right, targetSum);
        return res;
    }

    /**
     * 我们首先定义 rootSum(p,val) 表示以节点 p 为起点向下且满足路径总和为 val 的路径数目。
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int rootSum(TreeNode root, long targetSum) {
        int res = 0;
        //截至条件1
        if (root == null) {
            return res;
        }
        //截至条件2，targetSum−val 递进进来的
        //同时我们还需要判断一下当前节点 p 的值是否刚好等于 targetSum
        if (root.val == targetSum) {
            res++;
        }
        //对节点 p 的左孩子节点 pl求出 rootSum(pl,targetSum−val)，以及对右孩子节点 pr求出 rootSum(pr,targetSum−val)。节点 p 的 rootSum(p,targetSum) 即等于 rootSum(pl,targetSum−val) 与 rootSum(pr,targetSum−val) 之和
        res += rootSum(root.left, targetSum - root.val);
        res += rootSum(root.right, targetSum - root.val);
        return res;
    }


    public int pathSum_1(TreeNode root, int targetSum) {
        int res = 0;
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res += rootSum(node, targetSum);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return res;
    }

}
