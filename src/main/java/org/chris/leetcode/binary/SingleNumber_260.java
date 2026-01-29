package org.chris.leetcode.binary;

public class SingleNumber_260 {

    /**
     * 260. 只出现一次的数字 III
     * 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
     * <p>
     * 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
     *
     * @param args
     */
    public static void main(String[] args) {
        SingleNumber_260 singleNumber260 = new SingleNumber_260();
        System.out.println(singleNumber260.singleNumber(new int[]{1, 2, 1, 3, 2, 5}));
        System.out.println(singleNumber260.singleNumber(new int[]{-1, 0}));
        System.out.println(singleNumber260.singleNumber(new int[]{0, 1}));
    }

    /**
     * 思路 1：位运算
     * 一、背景知识：
     * 1. 在求解这道题之前，我们先来看看如何求解「一个数组中除了某个元素只出现一次以外，其余每个元素均出现两次。」即「136. 只出现一次的数字」问题。
     * 2. 我们可以对所有数不断进行异或操作，最终可得到单次出现的元素。
     * <p>
     * 二、下面我们再来看这道题。
     * 1. 如果数组中有两个数字只出现一次，其余每个元素均出现两次。那么经过全部异或运算。我们可以得到只出现一次的两个数字的异或结果。
     * 2. 根据异或结果的性质，异或运算中如果某一位上为 1，则说明异或的两个数在该位上是不同的。根据这个性质，我们将数字分为两组：
     * 1️⃣一组是和该位为 0 的数字，
     * 2️⃣一组是该位为 1 的数字。
     * 3️⃣然后将这两组分别进行异或运算，就可以得到最终要求的两个数字。
     *
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        int allXor = 0;
        // 1. 全员异或，得到的结果是两个只出现一次数字的异或值 (a ^ b)
        for (int num : nums) {
            allXor ^= num;
        }

        // 2. 获取 allXor 中最低位的 1
        // 因为 a != b，所以 allXor != 0，肯定有一位是 1
        // 这一位为 1 说明 a 和 b 在这一位上不同（一个是 0，一个是 1）
        int mask = 1;
        while ((allXor & mask) == 0) {
            mask <<= 1;
        }

        int aXor = 0;
        int bXor = 0;
        // 3. 根据 mask 将数组分为两组
        // mask 位为 0 的一组，mask 位为 1 的一组
        // 两个只出现一次的数字会被分到不同的组中
        // 其他出现两次的数字，两次都会被分到同一组中
        for (int num : nums) {
            if ((num & mask) == 0) {
                aXor ^= num;
            } else {
                bXor ^= num;
            }
        }

        return new int[]{aXor, bXor};
    }
}
