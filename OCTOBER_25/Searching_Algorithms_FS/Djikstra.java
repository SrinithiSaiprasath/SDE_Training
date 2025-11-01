package OCTOBER_25.Searching_Algorithms_FS;

import java.util.*;

// package OCTOBER_25.Searching_Algorithms_FS

public class Djikstra {

    public static void main(String[] args) {
        // Number of vertices
        int V = 5;

        // Adjacency list {neighbor, weight}
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Example edges
        adj.get(0).add(new int[]{1, 2});
        adj.get(0).add(new int[]{2, 4});
        adj.get(1).add(new int[]{2, 1});
        adj.get(1).add(new int[]{3, 7});
        adj.get(2).add(new int[]{4, 3});
        adj.get(3).add(new int[]{4, 2});

        // Run algorithm
        // Solution obj = new Solution();
        int[] dist = dijkstra(V, adj, 0);

        // Print shortest distances
        for (int i = 0; i < V; i++) {
            System.out.println("Distance from 0 to " + i + " = " + dist[i]);
        }
    }
    // Function to implement Dijkstra's Algorithm
    public static int[] dijkstra(int V, List<List<int[]>> adj, int src) {
        // Distance array initialized to large value
        int[] dist = new int[V];
        Arrays.fill(dist, (int)1e9);
        System.out.println(Arrays.toString(dist));
        // Min-heap storing {distance, node}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0]-b[0]);

        // Distance to source is 0
        dist[src] = 0;

        // Push source into heap
        pq.add(new int[]{0, src});
        System.out.println("Pq : " +Arrays.toString(dist));
        // Process nodes until heap is empty
        while (!pq.isEmpty()) {
            // Extract node with minimum distance
            int[] cur = pq.poll();
            int d = cur[0];
            int node = cur[1];
            System.out.println("Node : " +node + " Dist : " +d);

            // Skip if outdated distance
            System.out.println("Dist Array : " +Arrays.toString(dist) + " Current Dist : " +d);
            if (d > dist[node]) continue;

            // Traverse all neighbors
            for (int[] edge : adj.get(node)) {
                System.out.println("Edge : " +Arrays.toString(edge) + "Next :" + edge[0] + " Weight : " +edge[1]);
                int next = edge[0];
                int wt = edge[1];

                // Relaxation check
                if (dist[node] + wt < dist[next]) {
                    System.out.println("Updating distance of node " + next + " from " + dist[next] + " to " + (dist[node] + wt));
                    // Update distance
                    dist[next] = dist[node] + wt;
                    System.out.println("Dist Array after update: " +Arrays.toString(dist));
                    // Push into heap
                    pq.add(new int[]{dist[next], next});
                }
            }
        }
        return dist;
    }

}