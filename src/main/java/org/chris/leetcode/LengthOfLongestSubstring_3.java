package org.chris.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring_3 {

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
     * 示例 2:
     * <p>
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * <p>
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * @param args
     */
    public static void main(String[] args) {
        LengthOfLongestSubstring_3 lengthOfLongestSubstring3 = new LengthOfLongestSubstring_3();
        System.out.println(lengthOfLongestSubstring3.lengthOfLongestSubstring2("abcabcbb"));
        System.out.println(lengthOfLongestSubstring3.lengthOfLongestSubstring2("bbbbb"));
        System.out.println(lengthOfLongestSubstring3.lengthOfLongestSubstring2("pwwkew"));

        // 测试最优解法
        System.out.println("Optimal solution:");
        System.out.println(lengthOfLongestSubstring3.lengthOfLongestSubstring3("abcabcbb"));
        System.out.println(lengthOfLongestSubstring3.lengthOfLongestSubstring3("bbbbb"));
        System.out.println(lengthOfLongestSubstring3.lengthOfLongestSubstring3("pwwkew"));
    }


    /**
     * 这样一来，我们就可以使用「滑动窗口」来解决这个问题了：
     * <p>
     * 我们使用两个指针表示字符串中的某个子串（或窗口）的左右边界，其中左指针代表着上文中「枚举子串的起始位置」，而右指针即为上文中的 r
     * k
     * ​
     * ；
     * <p>
     * 在每一步的操作中，我们会将左指针向右移动一格，表示 我们开始枚举下一个字符作为起始位置，然后我们可以不断地向右移动右指针，但需要保证这两个指针对应的子串中没有重复的字符。在移动结束后，这个子串就对应着 以左指针开始的，不包含重复字符的最长子串。我们记录下这个子串的长度；
     * <p>
     * 在枚举结束后，我们找到的最长的子串的长度即为答案。
     * <p>
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters/solutions/227999/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetc-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int ans = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }


    public int lengthOfLongestSubstring2(String s) {
        int ans = 0;
        int left = 0, right = 0;
        Set<Character> set = new HashSet<>();
        while (right < s.length()) {
            if (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            } else {
                set.add(s.charAt(right));
                ans = Math.max(ans, right - left + 1);
                right++;
            }
        }
        return ans;
    }

    /**
     * ⏺ 我看到现有的两个实现方法都使用了HashSet来检测重复字符，但它们的时间复杂度和空间复杂度确实可以进一步优化。让我为你实现一个更优的滑动窗口解决方案，使用数组代替HashSet来提高性能。
     * 最优解法：使用滑动窗口 + 数组记录字符最后出现的位置
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
        // 使用数组记录每个字符最后出现的位置，初始化为-1
        //假设字符都是ASCII码，共128个字符
        int[] setArr = new int[128];
        Arrays.fill(setArr, -1);

        int ans = 0;
        int left = 0, right = 0;

        while (right < s.length()) {
            if (setArr[s.charAt(right)] != -1) {
                setArr[s.charAt(left)] = -1;
                left++;
            }else {
                setArr[s.charAt(right)] = right;
                ans = Math.max(ans, right - left + 1);
                right++;
            }
        }
        return ans;
    }


    /**
     * 最优解法：使用滑动窗口 + 数组记录字符最后出现的位置
     * 时间复杂度：O(n)，其中 n 是字符串的长度
     * 空间复杂度：O(1)，因为字符集大小是固定的（ASCII码最多128个字符）
     *
     * @param s 输入字符串
     * @return 不含重复字符的最长子串的长度
     */
    public int lengthOfLongestSubstringOptimal(String s) {
        // 使用数组记录每个字符最后出现的位置，初始化为-1
        // 假设字符都是ASCII码，共128个字符
        int[] lastSeen = new int[128];
        for (int i = 0; i < 128; i++) {
            lastSeen[i] = -1;
        }

        int maxLength = 0;
        int start = 0; // 滑动窗口的起始位置

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);

            // 如果当前字符之前出现过，并且在当前窗口内
            if (lastSeen[currentChar] >= start) {
                // 将窗口起始位置移动到重复字符的下一个位置
                start = lastSeen[currentChar] + 1;
            }

            // 更新当前字符最后出现的位置
            lastSeen[currentChar] = end;

            // 更新最大长度
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }


}
