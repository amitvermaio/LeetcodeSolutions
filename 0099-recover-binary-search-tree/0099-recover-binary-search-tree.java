class Solution {
    List<TreeNode> nodes = new ArrayList<>();
    void inorder(TreeNode root) {
        if (root == null)
            return;
        
        inorder(root.left);
        nodes.add(root);
        inorder(root.right);
    }

    public void recoverTree(TreeNode root) {
        inorder(root);
        List<TreeNode> temp = new ArrayList<>(nodes);
        Collections.sort(temp, (a, b) -> Integer.compare(a.val, b.val));

        TreeNode first = null;
        TreeNode second = null;

        for (int i=0; i<temp.size(); i++) {
            if (temp.get(i) != nodes.get(i)) {
                if (first == null) {
                    first = temp.get(i);
                } else {
                    second = temp.get(i);
                    break;
                }
            }
        }

        int sVal = second.val;
        second.val = first.val;
        first.val = sVal;
    }
}