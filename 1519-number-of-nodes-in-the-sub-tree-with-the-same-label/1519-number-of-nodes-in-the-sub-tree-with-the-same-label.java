class Solution {
    int[] ans;
    Map<Character, Integer> dfs(List<List<Integer>> adj, String labels, int node, int parent) {
        Map<Character, Integer> freq = new HashMap<>();

        for (int child : adj.get(node)) {
            if (child == parent) continue;
            Map<Character, Integer> mp = dfs(adj, labels, child, node);

            for (char k : mp.keySet()) {
                int v = mp.get(k);

                freq.put(k, freq.getOrDefault(k, 0) + v);
            }
        }

        char ch = labels.charAt(node);
        freq.put(ch, freq.getOrDefault(ch, 0)+1);

        ans[node] = freq.get(ch);

        return freq;
    }

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i=0; i<n; i++)
            adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        ans = new int[n];
        dfs(adj, labels, 0, -1);

        return ans;   
    }
}