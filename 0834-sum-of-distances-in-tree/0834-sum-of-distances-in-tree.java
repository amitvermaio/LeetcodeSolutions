class Solution {
    int root_result = 0;
    int[] count;
    int N;

    int dFSBASE(List<List<Integer>> adj, int curr, int prev, int curr_depth) {
        int total_count = 1;

        root_result += curr_depth;

        for (int child : adj.get(curr)) {
            if (child != prev) {
                total_count += dFSBASE(adj, child, curr, curr_depth+1);
            }
        }

        count[curr] = total_count;

        return total_count;
    }

    void DFS(List<List<Integer>> adj, int parent, int prev, int[] result) {
        
        for (int child : adj.get(parent)) {
            if (child != prev) {
                result[child] = result[parent] - count[child] + (N - count[child]);

                DFS(adj, child, parent, result);
            }
        }
    }

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        N = n;
        count = new int[n];

        for (int i=0; i<n; i++) 
            adj.add(new ArrayList<>());
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        dFSBASE(adj, 0, -1, 0); 

        int[] result = new int[n];
        result[0] = root_result;

        DFS(adj, 0, -1, result);

        return result;
    }
}