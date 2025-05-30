package coding;

public class Q2 {
    public static long solve(int n, int k, int x) {
        long[][] dp = new long[n + 1][k + 1];
        long mod = 1000_0000_07;

        for (int i = 1 ; i <= k; i++) {
            dp[1][i] = 1;
        }
        
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int prev = 1; prev <= k; prev++) {
                    if (j != prev) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][prev]) % mod;                 
                    } else if (j == x) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][prev]) % mod;
                    }
                }
            }
        }

        long total = 0;
        for (int i = 1; i <= k; i++) {
            total = (total + dp[n][i]) % mod;
        }

        return total;


    }

    public static void main(String[] args){
        int n = 3 ;
        int k = 2 ;
        int x = 1 ;
        System.out.println(solve(n, k, x));
    }
}

