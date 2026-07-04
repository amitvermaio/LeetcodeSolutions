class Solution {
    public int minScore(int n, int[][] roads) {
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for (int i=0; i<n+1; i++) adj.add(new ArrayList<>());
        for (int[] road : roads) {
            adj.get(road[0]).add(new int[]{ road[1], road[2] });
            adj.get(road[1]).add(new int[]{ road[0], road[2] });
        }

        boolean[] vis = new boolean[n+1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{ 1, Integer.MAX_VALUE });
        int minCost = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0];
            int cost = cur[1];

            vis[node] = true;

            minCost = Math.min(minCost, cost);

            for (int[] nbr : adj.get(node)) {
                int nb = nbr[0];
                int nbCost = nbr[1];
                if (!vis[nb]) {
                    q.offer(new int[]{ nb, nbCost });
                }
            }
        }

        return minCost==Integer.MAX_VALUE ? 0 : minCost;
    }
}