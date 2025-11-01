package Date_9_1_2024;

public class Boolean_Matrix {
    public static void main(String[][] args) {
        int[][] bm = {{9,9,4},{6,6,8},{2,1,1}};
        int[][] dp = new int[bm.length][bm[0].length];
        int max = 0;
        for (int i = 0; i < bm.length; i++){
            for (int j = 0; j < bm[i].length; j++){
                dfs(i, j, bm, dp, bm[i][j] - 1);
                max = Math.max(max, dp[i][j]);
            }
        }
        System.out.println(max);
    }
    
    public static int dfs(int i, int j, int[][] matrix, int[][] dp, int prevValue){
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length || matrix[i][j] <= prevValue)
            return 0;
        if (dp[i][j] != 0)
            return dp[i][j];
        int up = dfs(i-1, j, matrix, dp, matrix[i][j]); 
        int down = dfs(i+1, j, matrix, dp, matrix[i][j]);
        int left = dfs(i, j-1, matrix, dp, matrix[i][j]);
        int right = dfs(i, j+1, matrix, dp, matrix[i][j]);
        dp[i][j] = 1 + Math.max(Math.max(up, down), Math.max(left,right));
        return dp[i][j];
    }
}