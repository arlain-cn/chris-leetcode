package org.chris.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrder_54 {

    public static void main(String[] args) {
        SpiralOrder_54 spiralOrder54 = new SpiralOrder_54();
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(spiralOrder54.spiralOrder1(matrix));
        System.out.println(spiralOrder54.spiralOrder(matrix));
    }

    /**
     * 方法二：按层模拟
     * 可以将矩阵看成若干层，首先输出最外层的元素，其次输出次外层的元素，直到输出最内层的元素。
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder1(int[][] matrix) {
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        int left = 0, right = colNum - 1, top = 0, bottom = rowNum - 1;
        //0 左右，1上下，2右左，3下上
        int direction = 0;
        List<Integer> res = new ArrayList<>();
        while (right >= left && bottom >= top) {
            if (direction == 0) {
                for (int i = left; i <= right; i++) {
                    res.add(matrix[top][i]);
                }
                direction = ++direction % 4;
                top++;
                continue;
            }

            if (direction == 1) {
                for (int i = top; i <= bottom; i++) {
                    res.add(matrix[i][right]);
                }
                direction = ++direction % 4;
                right--;
                continue;
            }
            if (direction == 2) {
                for (int i = right; i >= left; i--) {
                    res.add(matrix[bottom][i]);
                }
                direction = ++direction % 4;
                bottom--;
                continue;
            }
            if (direction == 3) {
                for (int i = bottom; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                direction = ++direction % 4;
                left++;
            }
        }
        return res;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        int left = 0, right = colNum - 1, top = 0, bottom = rowNum - 1;

        List<Integer> res = new ArrayList<>();
        while (right >= left && bottom >= top) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            for (int i = top + 1; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            if (right > left && bottom > top) {
                //i >= left 左下角元素被统计在内了
                for (int i = right - 1; i >= left; i--) {
                    res.add(matrix[bottom][i]);
                }
                //bottom这层已经被访问了
                for (int i = bottom - 1; i >= top + 1; i--) {
                    res.add(matrix[i][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return res;
    }

    /**
     * {{1, 2, 3, 4},
     * {5, 6, 7, 8},
     * {9, 10, 11, 12}};
     */
}


