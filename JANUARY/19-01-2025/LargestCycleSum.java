import java.util.* ;

public class LargestCycleSum {
    public long largesSumCycle(int N, int Edge[]) { // Fix 
        List<Integer>[] adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            if (Edge[i] != -1) {
                adj[i].add(Edge[i]);
            }
        }
        int[] vis = new int[N];
        int[] path = new int[N];
        Arrays.fill(path, -1);
        long maxSum = -1;
        for (int i = 0; i < N; i++) {
            if (vis[i] == 0) {
                maxSum = Math.max(maxSum, dfs(i, vis, path, adj, i));
            }
        }
        return maxSum;
    }

    private long dfs(int node, int[] vis, int[] path, List<Integer>[] adj, int sum) {
        vis[node] = 1;
        path[node] = sum;
        long maxSum = Long.MIN_VALUE;
        for (int neighbor : adj[node]) {
            if (vis[neighbor] == 0) {
                maxSum = Math.max(maxSum, dfs(neighbor, vis, path, adj, sum + neighbor));
            } else if (path[neighbor] != -1) {
                long cycleSum = path[node] - path[neighbor] + neighbor;
                maxSum = Math.max(maxSum, cycleSum);
            }
        }
        path[node] = -1;
        return maxSum;
    }
    public static void main(String[] args) {
        LargestCycleSum solution = new LargestCycleSum();
        Scanner sc = new Scanner(System.in);
        
        // System.out.println("Enter the number of nodes:");
        int N = 10;
        
        int[] Edge = {1,2,3,4,5,6,7,8,9,5};
        System.out.println("Enter the edges:");
        // for (int i = 0; i < N; i++) {
            // Edge[i] = sc.nextInt();
        // }
        
        long result = solution.largesSumCycle(N, Edge);
        System.out.println("The largest sum cycle is: " + result);
        
        sc.close();
    }
}
