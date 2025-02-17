import java.util.*;
public class largestSumCy{
	public static long largestSumCycle1(int N, int Edges[]){
	        int[] indeg = new int[N];
	        for (int node : Edges) {
            	if (node != -1) {
                	indeg[node]++;
            	}
            }
	        bfs(indeg,Edges);
        	long res = -1;
        	for (int i = 0; i < N; i++) {
            		if (indeg[i] != 0) {
                		res = Math.max(res, helper(i, Edges , indeg) );
            		}
        	}
		return res;		
	}
	public static void bfs(int[] indeg, int[] Edges) {
        	Queue<Integer> queue = new LinkedList<>();
        	int n = indeg.length;
        	for (int i = 0; i < n; i++) {
            		if (indeg[i] == 0) {
                		queue.add(i);
            		}
        	}
		while (!queue.isEmpty()) {
            		int size = queue.size();
            		for (int i = 0; i < size; i++) {
                		int node = queue.poll();
                		if (Edges[node] == -1) continue;
                		indeg[Edges[node]]--;
                		if (indeg[Edges[node]] == 0) {
                    			queue.add(Edges[node]);
                		}
            		}
        	}
	}
	public static long helper(int node, int[] Edges , int[] indeg){
		long sum = node;
		indeg[node]-- ; 
		Queue<Integer> queue = new LinkedList<>();
		queue.add(Edges[node]);
		while (queue.peek() != node) {
				sum += queue.peek();
				queue.add(Edges[queue.poll()]);
				indeg[node]-- ; 


		}
		return sum;
	}   
	public static void main(String[] args){
		int N = 11;
		int[] Edges = {1,2,3,4,1,6,-1,8, 9 ,10  ,11 };
		System.out.println(largestSumCycle1(N,Edges));
	} 
}