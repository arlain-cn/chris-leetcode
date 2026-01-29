package org.chris.leetcode.binary;

import java.util.HashMap;
import java.util.Map;

public class SingleNumber_137 {

    /**
     * 137. 只出现一次的数字 II
     * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
     * <p>
     * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
     *
     * @param args
     */
    public static void main(String[] args) {
        SingleNumber_137 singleNumber137 = new SingleNumber_137();
        System.out.println(singleNumber137.singleNumber(new int[]{2, 2, 3, 2}));//3
        System.out.println(singleNumber137.singleNumber(new int[]{0, 1, 0, 1, 0, 1, 99}));//99
    }


    /**
     * 方法二：依次确定每一个二进制位
     * 思路与算法
     * 为了方便叙述，我们称「只出现了一次的元素」为「答案」。由于数组中的元素都在 int（即 32 位整数）范围内，因此我们可以依次计算答案的每一个二进制位是 0 还是 1。
     * 具体地，考虑答案的第 i 个二进制位（i 从 0 开始编号），它可能为 0 或 1。对于数组中非答案的元素，每一个元素都出现了 3 次，对应着第 i 个二进制位的 3 个 0 或 3 个 1，无论是哪一种情况，它们的和都是 3 的倍数（即和为 0 或 3）。因此：
     * <p>
     * `答案的第 i 个二进制位就是数组中所有元素的第 i 个二进制位之和除以 3 的余数`。
     * <p>
     * 这样一来，对于数组中的每一个元素 x，我们使用位运算 `(x >> i) & 1` 得到 x 的第 i 个二进制位，并将它们相加再对 3 取余，得到的结果一定为 0 或 1，即为答案的第 i 个二进制位。
     * <p>
     * 最后，`逐位将算出来的第i个二进制位挪到ans的第i位` 公式 `ans |= (1 << i);`
     * <p>
     * 细节
     * 需要注意的是，如果使用的语言对「有符号整数类型」和「无符号整数类型」没有区分，那么可能会得到错误的答案。这是因为「有符号整数类型」（即 int 类型）的第 31 个二进制位（即最高位）是补码意义下的符号位，对应着 −2^31，而「无符号整数类型」由于没有符号，第 31 个二进制位对应着 2^31。因此在某些语言（例如 Python）中需要对最高位进行特殊判断。
     *
     * @param nums
     * @return
     */
    public int singleNumber_0(int[] nums) {
        int ans = 0;
        // 遍历 32 位整数的每一位
        for (int i = 0; i < 32; ++i) {
            int total = 0;
            // 统计所有数字在第 i 位上的 1 的个数
            for (int num : nums) {
                total += ((num >> i) & 1);
            }
            // 如果某一位上的 1 的总个数不是 3 的倍数，说明只出现一次的那个数字在这一位上是 1
            // 因为其他出现三次的数字在这一位上的和肯定是 3 的倍数（0或3）
            if (total % 3 != 0) {
                // 将结果的第 i 位置为 1
                ans |= (1 << i);
            }
        }
        return ans;
    }

    /**
     * 方法一：哈希表
     * 思路与算法
     * <p>
     * 我们可以使用哈希映射统计数组中每个元素的出现次数。对于哈希映射中的每个键值对，键表示一个元素，值表示其出现的次数。
     * <p>
     * 在统计完成后，我们遍历哈希映射即可找出只出现一次的元素。
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return 0;
    }
}
