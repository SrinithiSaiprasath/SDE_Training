/**5. An array is bitonic if it is comprised of an increasing sequence
of integers followed immediately by a decreasing sequence of integers. Given a bitonic
array A of n distinct integers, describe how to determine whether a given integer is in the
array in O(logn) steps.
[1, 2, 3, 2, 1]
[0, 1, 2, 3, 2, 1, 0] */

public class Q5 {
    public static boolean isBitonic(int[] arr, int target) {
        int peakIndex = findPeak(arr);
        if (peakIndex == -1) {
            return false; // Not a bitonic array
        }
        
        if (binarySearch(arr, 0, peakIndex, target, true)) {
            return true;
        }
        
        return binarySearch(arr, peakIndex + 1, arr.length - 1, target, false);
    }

    private static int findPeak(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return -1; 
    }

    private static boolean binarySearch(int[] arr, int left, int right, int target, boolean isIncreasing) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return true; 
            }
            if (isIncreasing) {
                if (arr[mid] < target) {
                    left = mid + 1; 
                } else {
                    right = mid - 1; 
                }
            } else {
                if (arr[mid] < target) {
                    right = mid - 1; 
                } else {
                    left = mid + 1; 
                }
            }
        }
        return false; 
    }

    public static void main(String[] args) {
        int[] bitonicArray = {0, 1, 2, 3, 2, 1, 0};
        int target = 2;
        System.out.println(isBitonic(bitonicArray, target)); // Output: true

        target = 4;
        System.out.println(isBitonic(bitonicArray, target)); // Output: false
    }
}
