class Solution {
    int t = 0;
    List<List<Integer>> ans = new ArrayList<>();

    void dfs(List<List<Integer>> adj, int node, int parent, int[] timer, int[] low) {
        timer[node] = t++;
        low[node] = timer[node];

        for (int nbr : adj.get(node)) {

            if (nbr == parent) {
                continue;
            }

            if (timer[nbr] == -1) { // ye visited ke liye check hai

                dfs(adj, nbr, node, timer, low);

                low[node] = Math.min(low[node], low[nbr]);

                if (low[nbr] > timer[node]) {
                    ans.add(Arrays.asList(node, nbr));
                }

            } else {
                low[node] = Math.min(low[node], timer[nbr]);
            }
        }
    }

    public List<List<Integer>> criticalConnections(
            int n, List<List<Integer>> conn) {

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (List<Integer> con : conn) {

            int u = con.get(0);
            int v = con.get(1);

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] timer = new int[n];
        Arrays.fill(timer, -1);

        int[] low = new int[n];

        for (int i = 0; i < n; i++) {
            if (timer[i] == -1) {
                dfs(adj, i, -1, timer, low);
            }
        }

        return ans;
    }
}