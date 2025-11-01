package Date_9_1_2024;

import java.util.Stack;

public class Balance_Parenthesis {
    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String str1 = "((()))()()";
        System.out.println((isBalanced(str1) ? "Balanced" : "Not Balanced"));
    }
}
