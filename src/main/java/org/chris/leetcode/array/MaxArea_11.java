package org.chris.leetcode.array;

public class MaxArea_11 {

    public static void main(String[] args) {
        MaxArea_11 maxArea11 = new MaxArea_11();
        System.out.println(maxArea11.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(maxArea11.maxArea(new int[]{1, 1}));
    }

    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        if (height.length == 2) {
            return Math.min(height[0], height[1]);
        }
        int maxArea = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int curArea = (right - left) * Math.min(height[left], height[right]);
            if (curArea > maxArea) {
                maxArea = curArea;
            }
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
