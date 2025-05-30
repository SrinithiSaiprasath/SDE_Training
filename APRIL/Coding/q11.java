public class q11 {
    private static boolean isCyclic(int[][] graph, int v, boolean[] visited, boolean[] recStack) {
        if (recStack[v]) {
            return true;
        }
        if (visited[v]) {
            return false;
        }

        visited[v] = true;
        recStack[v] = true;

        for (int i = 0; i < graph.length; i++) {
            if (graph[v][i] == 1 && isCyclic(graph, i, visited, recStack)) {
                return true;
            }
        }

        recStack[v] = false;
        return false;
    }

    public static boolean isCyclic(int[][] graph) {
        int V = graph.length;
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (isCyclic(graph, i, visited, recStack)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1},
            {1, 0, 0, 0}
        };

        if (isCyclic(graph)) {
            System.out.println("Graph contains cycle");
        } else {
            System.out.println("Graph doesn't contain cycle");
        }
    }
}
