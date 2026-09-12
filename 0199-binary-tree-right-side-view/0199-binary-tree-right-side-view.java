class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, 0, ans);
        return ans;
    }

    private void dfs(TreeNode node, int depth, List<Integer> ans) {
        if (node == null) return;

        if (depth == ans.size()) {
            ans.add(node.val);
        }

        dfs(node.right, depth + 1, ans);
        dfs(node.left, depth + 1, ans);
    }
}