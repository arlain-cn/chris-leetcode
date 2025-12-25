package org.chris.leetcode;

/**
 * 240. 搜索二维矩阵 II
 */
public class SearchMatrix_240 {

    public static void main(String[] args) {
        SearchMatrix_240 searchMatrix240 = new SearchMatrix_240();
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
//        System.out.println(searchMatrix240.searchMatrix(matrix, 91));
        matrix = new int[][]{{-1, 3}};
//        System.out.println(searchMatrix240.searchMatrix(matrix, 3));
        matrix = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
        System.out.println(searchMatrix240.searchMatrix(matrix, 19));
    }

    /**
     * 方法二：二分查找
     * 思路与算法
     * <p>
     * 由于矩阵 matrix 中每一行的元素都是升序排列的，因此我们可以对每一行都使用一次二分查找，判断 target 是否在该行中，从而判断 target 是否出现。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix1(int[][] matrix, int target) {

        for (int[] rows : matrix) {
            if (search(rows, target)) {
                return true;
            }
        }
        return false;
    }

    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 方法三：Z 字形查找
     * 思路与算法
     * 我们可以从矩阵 matrix 的右上角 (0,n−1) 进行搜索。在每一步的搜索过程中，如果我们位于位置 (x,y)，那么我们希望在以 matrix 的左下角为左下角、以 (x,y) 为右上角的矩阵中进行搜索，即行的范围为 [x,m−1]，列的范围为 [0,y]：
     * <p>
     * 如果 matrix[x,y]=target，说明搜索完成；
     * 如果 matrix[x,y]>target，由于每一列的元素都是升序排列的，那么在当前的搜索矩阵中，所有位于第 y 列的元素都是严格大于 target 的，因此我们可以将它们全部忽略，即将 y 减少 1；
     * 如果 matrix[x,y]<target，由于每一行的元素都是升序排列的，那么在当前的搜索矩阵中，所有位于第 x 行的元素都是严格小于 target 的，因此我们可以将它们全部忽略，即将 x 增加 1。
     * <p>
     * 在搜索的过程中，如果我们超出了矩阵的边界，那么说明矩阵中不存在 target。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int raw = 0, col = n - 1;
        while (raw < m && col >= 0) {
            int num = matrix[raw][col];
            if (num == target) {
                return true;
            } else if (num > target) {
                col--;
            } else {
                raw++;
            }
        }
        return false;
    }


    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        for (int raw = m - 1; raw >= 0; raw--) {
            if (target >= matrix[raw][0]) {
                if (search(matrix[raw], target)) {
                    return true;
                }
            }
        }
        return false;
    }


}
