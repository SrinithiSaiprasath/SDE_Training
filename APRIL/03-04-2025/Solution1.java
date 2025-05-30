import java.io.*;
import java.util.Scanner;

public class Solution1{
    
    static final int MOD = 1000000007;
    
    public static int solve(int K, int[] arr, int N) {
        // Calculate total sum of all elements
        long totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }
        
        // If total sum is less than 2*K, it's impossible to have both parts > K
        if (totalSum <= 2L * K) {
            return 0;
        }
        
        // Initialize ways to count valid distributions
        int ways = 0;
        int totalDistributions = 1 << N; // 2^N
        
        for (int mask = 1; mask < totalDistributions - 1; mask++) {
            // Calculate sum of first subset (elements where bit is set)
            long sum1 = 0;
            for (int i = 0; i < N; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum1 += arr[i];
                }
            }
            
            // Calculate sum of second subset (remaining elements)
            long sum2 = totalSum - sum1;
            
            // Check if both sums are greater than K
            if (sum1 > K && sum2 > K) {
                ways = (ways + 1) % MOD;
            }
        }
        
        return ways;
    }
    
    // Main method for handling input/output
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int result = solve(K, arr, N);
        System.out.println(result);
        
        sc.close();


    }
}