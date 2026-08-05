class Solution {
    private void dfs(List<List<Integer>> adj, boolean[] vis, int node) {
        if (vis[node])
            return;
        
        vis[node] = true;
        for (int nbr : adj.get(node))
            dfs(adj, vis, nbr);
    }

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i=0; i<n; i++)
            adj.add(new ArrayList<>());
        
        for (int[] inv : invocations)
            adj.get(inv[0]).add(inv[1]);

        boolean[] vis = new boolean[n];
        dfs(adj, vis, k);

        Queue<Integer> q = new LinkedList<>();
        for (int i=0; i<n; i++) {
            if (!vis[i]) {
                q.offer(i);
            }
        }

        Set<Integer> set = new HashSet<>();
        boolean flag = false;
        while (!q.isEmpty()) {
            int node = q.poll();
            
            set.add(node);

            if (vis[node])
                flag = true;

            for (int nbr : adj.get(node)) {
                if (!set.contains(nbr)) {
                    q.add(nbr);
                }
            }
        } 

        if (flag)
            for (int i=0; i<n; i++)
                if (vis[i]) 
                    set.add(i);

        return new ArrayList<>(set);
    }
}