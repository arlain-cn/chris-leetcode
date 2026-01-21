package org.chris.leetcode.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FindLongestWord_524 {
    public static void main(String[] args) {
        System.out.println(new FindLongestWord_524().findLongestWord3("abpcplea", Arrays.asList("ale", "apple", "monkey", "plea")));
    }


    public String findLongestWord3(String s, List<String> dictionary) {
        int m = s.length();
        int[][] f = new int[m + 1][26];
        Arrays.fill(f[m], m);

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (s.charAt(i) == (char) (j + 'a')) {
                    f[i][j] = i;
                } else {
                    f[i][j] = f[i + 1][j];
                }
            }
        }
        /*
        System.out.println(Arrays.toString(f));
        for (int j = 0; j < f.length; j++) {
            int[] h = f[j];
            for (int i = 0; i < h.length; i++) {
                System.out.print(h[i] + " ");
            }
            System.out.println();
        }
        */
        String res = "";
        for (String word : dictionary) {
            boolean match = true;
            int j = 0;
            for (int i = 0; i < word.length(); i++) {
                if (f[j][word.charAt(i) - 'a'] == m) {
                    match = false;
                    break;
                } else {
                    j = f[j][word.charAt(i) - 'a'] + 1;
                }
            }
            if (match) {
                if (word.length() > res.length() || (word.length() == res.length() && word.compareTo(res) < 0)) {
                    res = word;
                }
            }
        }
        return res;
    }


    public String findLongestWord2(String s, List<String> dictionary) {
        int m = s.length();
        int[][] f = new int[m + 1][26];
        Arrays.fill(f[m], m);

        for (int i = m - 1; i >= 0; --i) {
            for (int j = 0; j < 26; ++j) {
                if (s.charAt(i) == (char) (j + 'a')) {
                    f[i][j] = i;
                } else {
                    f[i][j] = f[i + 1][j];
                }
            }
        }

        String res = "";
        for (String t : dictionary) {
            int j = 0;
            boolean match = true;
            for (int i = 0; i < t.length(); ++i) {
                if (f[j][t.charAt(i) - 'a'] == m) {
                    match = false;
                    break;
                }
                j = f[j][t.charAt(i) - 'a'] + 1;
            }
            if (match) {
                if (t.length() > res.length() || (t.length() == res.length() && t.compareTo(res) < 0)) {
                    res = t;
                }
            }
        }

        return res;
    }

    public String findLongestWord(String s, List<String> dictionary) {
        Collections.sort(dictionary, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                } else {
                    return o2.length() - o1.length();
                }
            }
        });

        for (String str : dictionary) {
            int i = 0, j = 0;
            char[] strArr = str.toCharArray();
            char[] sArr = s.toCharArray();
            while (i < strArr.length && j < sArr.length) {
                if (strArr[i] == sArr[j]) {
                    if (i == (strArr.length - 1)) {
                        return str;
                    }
                    i++;
                    j++;
                } else {
                    j++;
                }
            }
        }

        return "";
    }
}
