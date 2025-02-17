// find largest cycle sum
import java.util.* ;

public class solution2 {
    public static void main(String[] args){
        // int[] edges = {} ; 
        int n = 8 ;
        int[] edges = {1,2,3,4,1,6,-1,1} ;
        long result = largestSumCycle(n, edges) ;
        System.out.println(result) ;
    }

    public static long largestSumCycle(int n , int[] edges){
        int[] indeg = new int[n] ; 
        for(int node : edges){
            if(node !=-1){
                indeg[node]++ ; 
            }
        }

        bfs(edges, indeg) ; 
        long res = -1 ; 
        for(int i =0  ; i < n ; i++){
            if(indeg[i] !=0){
                res = Math.max(res, findsum_from_node(i, edges, indeg)) ; 
            }
        }
        return res ; 
    }
    public static void bfs(int[] edges , int[] indeg){
        Queue<Integer> pq = new LinkedList<>() ;
         int n = indeg.length ; 
         for(int i = 0 ; i < n ; i++){
            if(indeg[i]==0){
                pq.add(i) ; 
            }
         }
         while(!pq.isEmpty()){
            int size = pq.size() ;
             for(int i = 0 ; i < size ; i++){
                int node = pq.poll() ; 
                if(edges[node] == -1) continue ; 
                indeg[edges[node]]-- ; 
                if(indeg[edges[node]]==0){
                    pq.add(edges[node]) ; 
                }
             }
        }
    }

    public static long findsum_from_node(int node ,int[] edges ,int[] indeg){
        long sum = node ; 
        indeg[node]-- ; 
        Queue<Integer> pq = new LinkedList<>() ;
        pq.add(edges[node]) ; 
        while(pq.peek() != node){
            sum += pq.peek() ; 
            pq.add(edges[pq.poll()]) ; 
            indeg[node]-- ; 
        }
        return sum ; 
    }

}
