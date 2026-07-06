class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left==null && right!=null) {
            return right;
        } else if (right==null && left!=null) {
            return left;
        } else if (left==null && right==null) {
            return null;
        }

        return root;
    }
}