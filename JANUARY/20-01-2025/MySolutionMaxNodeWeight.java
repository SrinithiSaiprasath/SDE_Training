// package JANUARY.20-01-2025;

public class MySolutionMaxNodeWeight {
    public static void main(String[] args) {
        int arr[] = {2 , 0 -1 , 2} ; 
        int[] temp = new int[arr.length] ;
        for(int i = 0 ; i < arr.length ;i++){
            if(arr[i] != -1){
                temp[arr[i]] += i ; 
            }
        }    
        int ans= 0 ;
        int max = Integer.MIN_VALUE ;
        for(int i = 0 ; i < temp.length ; i++){
            if(temp[i] > max){
                max = temp[i] ; 
                ans = i ; 
            }
        }
        if(max != Integer.MIN_VALUE){
            System.out.println(ans) ;
        }else{
            System.out.println("-1") ;
        }
    }
}
