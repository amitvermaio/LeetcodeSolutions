class Solution {
    int ans = 0;
    private Pair<Integer, Integer> solve(TreeNode root) {
        if (root == null) {
            return new Pair(0, 0);
        }

        Pair<Integer, Integer> left  = solve(root.left);
        Pair<Integer, Integer> right = solve(root.right);

        int sum   = root.val + left.getKey() + right.getKey();
        int count = 1 + left.getValue() + right.getValue(); 

        if (sum/count == root.val) 
            ans++;

        return new Pair(sum, count);
    }

    public int averageOfSubtree(TreeNode root) {
        solve(root);
        return ans;
    }
}