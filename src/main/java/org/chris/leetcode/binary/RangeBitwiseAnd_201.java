package org.chris.leetcode.binary;

public class RangeBitwiseAnd_201 {

    /**
     * 201. 数字范围按位与
     * 给你两个整数 left 和 right ，表示区间 [left, right] ，返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
     *
     * @param args
     */
    public static void main(String[] args) {
        RangeBitwiseAnd_201 rangeBitwiseAnd201 = new RangeBitwiseAnd_201();
        System.out.println(rangeBitwiseAnd201.rangeBitwiseAnd(5, 7));//4
        System.out.println(rangeBitwiseAnd201.rangeBitwiseAnd(0, 0));//0
        System.out.println(rangeBitwiseAnd201.rangeBitwiseAnd(1, 2147483647));//0
    }

    /**
     * 思路 1：位运算代码
     * <p>
     * 一、背景知识：
     * 1. 只有对应位置上都为1的情况下，按位与才能得到1。而对应位置上只要出现0，则该位置上最终的按位与结果一定为0。
     * 2. 那么我们可以先来求一下区间所有数对应二进制的**公共前缀，假设这个前缀的长度为x**。
     * 3. 公共前缀部分**因为每个位置上的二进制值完全一样，所以按位与的结果也相同**。
     * 4. 接下来考虑除了公共前缀的剩余的二进制位部分。
     * <p>
     * 二、这时候剩余部分有两种情况：
     * 1. x=31。则left=right，其按位与结果就是left本身。
     * 2. 0≤x<31。这种情况下因为`left<right`，所以left的第x+1位必然为0，right的第x+1位必然为1。
     * ------ 注意：**left、right第x+1位上不可能同为0或1，这样就是公共前缀了**。
     * ------ 注意：同样不可能是left第x+1位为1，right第x+1位为0，这样就是left>right了。
     * 3. **而从第x+1位起，从left到right。肯定会经过10000...的位置，从而使得除了公共前缀的剩余部分（后面的31−x位）的按位与结果一定为0**。举个例子，x=27，则除了公共前缀的剩余部分长度为4。则剩余部分从0XXX到1XXX必然会经过1000，则剩余部分的按位与结果为0000。
     * <p>
     * 三、**那么这道题就转变为了求[left,right]区间范围内所有数的二进制公共前缀**，然后在后缀位置上补上0。
     * <p>
     * 1. 求解公共前缀，我们借助于Brian Kernigham算法中的`n & (n - 1)`公式来计算。
     * 2. `n & (n - 1)`公式：对n和n−1进行按位与运算后，n最右边的1会变成0，也就是清除了n对应二进制的最右侧的1。比如n=10110100(2)，进行n & (n - 1)操作之后，就变为了n=10110000(2)。
     * <p>
     * 具体计算步骤如下：
     * 1️⃣对于给定的区间范围[left,right]，对right进行right & (right - 1)迭代。
     * 2️⃣直到right小于等于left，此时区间内非公共前缀的1均变为了0。
     * 3️⃣最后输出right作为答案。
     *
     * @param left
     * @param right
     * @return
     */
    public int rangeBitwiseAnd(int left, int right) {
        while (left < right) {
            right = right & (right - 1);
        }
        return right;
    }


    public int rangeBitwiseAnd_9(int left, int right) {
        int res = left;
        left = left + 1;
        for (; left <= right; left++) {
            res &= left;
        }
        return res;
    }
}
