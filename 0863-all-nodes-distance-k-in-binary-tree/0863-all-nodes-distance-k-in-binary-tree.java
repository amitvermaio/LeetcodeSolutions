class Solution {
    Map<Integer, List<Integer>> mp; // undirected_graph
    Set<Integer> vis;

    private void dfs(int node, int k, List<Integer> ans) {
        if (k == 0) {
            ans.add(node);
            return;
        }

        vis.add(node);

        for (int nbr : mp.get(node)) {
            if (!vis.contains(nbr)) {
                dfs(nbr, k-1, ans);
            }
        }
    } 

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        if (k == 0) {
            return Arrays.asList(target.val);
        }

        mp = new HashMap<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            List<Integer> nbrs = mp.getOrDefault(curr.val, new ArrayList<>());

            if (curr.left != null) {
                int val = curr.left.val;
                nbrs.add(val);
                q.offer(curr.left);

                // we also have to add curr to the nbr of curr.left
                List<Integer> lNbr = mp.getOrDefault(val, new ArrayList<>());
                lNbr.add(curr.val);
                mp.put(val, lNbr);
            }

            if (curr.right != null) {
                int val = curr.right.val;
                nbrs.add(val);
                q.offer(curr.right);

                // we also have to add curr to the nbr of curr.right
                List<Integer> Nbr = mp.getOrDefault(val, new ArrayList<>());
                Nbr.add(curr.val);
                mp.put(val, Nbr);
            }

            mp.put(curr.val, nbrs);
        }

        List<Integer> ans = new ArrayList<>();

        vis = new HashSet<>();

        dfs(target.val, k, ans);

        return ans;
    }
}