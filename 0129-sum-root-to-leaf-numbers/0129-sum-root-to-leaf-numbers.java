class Solution {
    int ans = 0;
    void dfs(TreeNode root, int sum) {
        if (root == null) return;

        sum *= 10;

        if (root.left==null && root.right==null) {
            ans += sum + root.val;
            return;
        }

        dfs(root.left, sum+root.val);
        dfs(root.right, sum+root.val);
    }

    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return ans;
    }
}