public class problem2 {
    public static void main(String[] args){
        int[][] arr = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        System.out.println(checkIdempotent(arr)); 

        int[][] arr2 = {
            {1, 2},
            {3, 4}
        };
        System.out.println(checkIdempotent(arr2)); 
    }

    public static boolean checkIdempotent(int[][] arr){
        int n = arr.length;
        int[][] temp = new int[n][n]; 
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                temp[i][j] = 0; 
                for(int k = 0; k < n; k++){
                    temp[i][j] += arr[i][k] * arr[k][j]; 
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(temp[i][j] != arr[i][j]){
                    return false; 
                }
            }
        }
        return true ; 
    }
}