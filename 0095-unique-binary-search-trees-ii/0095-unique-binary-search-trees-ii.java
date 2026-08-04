class Solution {
    HashMap<Pair<Integer, Integer>, List<TreeNode>> memo;
    private List<TreeNode> solve(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) {
            res.add(null);
            return res;
        }

        if (start == end) {
            res.add(new TreeNode(start));
            return res;
        }

        if (memo.containsKey(new Pair<>(start, end))) {
            return memo.get(new Pair<>(start, end));
        }

        for (int i=start; i<=end; i++) {
            List<TreeNode> leftBSTs = solve(start, i-1);
            List<TreeNode> rightBSTs = solve(i+1, end);

            for (TreeNode left : leftBSTs) {
                for (TreeNode right : rightBSTs) {
                    TreeNode root = new TreeNode(i, left, right);
                    res.add(root);
                }
            }
        }
        memo.put(new Pair<>(start, end), res);
        return res;
    }

    public List<TreeNode> generateTrees(int n) {
        memo = new HashMap<>();
        return solve(1, n);
    }
}