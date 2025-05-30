/*4. Given an array of n elements. Find three elements in the array such that their sum
is equal to given element K? */

public class Q4 {
    public static boolean findThreeElementsWithSum(int[] arr, int k) {
        if (arr.length < 3) {
            return false; // Not enough elements
        }

        // Sort the array to use two-pointer technique
        java.util.Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == k) {
                    System.out.println("Elements found: " + arr[i] + ", " + arr[left] + ", " + arr[right]);
                    return true; // Found the triplet
                } else if (sum < k) {
                    left++; // Move left pointer to increase sum
                } else {
                    right--; // Move right pointer to decrease sum
                }
            }
        }

        return false; // No triplet found
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int k = 10;
        boolean result = findThreeElementsWithSum(arr, k);
        System.out.println("Triplet with sum " + k + ": " + result);
    }
}
