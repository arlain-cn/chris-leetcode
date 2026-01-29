package org.chris.leetcode.binary;

public class SingleNumber_136 {

    /**
     * 136. 只出现一次的数字
     *
     * @param args
     */
    public static void main(String[] args) {
        SingleNumber_136 singleNumber136 = new SingleNumber_136();
        System.out.println(singleNumber136.singleNumber(new int[]{2, 2, 1}));//1
        System.out.println(singleNumber136.singleNumber(new int[]{4, 1, 2, 1, 2}));//4
        System.out.println(singleNumber136.singleNumber(new int[]{1}));//1
    }

    /**
     * 方法一：位运算
     * 如何才能做到线性时间复杂度和常数空间复杂度呢？
     * <p>
     * 答案是使用位运算。对于这道题，可使用异或运算 ⊕。异或运算有以下三个性质。
     * 1. 任何数和 0 做异或运算，结果仍然是原来的数，即 a⊕0=a。
     * 2. 任何数和其自身做异或运算，结果是 0，即 a⊕a=0。
     * 3. 异或运算满足交换律和结合律，即 a⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b。
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            ans ^= x;
        }
        return ans;
    }

}
