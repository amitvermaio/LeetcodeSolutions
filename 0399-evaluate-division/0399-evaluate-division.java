class Solution {
    double dfs(Map<String, List<Pair<String, Double>>> adj, String src, String dst, Set<String> vis) {
        if (src.equals(dst))
            return 1.0;

        if (vis.contains(src)) 
            return -1.0;
        

        vis.add(src);
        
        for (Pair<String, Double> p : adj.get(src)) {
            String next = p.getKey();
            double wt = p.getValue();

            if (adj.containsKey(next)) {
                double ans = dfs(adj, next, dst, vis);

                if (ans != -1.0) {
                    return wt * ans;
                }
            }
        }

        return -1.0;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Pair<String, Double>>> adj = new HashMap<>();

        int k = 0;
        for (List<String> eq : equations) {
            String u = eq.get(0);
            String v = eq.get(1);
            double val = values[k++];

            List<Pair<String, Double>> l = adj.getOrDefault(u, new ArrayList<>());
            l.add(new Pair<>(v, val));
            adj.put(u, l);

            l = adj.getOrDefault(v, new ArrayList<>());
            l.add(new Pair<>(u, 1/val));
            adj.put(v, l);
        }

        int n = queries.size();
        double[] res = new double[n];

        for (int i=0; i<n; i++) {
            String src = queries.get(i).get(0);
            String dst = queries.get(i).get(1);

            if (!adj.containsKey(src)) {
                res[i] = -1.0;
            } else {
                res[i] = dfs(adj, src, dst, new HashSet<>());
                
            }
        }

        return res;
    }
}