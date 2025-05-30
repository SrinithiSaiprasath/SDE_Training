// # 3.  Given two arrays each containing n sorted elements, give an	
// # O(logn)-time algorithm	to find	the median of	all	2n	elements.
// # A = [1, 3, 8, 9]
// # B = [2, 4, 7, 10]
// # Combined: [1, 2, 3, 4, 7, 8, 9, 10]
// # Median = (4 + 7) / 2 = 5.5
// # Expected Output: 5.5 */

public class Q3 {
    public static double findMedianSortedArrays(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;

        if (n > m) {
            return findMedianSortedArrays(B, A); // Ensure A is the smaller array
        }

        int low = 0, high = n;
        while (low <= high) {
            int partitionA = (low + high) / 2;
            int partitionB = (n + m + 1) / 2 - partitionA;

            int maxLeftA = (partitionA == 0) ? Integer.MIN_VALUE : A[partitionA - 1];
            int minRightA = (partitionA == n) ? Integer.MAX_VALUE : A[partitionA];

            int maxLeftB = (partitionB == 0) ? Integer.MIN_VALUE : B[partitionB - 1];
            int minRightB = (partitionB == m) ? Integer.MAX_VALUE : B[partitionB];

            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                // Found the correct partitions
                if ((n + m) % 2 == 0) {
                    return (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2.0;
                } else {
                    return Math.max(maxLeftA, maxLeftB);
                }
            } else if (maxLeftA > minRightB) {
                high = partitionA - 1; // Move towards left in A
            } else {
                low = partitionA + 1; // Move towards right in A
            }
        }
        return 0.0 ;
    }

    public static void main(String[] args) {
        int[] A = {1, 3, 8, 9};
        int[] B = {2, 4, 7, 10};
        System.out.println("Median: " + findMedianSortedArrays(A, B)); // Output: Median: 5.5
    }
}