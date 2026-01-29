package org.chris.leetcode.binary;

public class GetSum_371 {

    /**
     * 371. 两整数之和
     * <p>
     * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。
     *
     * @param args
     */
    public static void main(String[] args) {
        GetSum_371 getSum371 = new GetSum_371();
        System.out.println(getSum371.getSum(1, 2));
        System.out.println(getSum371.getSum(2, 3));
    }

    /**
     * 无进位和：两个二进制位相加时，a ^ b（异或）能表示无进位的和（如 3 ^ 5 = 6）。
     * 进位：a & b（与）能表示进位，进位需左移一位（如 3 & 5 = 1，左移后为 2）。
     * 递归终止：当进位为0时，结果即为最终和。
     * 尝试用 3 + 5 逐步推导：
     * <p>
     * 第一步：3 ^ 5 = 6（无进位和），3 & 5 = 1（进位），进位左移 → 2
     * 第二步：6 ^ 2 = 4（无进位和），6 & 2 = 2（进位），进位左移 → 4
     * 第三步：4 ^ 4 = 0（无进位和），4 & 4 = 4（进位），进位左移 → 8
     * 第四步：0 ^ 8 = 8，进位为0 → 结果 8
     * <p>
     * <p>
     * 可以发现，对于整数 a 和 b：
     * 1. `在不考虑进位的情况下，其无进位加法结果为 a^b`。
     * 2. `而所有需要进位的位为 a & b，进位后的进位结果为 (a & b) << 1`。
     * <p>
     * 于是，我们可以将整数 a 和 b 的和，拆分为 a 和 b 的无进位加法结果与进位结果的和。`因为每一次拆分都可以让需要进位的最低位至少左移一位，又因为 a 和 b 可以取到负数，所以我们最多需要 log(max_int) 次拆分即可完成运算`。
     * <p>
     * 因为有符号整数用补码来表示，所以以上算法也可以推广到 0 和负数。
     *
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        while (b != 0) {
            int no_carry = a ^ b;
            int carry = (a & b) << 1;
            a = no_carry;
            b = carry;
        }
        return a;
    }
}
