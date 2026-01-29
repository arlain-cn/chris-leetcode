package org.chris.leetcode.binary;

public class HammingWeight_191 {

    /**
     * 191. 位1的个数
     *
     * @param args
     */
    public static void main(String[] args) {
        HammingWeight_191 hammingWeight191 = new HammingWeight_191();
        System.out.println(hammingWeight191.hammingWeight(11));
        System.out.println(hammingWeight191.hammingWeight(128));
        System.out.println(hammingWeight191.hammingWeight(2147483645));
    }

    /**
     * 方法一：循环检查二进制位
     * <p>
     * 我们可以直接循环检查给定整数 n 的二进制位的每一位是否为 1。
     * 具体代码中，当检查第 i 位时，我们可以让 n 与 2^i 进行与运算，当且仅当 n 的第 i 位为 1 时，运算结果不为 0。
     *
     * @param n
     * @return
     */
    public int hammingWeight_0(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (((n >> i) & 1) == 1) {
                res++;
            }
        }
        return res;
    }

    /**
     * 方法二：位运算优化
     *
     * 要将二进制数 X 最右侧的 1 置为 0，只需执行 X & (X - 1) 操作即可。
     */
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }
}
