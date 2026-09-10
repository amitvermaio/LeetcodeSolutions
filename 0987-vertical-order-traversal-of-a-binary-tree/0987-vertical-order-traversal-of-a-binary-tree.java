class Solution {
    Map<Integer, ArrayList<int[]>> mp;

    void solve(TreeNode root, int r, int c) {
        if (root == null)
            return;

        ArrayList<int[]> al = mp.getOrDefault(c, new ArrayList<>());
        al.add(new int[] { root.val, r });
        mp.put(c, al);

        solve(root.left, r + 1, c - 1);
        solve(root.right, r + 1, c + 1);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        mp = new TreeMap<>();
        solve(root, 0, 0);

        List<List<Integer>> ans = new ArrayList<>();

        for (ArrayList<int[]> al : mp.values()) {

            Collections.sort(al, (a, b) -> {
                if (a[1] == b[1])
                    return Integer.compare(a[0], b[0]);

                return Integer.compare(a[1], b[1]);
            });

            List<Integer> list = new ArrayList<>();

            for (int[] p : al) {
                list.add(p[0]); 
            }

            ans.add(list);
        }

        return ans;
    }
}