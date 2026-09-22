class Solution {
    List<List<Integer>> adj;
    long[] ans;
    int[] cost;

    public long[] placedCoins(int[][] edges, int[] cost) {
        int n = cost.length;
        this.cost = cost;
        
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        
        ans = new long[n];
        dfs(0, -1);
        
        return ans;
    }

    private List<Long> dfs(int u, int parent) {
        List<Long> res = new ArrayList<>();
        res.add((long) cost[u]);

        // Gather all accumulated top values from the children
        for (int v : adj.get(u)) {
            if (v == parent) continue;
            res.addAll(dfs(v, u));
        }

        // Sort the combined elements from the node and its children
        Collections.sort(res);
        int sz = res.size();

        if (sz < 3) {
            ans[u] = 1;
        } else {
            // The max product comes from either the 3 largest elements
            // OR the 2 smallest (potentially two large negatives) multiplied by the 1 largest element.
            long maxProd = Math.max(
                res.get(sz - 1) * res.get(sz - 2) * res.get(sz - 3),
                res.get(0) * res.get(1) * res.get(sz - 1)
            );
            
            // If the max product is still negative, fall back to 0
            ans[u] = Math.max(0L, maxProd);
        }

        // To prevent Time Limit Exceeded (TLE) & excess memory, we only need to pass upwards 
        // a max of 5 elements: the 2 smallest and the 3 largest.
        if (sz > 5) {
            List<Long> nextRes = new ArrayList<>();
            nextRes.add(res.get(0));
            nextRes.add(res.get(1));
            nextRes.add(res.get(sz - 3));
            nextRes.add(res.get(sz - 2));
            nextRes.add(res.get(sz - 1));
            return nextRes;
        }
        
        return res;
    }
}