class Solution {
    public Node connect(Node root) {
        if (root == null)
            return null;

        Node level = root;

        while (level.left != null) {

            Node curr = level;

            while (curr != null) {

                // Same parent
                curr.left.next = curr.right;

                // Different parents
                if (curr.next != null) {
                    curr.right.next = curr.next.left;
                }

                curr = curr.next;
            }

            // Move to next level
            level = level.left;
        }

        return root;
    }
}