/**
1. Given an array of n numbers, give an algorithm for checking whether there are
any duplicate elements in the array or no?
 */

import java.util.HashSet;
import java.util.Set;
public class Q1 {
    public static boolean hasDuplicates(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        for (int num : arr) {
            if (!seen.add(num)) {
                return true; // Duplicate found
            }
        }
        return false; // No duplicates
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 1};
        System.out.println(hasDuplicates(arr)); // Output: true

        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println(hasDuplicates(arr2)); // Output: false
    }
}
/*Q2
 * 2. Can we improve the complexity of Problem-1’s solution?
 * Yes, the solution provided has a time complexity of O(n) and a space complexity of O(n) due to the use of a HashSet.
 * This is optimal for checking duplicates in an unsorted array.
 */