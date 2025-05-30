package coding;
import java.util.* ;

//Bin & Array Operations

public class Q1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in) ; 
        int N = sc.nextInt()  ; 
        int[] arr = new int[N] ; 
        for(int i = 0 ; i < N ; i++ ){
            arr[i] =  sc.nextInt() ; 
        }
        sc.close();
    }

    public static int[] BinArrayOps(int k , int[] arr){
        int[] res = new int[arr.length] ;
        int i = 0 ; 
        while(k>0){
            if(isPrime(arr[i])) arr[i] -= 1 ; 
            else arr[i] += 1 ; 
            i = (i+1)%arr.length ; 
            k-- ; 
        } 
        return res ; 
    }

    public static boolean isPrime(int n){
        if(n == 1 || n== 2 ) return true ; 
        for(int i = 2 ; i <= n/2 ; i++){
            if(n%i == 0){
                return false;
            }
        }
        return true ; 
    }
}
