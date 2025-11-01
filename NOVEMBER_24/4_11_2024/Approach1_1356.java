import java.util.Arrays;

public class Approach1_1356 {
    public static void main(String[] args){
        int[] arr  = {1 ,2,4,5,8,6,14,41,42} ; 
        int[][] res = new int[arr.length][2] ;

        for(int i = 0  ; i < arr.length ; i++){
            res[i][0] = Integer.bitCount(arr[i]);
            res[i][1] = arr[i] ; 
        }

        Arrays.sort(res, (x, y) ->
            (x[0] == y[0])? (x[1] - y[1]) : (x[0] - y[0])
        );

        for(int i = 0 ; i < res.length ; i++){
            System.out.println(res[i][1]);
        }
    }
}
