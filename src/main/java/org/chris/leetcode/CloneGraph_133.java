package org.chris.leetcode;

import java.util.*;

public class CloneGraph_133 {

    /**
     * 133. 克隆图
     * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
     * <p>
     * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
     * <p>
     * class Node {
     * public int val;
     * public List<Node> neighbors;
     * }
     *
     * @param args
     */
    public static void main(String[] args) {
        // 构造邻接表 adjList = [[2,4],[1,3],[2,4],[1,3]]
        // 创建4个节点，值分别为1, 2, 3, 4
        Node node1 = new Node();
        node1.val = 1;
        node1.neighbors = new ArrayList<>();

        Node node2 = new Node();
        node2.val = 2;
        node2.neighbors = new ArrayList<>();

        Node node3 = new Node();
        node3.val = 3;
        node3.neighbors = new ArrayList<>();

        Node node4 = new Node();
        node4.val = 4;
        node4.neighbors = new ArrayList<>();

        // 根据邻接表 [[2,4],[1,3],[2,4],[1,3]] 设置邻居关系
        // 节点1的邻居是节点2和4
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        // 节点2的邻居是节点1和3
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        // 节点3的邻居是节点2和4
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        // 节点4的邻居是节点1和3
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        // 现在可以使用node1作为输入来测试cloneGraph函数
        CloneGraph_133 solution = new CloneGraph_133();
        Node clonedGraph = solution.cloneGraph(node1);

        System.out.println("Original graph node 1 neighbors: ");
        for (Node neighbor : node1.neighbors) {
            System.out.print(neighbor.val + " ");
        }
        System.out.println();

        if (clonedGraph != null) {
            System.out.println("Cloned graph node " + clonedGraph.val + " neighbors: ");
            for (Node neighbor : clonedGraph.neighbors) {
                System.out.print(neighbor.val + " ");
            }
            System.out.println();
        } else {
            System.out.println("Cloned graph is null");
        }
    }

    /**
     * 方法一：深度优先搜索
     * 1. 设 graph 为无向图的邻接表，visited 为已访问节点的集合，u 为当前节点。定义递归函数 def dfs_recursive(graph, u, visited):。
     * 2. 将起始节点 u 标记为已访问（visited.add(u)）。
     * 3. 判断当前节点 u 是否为目标节点（具体依据题目要求）。
     * 4. 如果 u 为目标节点，直接返回结果。
     * 5. 如果 u 不是目标节点，则遍历 u 的所有未访问的邻接节点 v。
     * 6. 对每个未访问的邻接节点 v，递归调用 dfs_recursive(graph, v, visited) 继续搜索。
     * 7. 如果 u 没有未访问的邻接节点，则自动回溯到上一个节点，继续探索其他分支。
     * 8. 重复步骤 3∼7，直到遍历完整个图或找到目标节点为止。
     *
     * @param node
     * @return
     */
    public static Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        // 如果该节点已经被访问过了，则直接从哈希表中取出对应的克隆节点返回
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        // 克隆节点，注意到为了深拷贝我们不会克隆它的邻居的列表
        Node clonedNode = new Node(node.val, new ArrayList<>());
        // 哈希表存储
        visited.put(node, clonedNode);

        // 遍历该节点的邻居并更新克隆节点的邻居列表
        for (Node neighbor : node.neighbors) {
            clonedNode.neighbors.add(cloneGraph(neighbor));
        }
        return clonedNode;
    }

    /**
     * 方法二：广度优先遍历
     * 下面以无向图为例，简要梳理广度优先搜索的基本流程：
     * 1. 将起始节点 u 加入队列，并标记为已访问。
     * 2. 当队列不为空时，重复以下操作：
     * ------从队列头部取出一个节点 x，访问该节点。
     * ------遍历 x 的所有未被访问的邻接节点 v，将它们加入队列并标记为已访问，避免重复访问。
     * 3. 如果在遍历过程中找到目标节点，可提前结束；否则直到队列为空为止。
     *
     * @param node
     * @return 克隆后的图的起始节点
     */
    public Node cloneGraph_bfs(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        visited.put(node, new Node(node.val, new ArrayList<>()));

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for (Node nb : curr.neighbors) {
                if (!visited.containsKey(nb)) {
                    queue.offer(nb);
                    visited.put(nb, new Node(nb.val, new ArrayList<>()));
                }
                // 更新当前节点的邻居列表，这个地方秒啊不用再遍历一次Map了
                visited.get(curr).neighbors.add(visited.get(nb));
            }
        }
        return visited.get(node);
    }


    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }

        @Override
        public String toString() {
            return "Node{" + "val=" + val + '}';
        }
    }
}
