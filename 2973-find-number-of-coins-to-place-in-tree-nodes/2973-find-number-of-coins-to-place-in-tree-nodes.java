class Solution {
    long[] ans;
    Set<Integer> vis;

    class Info {
        int nodesCount;
        PriorityQueue<Integer> max;
        PriorityQueue<Integer> min;

        Info() {
            nodesCount = 0;
            // Min-heap to keep the 3 LARGEST elements overall
            max = new PriorityQueue<>();
            // Max-heap to keep the 2 SMALLEST elements overall
            min = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        }
    }

    List<List<Integer>> buildTree(int[][] edges, int n) {
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int e[] : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        return adj;
    }

    Info dfs(List<List<Integer>> adj, int node, int[] cost) {
        Info result = new Info();
        result.nodesCount = 1;
        vis.add(node);

        // Feed to BOTH queues regardless of sign.
        // They will naturally filter out the 3 largest and 2 smallest.
        result.max.offer(cost[node]);
        if (result.max.size() > 3)
            result.max.poll();

        result.min.offer(cost[node]);
        if (result.min.size() > 2)
            result.min.poll();

        for (int child : adj.get(node)) {
            if (vis.contains(child))
                continue;

            Info info = dfs(adj, child, cost);

            result.nodesCount += info.nodesCount;

            for (int val : info.max) {
                result.max.offer(val);
                if (result.max.size() > 3)
                    result.max.poll();
            }

            for (int val : info.min) {
                result.min.offer(val);
                if (result.min.size() > 2)
                    result.min.poll();
            }
        }

        if (result.nodesCount < 3) {
            ans[node] = 1L;
            return result;
        }

        // Calculate prod1 (Product of top 3 elements)
        // Order doesn't matter for multiplication, so standard for-each is safe.
        long prod1 = 1;
        for (int val : result.max) {
            prod1 *= (long) val; // Cast to long to prevent overflow
        }

        // Calculate prod2 (Product of 2 smallest elements * 1 largest element)
        long minProd = 1;
        for (int val : result.min) {
            minProd *= (long) val;
        }
        
        // Find the absolute largest element from the max PQ safely
        long maxVal = Integer.MIN_VALUE;
        for (int val : result.max) {
            maxVal = Math.max(maxVal, (long) val);
        }

        long prod2 = minProd * maxVal;

        long temp = Math.max(prod1, prod2);

        if (temp <= 0)
            ans[node] = 0;
        else
            ans[node] = temp;

        return result;
    }

    public long[] placedCoins(int[][] edges, int[] cost) {
        int n = cost.length;
        vis = new HashSet<>();
        ans = new long[n];

        List<List<Integer>> adj = buildTree(edges, n);
        dfs(adj, 0, cost);

        return ans;
    }
}