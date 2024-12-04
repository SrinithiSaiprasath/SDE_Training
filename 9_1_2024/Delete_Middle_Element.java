package Date_9_1_2024;

import java.util.Stack;

public class Delete_Middle_Element {
    private static void deleteMiddle(Stack<Integer> stack) {
        int currIndex = stack.size() / 2;
        if (currIndex == 0) {
            stack.pop();
            return;
        }
        currIndex--;
        int top = stack.pop();
        deleteMiddle(stack);
        stack.push(top);
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        deleteMiddle(s);
        System.out.println(s); // 3 will not be there
    }
} 