package org.chris.leetcode;

import java.util.Arrays;

public class SetZeroes_73 {

    public static void main(String[] args) {
        SetZeroes_73 setZeroes73 = new SetZeroes_73();
        int[][] matrix = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        setZeroes73.setZeroes(matrix);
        Arrays.stream(matrix).forEach(v -> System.out.println(Arrays.toString(v)));
        matrix = new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroes73.setZeroes(matrix);
        Arrays.stream(matrix).forEach(v -> System.out.println(Arrays.toString(v)));
        matrix = new int[][]{{-2147483648}, {2}, {3}};
        setZeroes73.setZeroes(matrix);
        Arrays.stream(matrix).forEach(v -> System.out.println(Arrays.toString(v)));
        matrix = new int[][]{{1, 0, 3}};
        setZeroes73.setZeroes(matrix);
        Arrays.stream(matrix).forEach(v -> System.out.println(Arrays.toString(v)));
    }

    /**
     * 方法一：使用标记数组
     * 思路和算法
     * 我们可以用两个标记数组分别记录每一行和每一列是否有零出现。
     * 具体地，我们首先遍历该数组一次，如果某个元素为 0，那么就将该元素所在的行和列所对应标记数组的位置置为 true。最后我们再次遍历该数组，用标记数组更新原数组即可。
     *
     * @param matrix
     */
    public void setZeroes1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] mArr = new int[m];
        int[] nArr = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    mArr[i] = -1;
                    nArr[j] = -1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mArr[i] == -1 || nArr[j] == -1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 方法二：使用两个标记变量
     * 思路和算法
     * <p>
     * 我们可以用矩阵的第一行和第一列代替方法一中的两个标记数组，以达到 O(1) 的额外空间。但这样会导致原数组的第一行和第一列被修改，无法记录它们是否原本包含 0。因此我们需要额外使用两个标记变量分别记录第一行和第一列是否原本包含 0。
     * <p>
     * 在实际代码中，我们首先预处理出两个标记变量，接着使用其他行与列去处理第一行与第一列，然后反过来使用第一行与第一列去更新其他行与列，最后使用两个标记变量更新第一行与第一列即可。
     *
     * @param matrix
     * @see 第一行&第一列 的记录到的0是不需要再恢复的，因为已经包含0才会被更新为0；两个标记的作用是最后更新 第一行&第一列 用的
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean rowFlag0 = false;
        boolean colFlag0 = false;

        //row_0 原本是否包含0
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                colFlag0 = true;
            }
        }
        //col_0 原本是否包含0
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                rowFlag0 = true;
            }
        }
        //统计row_0,col_0 之外的row、col到row_0、col_0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = matrix[i][0] = 0;
                }
            }
        }
        //根据row_0,col_0重写row_0,col_0 之外的row、col
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        //根据col_0重写row_0
        for (int i = 0; i < m; i++) {
            if (colFlag0) {
                matrix[i][0] = 0;
            }
        }
        //根据row_0重写col_0 之外的row、col
        for (int j = 0; j < n; j++) {
            if (rowFlag0) {
                matrix[0][j] = 0;
            }
        }
    }
}
