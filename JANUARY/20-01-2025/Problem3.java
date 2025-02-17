import java.util.* ;
public class Problem3 {
    public static void main(String[] args){
        int n = 23 ; 
        int[] edges = {4, 4, 1, 4, 13 ,8, 8, 8 ,0, 8, 14, 9, 15, 11, -1, 10, 15, 22, 22, 22 ,22, 22, 21} ; 
        int x1 = 9 ;
        int x2 = 2;
        int result = minweight(n , edges , x1 , x2) ;
        System.out.println(result) ; 

    }

    public static int minweight(int n ,int[] edges , int x1 , int x2){
        List<List<Integer>> graph = new ArrayList<>() ; 
        for(int i = 0 ; i < n ; i++){
            graph.add(new ArrayList<>()) ;
        }
        for(int i = 0 ; i < n ;i++){
            if(edges[i] != -1){
                graph.get(i).add(edges[i]) ; 
            }
        }
        long[] x1_path = new long[n] ; 
        long[] x2_path = new long[n] ; 

        Arrays.fill(x1_path , Long.MAX_VALUE) ;
        Arrays.fill(x2_path , Long.MAX_VALUE) ; 

        dijkstra(graph , x1_path , x1) ; 
        dijkstra(graph , x2_path , x2) ;

        int node = 0 ;
        long dist = Long.MAX_VALUE ;
        for(int i = 0 ; i < n ; i++){
            if(x1_path[i] == Long.MAX_VALUE || x2_path[i] == Long.MAX_VALUE) continue ;
            if(x1_path[i] + x2_path[i]  < dist){
                dist = x1_path[i] + x2_path[i] ;
                node = i;
            }
        }
        if(dist == Long.MAX_VALUE) return -1 ; 
        return node ; 
    }

    public static void dijkstra(List<List<Integer>> graph , long[] distance , int pos ){
        PriorityQueue<Integer> pq = new PriorityQueue<>() ; 
        pq.offer(pos) ; 
        distance[pos] = 0 ;
        while(pq.isEmpty()){
            int currnode = pq.poll() ;
             for(int next : graph.get(currnode)){
                long dist = distance[currnode] +1 ;
                if(dist < distance[next]){
                    distance[next] = dist ; 
                    pq.offer(next) ; 
                }
            }
        }
    }
}
