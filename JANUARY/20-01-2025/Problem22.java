
import java.util.*;
public class Problem22 {
    public static void main(String[] args){
        int n = 8 ; 
        int[] edges = {1,2,3,4,1,6,-1,1} ;
        long result = largestCycleSum(n , edges) ;
        System.out.println(result) ;
    }
    public static long largestCycleSum(int n , int[] edges){
        int[] indeg = new int[n] ; 
        for(int node : edges){
            if(node != -1){
                indeg[node]++ ; 
            }
        }
        long sum = -1 ; 
        return sum ; 
    }
    public static void bfs(int[] edges ,int[] indeg){
        Queue<Integer> pq =new LinkedList<>() ; 
        for(int i = 0 ; i < indeg.length ; i++){
            if(indeg[edges[i]] == 0){
                pq.add(i); 
            }
        }

        while(!pq.isEmpty()){
            int size = pq.size() ; 
            for(int i = 0 ; i < size ; i++){
                int edge = edges[pq.poll()] ; 
                if(edge == -1) continue ;
                indeg[edge]--  ;
                if(indeg[edges[edge]] == 0) pq.add(edges[edge]) ; 
            }
        }
    }

    public static long findSum(int node,int[] edges , int[] indeg){
        long sum = node ; 
        indeg[node]-- ;
        Queue<Integer> pq = new LinkedList<>() ; 
        pq.add(node) ; 
        while(pq.peek() != node){
            sum = pq.peek() ;
            pq.add(edges[pq.poll()]) ; 
            indeg[node]-- ;
        }
        return sum ; 
    }
}
