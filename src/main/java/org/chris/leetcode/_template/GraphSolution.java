package org.chris.leetcode._template;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GraphSolution {
    /**
     * 基于递归的深度优先搜索实现代码递归实现深度优先搜索（DFS）
     * 
     * @param graph   Map<String, List<String>>，邻接表，key为节点，value为邻接节点列表
     * @param u       String，当前访问的节点
     * @param visited Set<String>，已访问节点的集合
     */
    public void dfsRecursive(Map<String, List<String>> graph, String u, Set<String> visited) {
        System.out.println(u); // 访问当前节点 u
        visited.add(u); // 标记节点 u 已访问

        // 遍历所有邻接节点
        List<String> neighbors = graph.get(u);
        if (neighbors != null) {
            for (String v : neighbors) {
                if (!visited.contains(v)) { // 如果邻接节点 v 未被访问
                    // 递归访问邻接节点 v
                    dfsRecursive(graph, v, visited);
                }
            }
        }
    }

    /**
     * 基于堆栈实现的深度优先搜索实现代码
     * 基于显式栈的深度优先搜索（DFS），适用于无向图/有向图的邻接表表示。
     * 
     * @param graph Map<String, List<String>>，邻接表，key为节点，value为邻接节点列表
     * @param u     String，起始节点
     */
    public void dfsStack(Map<String, List<String>> graph, String u) {
        Set<String> visited = new HashSet<>(); // 记录已访问节点，防止重复遍历
        Deque<StackFrame> stack = new ArrayDeque<>(); // 显式栈，模拟递归过程

        stack.push(new StackFrame(u, 0)); // 入栈：节点u及其下一个待访问邻接节点的下标 0
        visited.add(u); // 标记起始节点已访问
        System.out.println(u); // 访问起始节点

        while (!stack.isEmpty()) {
            StackFrame frame = stack.pop(); // 取出当前节点及其下一个邻接节点下标
            String cur = frame.node;
            int idx = frame.index;

            List<String> neighbors = graph.get(cur); // 当前节点的所有邻接节点
            if (neighbors == null) {
                continue;
            }

            // 如果还有未遍历的邻接节点
            if (idx < neighbors.size()) {
                String v = neighbors.get(idx); // 取出下一个邻接节点
                stack.push(new StackFrame(cur, idx + 1)); // 当前节点下标 + 1，回溯时继续遍历下一个邻接点

                if (!visited.contains(v)) {
                    System.out.println(v); // 访问新节点
                    visited.add(v); // 标记为已访问
                    stack.push(new StackFrame(v, 0)); // 新节点入栈，准备遍历其邻接节点
                }
            }
        }
    }
    // 辅助类：栈帧，用于保存节点和当前遍历到的邻接节点索引
    private static class StackFrame {
        String node;
        int index;

        StackFrame(String node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    
    /**
     * 基于队列实现的广度优先搜索实现代码
     * 基于队列实现的广度优先搜索 (BFS)
     * 
     * @param graph Map<String, List<String>>，邻接表，key为节点，value为邻接节点列表
     * @param u     String，起始节点
     */
    public void bfs(Map<String, List<String>> graph, String u) {
        Set<String> visited = new HashSet<>(); // 使用 visited 标记访问过的节点
        Queue<String> queue = new ArrayDeque<>(); // 使用 queue 存放临时节点

        visited.add(u); // 将起始节点 u 标记为已访问
        queue.offer(u); // 将起始节点 u 加入队列中

        while (!queue.isEmpty()) { // 队列不为空
            u = queue.poll(); // 取出队头节点 u
            System.out.println(u); // 访问节点 u

            List<String> neighbors = graph.get(u);
            if (neighbors != null) {
                for (String v : neighbors) { // 遍历节点 u 的所有未访问邻接节点 v
                    if (!visited.contains(v)) { // 节点 v 未被访问
                        visited.add(v); // 将节点 v 标记为已访问
                        queue.offer(v); // 将节点 v 加入队列中
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        GraphSolution solution = new GraphSolution();

        // 构造示例图
        // graph = {
        // "1": ["2", "3"],
        // "2": ["1", "3", "4"],
        // "3": ["1", "2", "4", "5"],
        // "4": ["2", "3", "5", "6"],
        // "5": ["3", "4"],
        // "6": ["4", "7"],
        // "7": []
        // }
        Map<String, List<String>> graph = new java.util.HashMap<>();
        graph.put("1", java.util.Arrays.asList("2", "3"));
        graph.put("2", java.util.Arrays.asList("1", "3", "4"));
        graph.put("3", java.util.Arrays.asList("1", "2", "4", "5"));
        graph.put("4", java.util.Arrays.asList("2", "3", "5", "6"));
        graph.put("5", java.util.Arrays.asList("3", "4"));
        graph.put("6", java.util.Arrays.asList("4", "7"));
        graph.put("7", java.util.Collections.emptyList());

        System.out.println("BFS starting from node '1':");
        solution.bfs(graph, "1");
    }
}
