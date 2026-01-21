package org.chris.leetcode.string;

import java.util.Stack;

public class IsValid_20 {
    /**
     * 20. 有效的括号
     *
     * @param args
     */
    public static void main(String[] args) {
        IsValid_20 isValid20 = new IsValid_20();
        String str = "()";
        System.out.println(str + " :" + isValid20.isValid(str));
        str = "()[]{}";
        System.out.println(str + " :" + isValid20.isValid(str));
        str = "(]";
        System.out.println(str + " :" + isValid20.isValid(str));
        str = "([])";
        System.out.println(str + " :" + isValid20.isValid(str));
        str = "([)]";
        System.out.println(str + " :" + isValid20.isValid(str));
        str = "((";
        System.out.println(str + " :" + isValid20.isValid(str));
        str = "){";
        System.out.println(str + " :" + isValid20.isValid(str));
        str = "))";
        System.out.println(str + " :" + isValid20.isValid(str));
    }

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.peek() != c) {
                return false;
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
