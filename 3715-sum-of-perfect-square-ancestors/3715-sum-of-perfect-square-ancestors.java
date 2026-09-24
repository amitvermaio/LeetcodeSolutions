class Solution {
    long ans = 0;
    int strip(int v) {
        int prod = 1;
        for (int i=2; i<=(int)Math.sqrt(v); i++) {
            int cnt = 0;
            while (v%i == 0) {
                v /= i;
                cnt++;
            }
            if (cnt%2 == 1) prod *= i;
        }
        if (v > 1) prod *= v;
        return prod;
    }

    void dfs(int node, int parent, List<List<Integer>> adj, Map<Integer, Integer> mp, int[] nums) {
        int s = strip(nums[node]);
        ans += mp.getOrDefault(s, 0);
        mp.put(s, mp.getOrDefault(s, 0) + 1);

        for (int child : adj.get(node)) {
            if (child == parent) continue;
            dfs(child, node, adj, mp, nums);
        }

        mp.put(s, mp.get(s) - 1);
    }

    public long sumOfAncestors(int n, int[][] edges, int[] nums) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i=0; i<n; i++) 
            adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        Map<Integer, Integer> mp = new HashMap<>();
        dfs(0, -1, adj, mp, nums);
        return ans;
    }
}