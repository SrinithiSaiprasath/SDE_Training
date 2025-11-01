import java.util.*;

public class EqualArrays {
    public static boolean areEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) return false;
        
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        
        for (int num : arr1) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        for (int num : arr2) {
            if (!frequencyMap.containsKey(num) || frequencyMap.get(num) == 0) {
                return false;
            }
            frequencyMap.put(num, frequencyMap.get(num) - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {5, 4, 3, 2, 1};
        System.out.println("Arrays are equal: " + areEqual(arr1, arr2));
    }
}
