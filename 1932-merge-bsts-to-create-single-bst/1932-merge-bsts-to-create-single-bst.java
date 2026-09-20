class Solution {
    Map<Integer, Pair<TreeNode, Boolean>> parents;

    boolean populate(TreeNode root, TreeNode prev, Boolean dir) {
        if (root == null)
            return true;

        if (prev != null) {
            // Same value can appear as a root in one tree
            // and as a leaf in another, so don't reject it here.
            if (parents.containsKey(root.val))
                return false;

            parents.put(root.val, new Pair<>(prev, dir));
        }

        return populate(root.left, root, false)
            && populate(root.right, root, true);
    }

    boolean validate(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null)
            return true;

        if (min != null && root.val <= min.val)
            return false;

        if (max != null && root.val >= max.val)
            return false;

        return validate(root.left, min, root)
            && validate(root.right, root, max);
    }

    int countNodes(TreeNode root, Set<TreeNode> visited) {
        if (root == null)
            return 0;

        // Prevent infinite recursion if a cycle exists
        if (!visited.add(root))
            return -1;

        int left = countNodes(root.left, visited);
        if (left == -1)
            return -1;

        int right = countNodes(root.right, visited);
        if (right == -1)
            return -1;

        return 1 + left + right;
    }

    public TreeNode canMerge(List<TreeNode> trees) {
        parents = new HashMap<>();

        int totalNodes = 0;

        // Build parent map and count all original nodes
        for (TreeNode t : trees) {
            totalNodes += countNodes(t, new HashSet<>());

            if (!populate(t, null, true))
                return null;
        }

        TreeNode parent = null;
        int rootCount = 0;

        for (TreeNode t : trees) {

            if (!parents.containsKey(t.val)) {
                parent = t;
                rootCount++;
            } else {
                Pair<TreeNode, Boolean> p = parents.get(t.val);

                TreeNode par = p.getKey();
                boolean dir = p.getValue();

                // Check cycle
                Pair<TreeNode, Boolean> gp = parents.get(par.val);

                if (gp != null && gp.getKey() == t)
                    return null;

                if (!dir)
                    par.left = t;
                else
                    par.right = t;
            }

            if (rootCount > 1)
                return null;
        }

        if (parent == null)
            return null;

        // Validate BST
        if (!validate(parent, null, null))
            return null;

        /*
         * Every merge replaces one leaf node by another tree root.
         * Therefore final node count must be:
         *
         * total original nodes - number of merges
         */
        int expectedNodes = totalNodes - (trees.size() - 1);

        int actualNodes = countNodes(parent, new HashSet<>());

        if (actualNodes != expectedNodes)
            return null;

        return parent;
    }
}