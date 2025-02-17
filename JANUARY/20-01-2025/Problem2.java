
import java.util.*;

public class Problem2 {
    public static void main(String[] args){

    }
    public static long largestSumCy(int n , int[] edges){
        int[] indeg = new int[n] ; 
        for(int node : edges){
            if(node != -1){
                indeg[node]++ ; 
            }
        }
        long msum = -1 ; 
        //now remove nodes with indeg 0
        for(int i = 0 ; i < n ; i++){
            if(indeg[i] == 1 ){
                msum = Math.max(msum , helper(i , indeg , edges)) ;
            }
        }
        return msum ; 
    }

    public static void bfs(int n , int[] indeg , int[] edges){
        Queue<Integer> pq = new LinkedList<>() ; 
        for(int i = 0 ; i < n ; i++){
            if(indeg[edges[i]] == 0){
                pq.add(i) ; 
            }
        }
        while(!pq.isEmpty()){
            int s = pq.size() ; 
            for(int  i = 0  ; i < s ; i++){
                int node = pq.poll() ; 
                if(edges[node] == -1) continue ; 
                indeg[edges[node]]-- ; 
                if(indeg[edges[node]]== 0) {
                    pq.add(edges[node]) ; 
                }
            }
        }    
    }

    public static long helper(int node , int[] indeg , int[] edges){
        long sum = node ;
        int edge = edges[node] ;
        Queue<Integer> pq = new LinkedList<>() ;
        indeg[node]-- ;
        pq.add(edge) ;
        while(pq.peek()!= node){
            sum += pq.peek() ; 
            pq.add(edges[pq.poll()]) ; 
            indeg[node]-- ;
        }
        return sum ;
    }
}
