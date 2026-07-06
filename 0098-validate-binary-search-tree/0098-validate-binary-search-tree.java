class Solution {
    static boolean check(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) return true;

        if (max!=null && root.val >= max.val) { // for left subtree
            return false;
        }
        if (min!=null && root.val <= min.val) { // right subtree
            return false;
        }

        return check(root.left, min, root) && check(root.right, root, max);
    }

    public boolean isValidBST(TreeNode root) {
        return check(root, null, null); // passing range for left & right        
    }
}