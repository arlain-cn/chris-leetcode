package org.chris.leetcode.Collection;

import java.util.*;

/**
 * 图的常见存储结构实现
 */
public class GraphStorageStructure {

    /**
     * 邻接矩阵表示法
     */
    public static class AdjacencyMatrixGraph {
        private int[][] matrix;  // 邻接矩阵
        private int vertices;    // 顶点数量
        private boolean directed; // 是否为有向图

        public AdjacencyMatrixGraph(int vertices, boolean directed) {
            this.vertices = vertices;
            this.directed = directed;
            this.matrix = new int[vertices][vertices];
        }

        /**
         * 添加边
         */
        public void addEdge(int from, int to) {
            if (from >= 0 && from < vertices && to >= 0 && to < vertices) {
                matrix[from][to] = 1;
                if (!directed) {
                    matrix[to][from] = 1; // 无向图需要添加反向边
                }
            }
        }

        /**
         * 删除边
         */
        public void removeEdge(int from, int to) {
            if (from >= 0 && from < vertices && to >= 0 && to < vertices) {
                matrix[from][to] = 0;
                if (!directed) {
                    matrix[to][from] = 0;
                }
            }
        }

        /**
         * 检查是否存在边
         */
        public boolean hasEdge(int from, int to) {
            if (from >= 0 && from < vertices && to >= 0 && to < vertices) {
                return matrix[from][to] == 1;
            }
            return false;
        }

        /**
         * 获取顶点的邻接点
         */
        public List<Integer> getNeighbors(int vertex) {
            List<Integer> neighbors = new ArrayList<>();
            if (vertex >= 0 && vertex < vertices) {
                for (int i = 0; i < vertices; i++) {
                    if (matrix[vertex][i] == 1) {
                        neighbors.add(i);
                    }
                }
            }
            return neighbors;
        }

        /**
         * 获取顶点的度数（出度或度）
         */
        public int getDegree(int vertex) {
            int degree = 0;
            if (vertex >= 0 && vertex < vertices) {
                for (int i = 0; i < vertices; i++) {
                    if (matrix[vertex][i] == 1) {
                        degree++;
                    }
                }
            }
            return degree;
        }

        /**
         * 打印邻接矩阵
         */
        public void printMatrix() {
            System.out.println("Adjacency Matrix:");
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    /**
     * 邻接表表示法
     */
    public static class AdjacencyListGraph {
        private List<List<Integer>> adjacencyList;
        private int vertices;
        private boolean directed;

        public AdjacencyListGraph(int vertices, boolean directed) {
            this.vertices = vertices;
            this.directed = directed;
            this.adjacencyList = new ArrayList<>(vertices);
            for (int i = 0; i < vertices; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        /**
         * 添加边
         */
        public void addEdge(int from, int to) {
            if (from >= 0 && from < vertices && to >= 0 && to < vertices) {
                adjacencyList.get(from).add(to);
                if (!directed && from != to) { // 无向图且不是自环
                    adjacencyList.get(to).add(from);
                }
            }
        }

        /**
         * 删除边
         */
        public void removeEdge(int from, int to) {
            if (from >= 0 && from < vertices && to >= 0 && to < vertices) {
                adjacencyList.get(from).remove(Integer.valueOf(to));
                if (!directed) {
                    adjacencyList.get(to).remove(Integer.valueOf(from));
                }
            }
        }

        /**
         * 检查是否存在边
         */
        public boolean hasEdge(int from, int to) {
            if (from >= 0 && from < vertices && to >= 0 && to < vertices) {
                return adjacencyList.get(from).contains(to);
            }
            return false;
        }

        /**
         * 获取顶点的邻接点
         */
        public List<Integer> getNeighbors(int vertex) {
            if (vertex >= 0 && vertex < vertices) {
                return new ArrayList<>(adjacencyList.get(vertex));
            }
            return new ArrayList<>();
        }

        /**
         * 获取顶点的度数
         */
        public int getDegree(int vertex) {
            if (vertex >= 0 && vertex < vertices) {
                return adjacencyList.get(vertex).size();
            }
            return 0;
        }

        /**
         * 打印邻接表
         */
        public void printList() {
            System.out.println("Adjacency List:");
            for (int i = 0; i < vertices; i++) {
                System.out.print("Vertex " + i + ": ");
                for (int neighbor : adjacencyList.get(i)) {
                    System.out.print(neighbor + " ");
                }
                System.out.println();
            }
        }
    }

    /**
     * 使用Map实现的邻接表（支持任意类型的顶点）
     */
    public static class MapBasedGraph<T> {
        private Map<T, List<T>> adjacencyMap;
        private boolean directed;

        public MapBasedGraph(boolean directed) {
            this.directed = directed;
            this.adjacencyMap = new HashMap<>();
        }

        /**
         * 添加顶点
         */
        public void addVertex(T vertex) {
            if (!adjacencyMap.containsKey(vertex)) {
                adjacencyMap.put(vertex, new ArrayList<>());
            }
        }

        /**
         * 添加边
         */
        public void addEdge(T from, T to) {
            addVertex(from);
            addVertex(to);
            adjacencyMap.get(from).add(to);
            if (!directed && !from.equals(to)) {
                adjacencyMap.get(to).add(from);
            }
        }

        /**
         * 删除边
         */
        public void removeEdge(T from, T to) {
            if (adjacencyMap.containsKey(from)) {
                adjacencyMap.get(from).remove(to);
            }
            if (!directed && adjacencyMap.containsKey(to)) {
                adjacencyMap.get(to).remove(from);
            }
        }

        /**
         * 检查是否存在边
         */
        public boolean hasEdge(T from, T to) {
            return adjacencyMap.containsKey(from) &&
                   adjacencyMap.get(from).contains(to);
        }

        /**
         * 获取顶点的邻接点
         */
        public List<T> getNeighbors(T vertex) {
            if (adjacencyMap.containsKey(vertex)) {
                return new ArrayList<>(adjacencyMap.get(vertex));
            }
            return new ArrayList<>();
        }

        /**
         * 检查是否包含顶点
         */
        public boolean containsVertex(T vertex) {
            return adjacencyMap.containsKey(vertex);
        }

        /**
         * 获取所有顶点
         */
        public Set<T> getVertices() {
            return adjacencyMap.keySet();
        }

        /**
         * 获取顶点数量
         */
        public int getVertexCount() {
            return adjacencyMap.size();
        }

        /**
         * 获取边的数量
         */
        public int getEdgeCount() {
            int count = 0;
            for (List<T> neighbors : adjacencyMap.values()) {
                count += neighbors.size();
            }

            if (!directed) {
                count /= 2; // 无向图的边被计算了两次
            }

            return count;
        }

        /**
         * 打印图
         */
        public void printGraph() {
            System.out.println("Map-Based Graph:");
            for (Map.Entry<T, List<T>> entry : adjacencyMap.entrySet()) {
                System.out.print(entry.getKey() + ": ");
                for (T neighbor : entry.getValue()) {
                    System.out.print(neighbor + " ");
                }
                System.out.println();
            }
        }
    }

    /**
     * 测试示例
     */
    public static void main(String[] args) {
        System.out.println("=== 测试邻接矩阵表示法 ===");
        AdjacencyMatrixGraph matrixGraph = new AdjacencyMatrixGraph(4, false);
        matrixGraph.addEdge(0, 1);
        matrixGraph.addEdge(0, 2);
        matrixGraph.addEdge(1, 2);
        matrixGraph.addEdge(2, 3);

        matrixGraph.printMatrix();
        System.out.println("顶点0的邻接点: " + matrixGraph.getNeighbors(0));
        System.out.println("顶点2的度数: " + matrixGraph.getDegree(2));

        System.out.println("\n=== 测试邻接表表示法 ===");
        AdjacencyListGraph listGraph = new AdjacencyListGraph(4, false);
        listGraph.addEdge(0, 1);
        listGraph.addEdge(0, 2);
        listGraph.addEdge(1, 2);
        listGraph.addEdge(2, 3);

        listGraph.printList();
        System.out.println("顶点0的邻接点: " + listGraph.getNeighbors(0));
        System.out.println("顶点2的度数: " + listGraph.getDegree(2));

        System.out.println("\n=== 测试基于Map的图表示法 ===");
        MapBasedGraph<String> mapGraph = new MapBasedGraph<>(false);
        mapGraph.addEdge("A", "B");
        mapGraph.addEdge("A", "C");
        mapGraph.addEdge("B", "C");
        mapGraph.addEdge("C", "D");

        mapGraph.printGraph();
        System.out.println("顶点A的邻接点: " + mapGraph.getNeighbors("A"));
        System.out.println("边的数量: " + mapGraph.getEdgeCount());
    }
}
