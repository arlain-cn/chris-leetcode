package org.chris.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindAnagrams_438 {

    public static void main(String[] args) {
        FindAnagrams_438 findAnagrams438 = new FindAnagrams_438();
        System.out.println(findAnagrams438.findAnagrams("cbaebabacd", "abc"));
        System.out.println(findAnagrams438.findAnagrams("abab", "ab"));
    }

    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) {
            return Collections.emptyList();
        }

        List<Integer> ans = new ArrayList<>();
        int[] differ = new int[26];
        int[] blank = new int[26];
        for (int i = 0; i < pLen; i++) {
            differ[s.charAt(i) - 'a']++;
            differ[p.charAt(i) - 'a']--;
        }

        if (Arrays.equals(differ, blank)) {
            ans.add(0);
        }

        for (int i = 0; i < sLen - pLen; i++) {
            differ[s.charAt(i) - 'a']--;
            differ[s.charAt(i + pLen) - 'a']++;
            if (Arrays.equals(differ, blank)) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    public List<Integer> findAnagrams3(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) {
            return Collections.emptyList();
        }

        List<Integer> ans = new ArrayList<>();
        int[] sCountArr = new int[26];
        int[] pCountArr = new int[26];
        for (int i = 0; i < pLen; i++) {
            sCountArr[s.charAt(i) - 'a']++;
            pCountArr[p.charAt(i) - 'a']++;
        }
        if (Arrays.equals(sCountArr, pCountArr)) {
            ans.add(0);
        }

        for (int i = 0; i < sLen - pLen; i++) {
            sCountArr[s.charAt(i) - 'a']--;
            sCountArr[s.charAt(i + pLen) - 'a']++;

            if (Arrays.equals(sCountArr, pCountArr)) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        int pl = p.length();
        int sl = s.length();
        if (pl > sl) {
            return Collections.emptyList();
        }

        List<Integer> ans = new ArrayList<>();
        for (int left = 0, right = pl - 1; right < sl; left++, right++) {
            int[] setArr = new int[26];
            Arrays.fill(setArr, 0);
            for (int i = 0; i < pl; i++) {
                setArr[p.charAt(i) - 'a']++;
            }

            boolean match = true;
            for (int i = left; i <= right; i++) {
                if (setArr[s.charAt(i) - 'a'] == 0) {
                    match = false;
                    break;
                } else {
                    setArr[s.charAt(i) - 'a']--;
                }
            }
            if (match) {
                ans.add(left);
            }
        }
        return ans;
    }
}
