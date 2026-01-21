package org.chris.leetcode.array;

import java.util.Arrays;

public class RotatePic_48 {
    public static void main(String[] args) {
        RotatePic_48 rotatePic48 = new RotatePic_48();
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        rotatePic48.rotate(matrix);
        Arrays.stream(matrix).forEach(v -> System.out.println(Arrays.toString(v)));
    }

    /**
     *
     * 方法二：原地旋转 ：一层一层遍历的写法
     * 基于公式：[row, col] ==> [col, n-raw-1]
     * 循环：
     * [raw, col]
     * [col, n - raw - 1]
     * [n - raw - 1, n- col -1]
     * [n - col - 1, raw]（[n - col - 1, n - (n - raw - 1) - 1]）
     * [raw, col]（[raw,n- (n - col - 1) -1]）
     * 公式：
     * int tmp = [raw, col];
     * [raw, col] = [n - col - 1, raw];
     * [n - col - 1, raw] = [n - raw - 1, n- col -1];
     * [n - raw - 1, n- col -1] = [col, n - raw - 1];
     * [col, n - raw - 1] = tmp;
     *
     * @param matrix
     */
    public void rotate1(int[][] matrix) {
        int n = matrix.length;

        for (int raw = 0; raw <= n / 2; raw++) {
            //第一层的col满足< n-1即可，第二层需要满足 < n - 2 * raw - 1；但是需要从row做起点
            for (int col = raw; col < n - raw - 1; col++) {
                // leet的做法是 col <  (n + 1) / 2
                // for (int col = raw; col <  (n + 1) / 2; col++) {
                int tmp = matrix[raw][col];
                matrix[raw][col] = matrix[n - col - 1][raw];
                matrix[n - col - 1][raw] = matrix[n - raw - 1][n - col - 1];
                matrix[n - raw - 1][n - col - 1] = matrix[col][n - raw - 1];
                matrix[col][n - raw - 1] = tmp;
            }
        }
    }

    /**
     * 方法二：原地旋转 ：左上角起点遍历的写法
     * 当 n 为偶数时，我们需要枚举 n*n/4=(n/2)×(n/2) 个位置，可以将该图形分为四块
     * 当 n 为奇数时，由于中心的位置经过旋转后位置不变，我们需要枚举 (n*n−1)/4=((n−1)/2)×((n+1)/2) 个位置，需要换一种划分的方式
     * 合并后，最终选择了外层循环:n/2，内层循环:(n+1)/2
     *
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;

        for (int raw = 0; raw < n / 2; raw++) {
            //第一层的col满足< n-1即可，第二层需要满足 < n - 2 * raw - 1；但是需要从row做起点
            // leet的做法是 col <  (n + 1) / 2
            for (int col = raw; col < (n + 1) / 2; col++) {
                int tmp = matrix[raw][col];
                matrix[raw][col] = matrix[n - col - 1][raw];
                matrix[n - col - 1][raw] = matrix[n - raw - 1][n - col - 1];
                matrix[n - raw - 1][n - col - 1] = matrix[col][n - raw - 1];
                matrix[col][n - raw - 1] = tmp;
            }
        }
    }

    /**
     * 方法三：用翻转代替旋转
     * 先将其通过上下水平轴翻转：(raw,col) --> (n-raw-1,col)
     * 再根据主（斜）对角线翻转: (raw,col) --> (col,raw)
     * 以上两部联立后等同于：[row, col] ==> [col, n-raw-1]
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int raw = 0; raw < n / 2; raw++) {
            for (int col = 0; col < n; col++) {
                int tmp = matrix[raw][col];
                matrix[raw][col] = matrix[n - raw - 1][col];
                matrix[n - raw - 1][col] = tmp;
            }
        }

        for (int raw = 0; raw < n - 1; raw++) {
            for (int col = raw + 1; col < n; col++) {
                int tmp = matrix[raw][col];
                matrix[raw][col] = matrix[col][raw];
                matrix[col][raw] = tmp;
            }
        }
    }
}
