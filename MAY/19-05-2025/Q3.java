/**3. Find two elements whose sum is closest to zero: Given an array with both
positive and negative numbers, find the two elements such that their sum is closest to zero.
For the below array, algorithm should give -80 and 85. 
Example: 1 60 – 10 70 – 80 85  */

public class Q3 {
    public static int[] findClosestToZero(int[] arr) {
        if (arr.length < 2) {
            return new int[0]; // Not enough elements
        }

        int closestSum = Integer.MAX_VALUE;
        int[] result = new int[2];
        
        // Sort the array to use two-pointer technique
        java.util.Arrays.sort(arr);
        
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (Math.abs(sum) < Math.abs(closestSum)) {
                closestSum = sum;
                result[0] = arr[left];
                result[1] = arr[right];
            }
            
            if (sum < 0) {
                left++; // Move left pointer to increase sum
            } else {
                right--; // Move right pointer to decrease sum
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 60, -10, 70, -80, 85};
        int[] closestPair = findClosestToZero(arr);
        System.out.println("Elements with sum closest to zero: " + closestPair[0] + " and " + closestPair[1]);
    }
}
