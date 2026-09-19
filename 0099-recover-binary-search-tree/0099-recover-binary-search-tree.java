class Solution {
    TreeNode first, second, prev;
    void inorder(TreeNode root) {
        if (root == null)
            return;
        
        inorder(root.left);
        
        if (prev != null && prev.val > root.val) {
            if (first == null) {
                first = prev;
            } 
            /* 1 3 2 4 
            Only violation:
                3 > 2
            agar else me `second` ko update karu code mein:
                first = 3
                second = null

            pehli violation par second set nahi hota. Ye actually adjacent swapped nodes ke case mein problem karega.

            isliye second wala condition else me nhi hoga
            */

            second = root;
        }

        prev = root;

        inorder(root.right);
    }

    public void recoverTree(TreeNode root) {
        first = second = prev = null;
        inorder(root);

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}