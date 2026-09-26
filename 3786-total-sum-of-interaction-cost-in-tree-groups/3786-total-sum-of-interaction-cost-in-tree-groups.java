class Solution {
    public long interactionCosts(int n, int[][] edges, int[] group) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        final int G = 21; // groups are 1..20

        // Total count of each group across the whole tree
        long[] total = new long[G];
        for (int i = 0; i < n; i++) total[group[i]]++;

        // BFS from root 0 to get parent[] and a visiting order
        int[] parent = new int[n];
        int[] order = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(parent, -1);

        int head = 0, tail = 0;
        order[tail++] = 0;
        visited[0] = true;
        while (head < tail) {
            int u = order[head++];
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    parent[v] = u;
                    order[tail++] = v;
                }
            }
        }

        // Per-node subtree group counts
        int[][] count = new int[n][G];
        for (int i = 0; i < n; i++) count[i][group[i]] = 1;

        long answer = 0;

        // Process nodes in reverse BFS order => children processed before parents
        for (int idx = n - 1; idx >= 1; idx--) {
            int node = order[idx];
            int par = parent[node];

            // Contribution of edge (node - parent)
            for (int g = 1; g < G; g++) {
                int inSub = count[node][g];
                long outSub = total[g] - inSub;
                answer += (long) inSub * outSub;
            }

            // Merge node's counts into parent's counts
            for (int g = 1; g < G; g++) {
                count[par][g] += count[node][g];
            }
        }

        return answer;
    }
}