package org.chris.leetcode.binary;

public class ReverseBits_190 {

    /**
     * 190. 颠倒二进制位
     * 颠倒给定的 32 位有符号整数的二进制位。
     *
     * @param args
     */
    public static void main(String[] args) {
        ReverseBits_190 reverseBits190 = new ReverseBits_190();
        System.out.println(reverseBits190.reverseBits(43261596));
    }

    /**
     * 思路 1：逐位翻转
     * 用一个变量 res 存储翻转后的结果。
     * 算法核心思想：取出 n 的最低位，加到 res 的最低位，然后将 res 左移，n 右移。
     * 这样，n 中原本靠后的位（低位），在 res 中会因为不断的左移而变到靠前的位（高位），从而实现翻转。
     *
     * @param n 输入整数
     * @return 翻转后的整数
     */
    public int reverseBits0(int n) {
        int res = 0;
        // 遍历 32 位（Java int 固定为 32 位）
        for (int i = 0; i < 32; i++) {
            // 1. res << 1: 将 res 左移一位，为即将加入的位腾出位置（相当于给新来的位让座）
            // 2. n & 1: 取出 n 的当前最低位（0 或 1）
            // 3. | : 将取出的位加到 res 的最低位上
            res = (res << 1) | (n & 1);

            // 4. n >>= 1: 将 n 右移一位，准备处理下一位
            n >>= 1;
        }
        return res;
    }


    public int reverseBits(int n) {
        int res = 0;
        // 遍历 32 位（Java int 固定为 32 位）
        for (int i = 0; i < 32 && n != 0; i++) {
            res |= (n & 1) << (32 - i);
            // 4. n >>= 1: 将 n 右移一位，准备处理下一位
            n >>= 1;
        }
        return res;
    }
}
