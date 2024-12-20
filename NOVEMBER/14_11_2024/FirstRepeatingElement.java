import java.util.*;

public class FirstRepeatingElement {
    public static int firstRepeating(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();
        
        for (int num : nums) {
            if (seen.contains(num)) {
                return num;  
            }
            seen.add(num);
        }
        
        return -1;  
    }

    public static void main(String[] args) {
        int[] nums = {5, 3, 4, 3, 5, 6};
        int firstRepeating = firstRepeating(nums);
        System.out.println("First repeating element: " + firstRepeating);
    }
}
