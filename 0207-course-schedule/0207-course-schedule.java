class Solution {
    boolean hasCycle(List<List<Integer>> adj, int node, boolean[] vis, boolean[] rec) {
        if (vis[node] && rec[node])
            return true;

        if (vis[node]) 
            return false;        

        vis[node] = true;
        rec[node] = true;

        for (int nbr : adj.get(node)) 
            if (hasCycle(adj, nbr, vis, rec))
                return true;
            
        rec[node] = false;

        return false;
    }

    public boolean canFinish(int courses, int[][] pre) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i=0; i<courses; i++)
            adj.add(new ArrayList<>());
        for (int[] p : pre) 
            adj.get(p[0]).add(p[1]);

        boolean[] vis = new boolean[courses];
        boolean[] rec = new boolean[courses];

        for (int i=0; i<courses; i++) {
            if (hasCycle(adj, i, vis, rec)) {
                return false;
            }
        }

        return true;
    }
}