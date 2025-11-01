import java.util.*;

public class TripletSum {
    public static boolean findTriplet(int[] arr, int target) {
        Arrays.sort(arr);
        
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1, right = arr.length - 1;
            
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                
                if (sum == target) return true;
                else if (sum < target) left++;
                else right--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 45, 6, 10, 8};
        int target = 22;
        System.out.println("Triplet found: " + findTriplet(arr, target));
    }
}
