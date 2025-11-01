/**4. Given two arrays of unordered numbers, check	whether	both arrays have the same set of numbers.

Time Complexity: O(n). Space Complexity: O(n). */
import java.util.* ; 

public class Q4 {
    public static boolean haveSameSet(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false; 
        }
        
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        
        for (int num : arr1) set1.add(num); 
        for (int num : arr2) set2.add(num); 

        // Compare the two sets
        for(int num : set1) {
            if (!set2.contains(num)) {
                return false; 
            }
        }

        return true ; 
        
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {5, 4, 3, 2, 1};
        System.out.println(haveSameSet(arr1, arr2)); // Output: true

        int[] arr3 = {1, 2, 3};
        int[] arr4 = {4, 5, 6};
        System.out.println(haveSameSet(arr3, arr4)); // Output: false
    }
    
}
