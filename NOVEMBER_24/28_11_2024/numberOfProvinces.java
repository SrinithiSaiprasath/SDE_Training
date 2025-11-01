import java.util.ArrayList;

public class numberOfProvinces{
    public static void main(String[] args) {
    }

    public static void dfs(ArrayList<ArrayList<Integer>> adj , int i , int[] vis){
        
    }
    public static int numprov(ArrayList<ArrayList<Integer>> adj , int V ){
        ArrayList<ArrayList<Integer>> adjL =  new ArrayList<ArrayList<Integer>>() ;
        for(int i =0 ; i < V ; i++){
            adjL.add(new ArrayList<Integer>());
        }

        for(int i = 0; i < V ; i++){
            for(int j = 0 ; j < V ; j++){
                if(adj.get(i).get(j) == 1 && i != j){
                    adjL.get(i).add(j);
                    adjL.get(j).add(i);
                }
            }
        }

        int[] vis = new int[V] ;
        int count = 0 ;
        for(int i =  0 ; i < V ; i++){
            if(vis[i] == 0){
                count++ ;
                dfs(adjL , i , vis) ;
            }
        }
        return count ;
    }

}