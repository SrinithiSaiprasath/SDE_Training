import java.util.HashSet;
import java.util.Arrays;

public class RemoveDuplicates {
    public static int[] removeDuplicates(int[] nums) {
        HashSet<Integer> uniqueElements = new HashSet<>();
        
        for (int num : nums) {
            uniqueElements.add(num);
        }
        
        int[] result = new int[uniqueElements.size()];
        int index = 0;
        for (int num : uniqueElements) {
            result[index++] = num;
        }
        
        Arrays.sort(result);
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 3, 4, 4, 5};
        int[] uniqueArray = removeDuplicates(nums);
        System.out.println("Array after removing duplicates: " + Arrays.toString(uniqueArray));
    }
}
