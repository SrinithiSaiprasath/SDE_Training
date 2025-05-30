import java.io.*;
import java.util.*;

public class Solution2{
    public static int solution(int N, int K, int[] money) {
        // Count initial followers - people who already have K money
        int initialFollowers = 0;
        for (int i = 0; i < N; i++) {
            if (money[i] == K) {
                initialFollowers++;
            }
        }
        
        int maxFollowers = initialFollowers;
        
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                // For each subarray, we need to find the best value X to add
                // Count frequencies of (K - money[i]) for elements in the subarray
                // Because if we add (K - money[i]) to the subarray, then money[i] becomes K
                Map<Integer, Integer> diffFreq = new HashMap<>();
                
                for (int i = L; i <= R; i++) {
                    int diff = K - money[i];
                    diffFreq.put(diff, diffFreq.getOrDefault(diff, 0) + 1);
                }
                
                // For each possible value of X we can add
                for (Map.Entry<Integer, Integer> entry : diffFreq.entrySet()) {
                    int countInSubarray = entry.getValue(); // How many elements in subarray will become K
                    
                    // Count how many elements outside the subarray already equal K
                    int countOutside = 0;
                    for (int i = 0; i < N; i++) {
                        if (i < L || i > R) {
                            if (money[i] == K) {
                                countOutside++;
                            }
                        }
                    }
                    
                    int totalFollowers = countInSubarray + countOutside;
                    maxFollowers = Math.max(maxFollowers, totalFollowers);
                }
            }
        }
        
        return maxFollowers;
    }
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        int[] money = new int[N];
        for (int i = 0; i < N; i++) {
            money[i] = sc.nextInt();
        }

        int result = solution(N, K, money);
        System.out.println(result);
        
        sc.close();
    }
}