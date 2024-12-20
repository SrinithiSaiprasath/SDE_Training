package Date_9_1_2024;

import java.util.*;

public class NextGreater_Element {
    public static HashMap<Integer, Integer> findNextGreaterElements(int[] arr) {
        HashMap<Integer, Integer> res = new HashMap<>();
        Stack<Integer> check_stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            int curr = arr[i];
            while (!check_stack.isEmpty() && check_stack.peek() < curr) {
                res.put(check_stack.pop(), curr);
            }
            check_stack.push(curr);
        }
        while (!check_stack.isEmpty()) {
            res.put(check_stack.pop(), -1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = { 4, 5, 2, 25 };
        System.out.println("Next Greater Elements:");
        HashMap<Integer, Integer> result1 = findNextGreaterElements(arr1);
        for (int num : arr1) {
            System.out.println(result1.get(num));
        }
    }
}