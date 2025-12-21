package org.chris.leetcode;

import java.util.Arrays;

public class RotateArr_189 {
    public static void main(String[] args) {
        RotateArr_189 rotateArr189 = new RotateArr_189();

        // 测试三次反转法
        int[] nums1 = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotateArr189.rotate(nums1, 3);
        System.out.println("三次反转法结果: " + Arrays.toString(nums1));

        // 测试环状替换法
        int[] nums2 = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotateArr189.rotate2(nums2, 3);
        System.out.println("环状替换法结果: " + Arrays.toString(nums2));

        System.out.println("gcd(3, 6) = " + rotateArr189.gcd(3, 6));
    }


    /**
     * 三次反转法：为什么先反转整个数组，再反转前k个，最后反转后n-k个能实现轮转？（用小例子模拟：nums = [1,2,3,4,5,6,7], k=3，记录每步数组状态）
     * 空间复杂度：与使用额外O(n)空间的解法相比，三次反转法的空间复杂度是多少？（提示：O(1)）
     *
     * @param nums 输入数组
     * @param k    轮转步数
     * @see 该方法基于如下的事实：当我们将数组的元素向右移动 k 次后，尾部 kmodn 个元素会移动至数组头部，其余元素向后移动 kmodn 个位置。
     * <p>
     * 该方法为数组的翻转：我们可以先将所有元素翻转，这样尾部的 kmodn 个元素就被移至数组头部，然后我们再翻转 [0,kmodn−1] 区间的元素和 [kmodn,n−1] 区间的元素即能得到最后的答案。
     */
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k %= len;
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    public void reverse(int[] nums, int left, int right) {
        while (right > left) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }

    /**
     * k的有效性：当k大于数组长度n时，实际需要轮转的步数是多少？（提示：k % n，例如n=7, k=10时只需轮转3步）
     * O(n)解法
     *
     * @param nums 输入数组
     * @param k    轮转步数
     */
    public void rotate1(int[] nums, int k) {
        int len = nums.length;

        int[] temp = Arrays.copyOfRange(nums, 0, len);
        k = k % len;
        for (int i = k; i < len; i++) {
            nums[i] = temp[i - k];
        }

        for (int i = 0; i < k; i++) {
            nums[i] = temp[i + len - k];
        }
    }

    /**
     * 求最大公约数 (GCD) ：
     * 欧几里得算法 (Euclidean Algorithm) - 最常用，这是最经典、最高效的算法，基于以下原理：
     * gcd(a, b) = gcd(b, a mod b)直到 b = 0，此时 a 即为最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    public int gcd(int a, int b) {
        if (b == 0) {
            return Math.abs(a);  // 处理负数
        }
        return gcd(b, a % b);
    }

    /**
     * 方法二：环状替换
     * <p>
     * 解题思路：
     * 1. 数组向右轮转 k 个位置，等价于将每个元素从位置 i 移动到位置 (i+k)%n
     * 2. 我们可以通过不断交换的方式，把每个元素放到它最终应该在的位置上
     * 3. 通过数学知识可知，如果从位置0开始，连续进行这样的移动，会在若干次后回到起点，
     * 形成一个环。这个环的长度等于 n/gcd(n,k)
     * 4. 我们需要进行 gcd(n,k) 个这样的环才能遍历完所有元素
     * <p>
     * 时间复杂度：O(n)，虽然有嵌套循环，但每个元素只被访问一次
     * 空间复杂度：O(1)，只使用了常数级别的额外空间
     * <p>
     * <p>
     * 每次移动k步，走了a圈，所以总移动步数是a * n（因为一圈是n步，但这里不是物理步数，而是索引变化）。
     * 实际上，移动的总次数是b（遍历的元素个数），每次移动k步，所以总移动距离是b * k。
     * 由于回到了起点，总移动距离必须是n的整数倍（因为模n后回到原点），所以b * k = a * n。
     * <p>
     * 这是一个著名的数论恒等式。我们可以从最小公倍数和最大公约数的定义和关系来理解。
     * 回顾：对于两个正整数 a和 b，它们的最小公倍数 lcm(a,b)和最大公约数 gcd(a,b)满足以下关系：
     * a⋅b=gcd(a,b)⋅lcm(a,b).
     *
     * @param nums 输入数组
     * @param k    轮转步数
     */
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // 处理k大于数组长度的情况

        // 特殊情况：k为0时不需旋转
        if (k == 0) {
            return;
        }

        // 计算需要处理的环的数量
        int count = gcd(n, k);

        // 对每个环进行处理
        for (int start = 0; start < count; start++) {
            int current = start;           // 当前处理的位置
            int prev = nums[start];        // 当前位置的值

            // 在当前环中不断移动元素，直到回到起点
            do {
                int next = (current + k) % n;  // 计算下一个位置
                int temp = nums[next];         // 保存下一个位置的原始值
                nums[next] = prev;             // 将当前位置的值放到下一个位置
                prev = temp;                   // 更新prev为下一个位置的原始值
                current = next;                // 移动到下一个位置
            } while (current != start);        // 当回到起点时，当前环处理完成
        }
    }
}
