

import java.util.* ;

public class problem111 {
    public static void mian(String[] args){
        int[] edges = {1, 2, -1, 1, 2}; 
        int[] temp = new int[edges.length] ; 
        for(int i = 0; i < edges.length ; i++){
            if(edges[i] != -1){
                temp[edges[i]] += i ; 
            }
        }
        int max = Integer.MIN_VALUE ;
        int res = -1 ; 
        for(int i = 0 ; i < temp.length ; i++){
            if(temp[i] > max){
                max = temp[i] ; 
                res = i ;
            }
        }
        if(max != Integer.MIN_VALUE) System.out.println(res) ;
        else System.out.println(-1) ;
    }
}
