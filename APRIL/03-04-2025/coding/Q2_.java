package coding;

public class Q2_ {
    public static long solution(int n , int k , int x){
        long[][] dp = new long[n+1][k+1];
        long mod = 1_000_000_007 ; 

        for(int i = 1 ; i<= k ; i++){
            dp[1][i] = 1 ; 
        }

        for(int i = 2 ; i <= n ; i++){
            for(int j = 1 ; j <= k ; j++){
                for(int prev = 1 ; prev <= k ; prev++){
                    if(prev != j || prev == x){
                        dp[i][j] = dp[i][j] + dp[i-1][prev] % mod ; 
                    }
                }
            }
        }
        long total = 0 ; 
        for(int i = 1 ; i <= k ; i++){
            total =  total +  (dp[n][i] % mod) ; 
        }
        return total ; 
    }

}
