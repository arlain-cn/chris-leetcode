package org.chris.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class NumIslands_200 {
    /**
     * 200. 岛屿数量
     *
     * @param args
     */
    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
//        char[][] grid = new char[][]{{'1', '1', '1'}, {'0', '1', '0'}, {'1', '1', '1'}};
        NumIslands_200 numIslands200 = new NumIslands_200();
        int res = numIslands200.numIslands(grid);
        System.out.println(res);
    }

    /**
     * 方法三：并查集
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int nr = grid.length;
        int nc = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (grid[r][c] == '1') {
                    //先设置为0再放队列，可以提效（当queue元素较多时可以提前设置为0，减少重复处理）
                    grid[r][c] = '0';
                    if (r + 1 < nr && grid[r + 1][c] == '1') {
                        uf.union(r * nc + c, (r + 1) * nc + c);
                    }
                    if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                        uf.union(r * nc + c, (r - 1) * nc + c);
                    }
                    if (c + 1 < nc && grid[r][c + 1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                    if (c - 1 > 0 && grid[r][c - 1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                }
            }
        }
        return uf.count;
    }

    /**
     * 并查集
     * 按秩合并（Union By Rank）：在每次合并操作时，总是将「秩」较小的树的根节点连接到「秩」较大的树的根节点下。
     * 按大小合并（Union By Size）：此处的「大小」指的是集合中节点的数量。每次合并时，总是将节点数较少的集合的根节点指向节点数较多的集合的根节点，从而有效控制树的高度。
     * 1、初始化：将每个元素的集合编号设为其自身的下标，即每个元素自成一个集合。
     * 2、合并操作：将一个集合中的所有元素的 id 修改为另一个集合的 id，从而实现集合的合并。这样，合并后同一集合内所有元素的 id 都相同。
     * 3、查找操作：直接比较两个元素的 idid 是否相同，如果相同则属于同一集合，否则属于不同集合。
     */
    static class UnionFind {
        private int count;
        //表示元素 i 的父节点，初始时每个元素自成一个集合
        int[] parent;
        //rank[i] 表示以 i 为根的树的深度，初始为 1
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        count++;
                    }
                    //默认深度为0
                    rank[i * n + j] = 0;
                }
            }
        }

        /**
         * find(x)：查找元素 x 所属集合的代表元（根节点）。
         *
         * @param i
         * @return
         */
        public int find(int i) {
            //如果 x 不是根节点，继续查找其父节点
            if (parent[i] != i) {
                //路径压缩：将 x 直接连接到祖父节点，实现隔代压缩
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        /**
         * union(x, y)：合并 x 和 y 所在集合。
         *
         * @param x
         * @param y
         */
        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                // 按秩合并：将深度较小的树合并到深度较大的树下
                if (rank[rootx] > rank[rooty]) {
                    //y 的根节点连接到 x 的根节点
                    parent[rooty] = rootx;
                } else if ((rank[rootx] < rank[rooty])) {
                    //x 的根节点连接到 y 的根节点
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    //深度相同，任选一方作为新根，深度+1
                    //新根的深度加 1
                    rank[rootx] += 1;
                }
                //集合缩小
                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }

    /**
     * 方法二：广度优先搜索
     * 同样地，我们也可以使用广度优先搜索代替深度优先搜索。
     * <p>
     * 为了求出岛屿的数量，我们可以扫描整个二维网格。如果一个位置为 1，则将其加入队列，开始进行广度优先搜索。在广度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 0。直到队列为空，搜索结束。
     * <p>
     * 最终岛屿的数量就是我们进行广度优先搜索的次数。
     *
     * @param grid
     * @return
     */
    public int numIslands_bfs(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        //为了求出岛屿的数量，我们可以扫描整个二维网格。如果一个位置为 1，则将其加入队列，开始进行广度优先搜索。在广度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 0。直到队列为空，搜索结束。
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (grid[r][c] == '1') {
                    num_islands++;
                    //则 val/grid[0].length 为 r；val%grid[0].length 为c
                    Queue<Integer> queue = new LinkedList<>();
                    //先设置为0再放队列，可以提效（当queue元素较多时可以提前设置为0，减少重复处理）
                    grid[r][c] = '0';
                    queue.offer(r * nc + c);
                    while (!queue.isEmpty()) {
                        int val = queue.poll();
                        int raw = val / nc;
                        int col = val % nc;
                        if (raw + 1 < nr && grid[raw + 1][col] == '1') {
                            queue.add((raw + 1) * nc + col);
                            grid[raw + 1][col] = '0';
                        }
                        if (raw - 1 >= 0 && grid[raw - 1][col] == '1') {
                            queue.add((raw - 1) * nc + col);
                            grid[raw - 1][col] = '0';
                        }
                        if (col + 1 < nc && grid[raw][col + 1] == '1') {
                            queue.add(raw * nc + col + 1);
                            grid[raw][col + 1] = '0';
                        }
                        if (col - 1 >= 0 && grid[raw][col - 1] == '1') {
                            queue.add(raw * nc + col - 1);
                            grid[raw][col - 1] = '0';
                        }
                    }
                }
            }
        }
        return num_islands;
    }

    public void bfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;
        //则 val/grid[0].length 为 r；val%grid[0].length 为c
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(r * nc + c);
        while (!queue.isEmpty()) {
            int val = queue.poll();
            int raw = val / nc;
            int col = val % nc;
            grid[raw][col] = '0';
            if (raw + 1 < nr && grid[raw + 1][col] == '1') {
                queue.add((raw + 1) * nc + col);
            }
            if (raw - 1 >= 0 && grid[raw - 1][col] == '1') {
                queue.add((raw - 1) * nc + col);
            }
            if (col + 1 < nc && grid[raw][col + 1] == '1') {
                queue.add(raw * nc + col + 1);
            }
            if (col - 1 >= 0 && grid[raw][col - 1] == '1') {
                queue.add(raw * nc + col - 1);

            }
        }
    }


    /**
     * 方法一：深度优先搜索
     * 我们可以将二维网格看成一个无向图，竖直或水平相邻的 1 之间有边相连。
     * <p>
     * 为了求出岛屿的数量，我们可以扫描整个二维网格。如果一个位置为 1，则以其为起始节点开始进行深度优先搜索。在深度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 0。
     * <p>
     * 最终岛屿的数量就是我们进行深度优先搜索的次数。
     *
     * @param grid
     * @return
     */
    public int numIslands_dfs(char[][] grid) {
        int nr = grid.length;
        int nc = grid[0].length;
        //最终岛屿的数量就是我们进行深度优先搜索的次数
        int num_islands = 0;
        //为了求出岛屿的数量，我们可以扫描整个二维网格。如果一个位置为 1，则以其为起始节点开始进行深度优先搜索。
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (grid[r][c] == '1') {
                    num_islands++;
                    dfs(grid, r, c);
                }
            }
        }
        return num_islands;
    }

    public void dfs(char[][] grid, int nr, int nc) {
        if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length) {
            return;
        }
        if (grid[nr][nc] != '1') {
            return;
        }
        //在深度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 0。
        grid[nr][nc] = '0';
        dfs(grid, nr + 1, nc);
        dfs(grid, nr - 1, nc);
        dfs(grid, nr, nc + 1);
        dfs(grid, nr, nc - 1);
    }
}
