//Bottom up approach
public class fibonacci_V2 {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(fibonacci_so(n));

    }

    public static int fibonacci(int n) {
        int res = 0;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        if (n <= 1)
            res = n;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        res = dp[n];
        return res;
    }

    public static int fibonacci_so(int n) {
        int prev = 0;
        int curr = 1;
        int next = 0;
        if (n <= 1)
            return n;
        for (int i = 2; i < n + 1; i++) {
            next = prev + curr;
            prev = curr;
            curr = next;
        }
        return next;
    }

}
