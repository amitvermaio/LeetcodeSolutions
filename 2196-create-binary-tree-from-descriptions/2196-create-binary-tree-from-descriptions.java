class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Set<Integer> childs = new HashSet<>();

        for (int[] d : descriptions) {
            childs.add(d[1]);
        }

        TreeNode parent = null;
        Map<Integer, TreeNode> mp = new HashMap<>();
        for (int[] d : descriptions) {
            TreeNode ch = mp.getOrDefault(d[1], new TreeNode(d[1]));
            TreeNode par = mp.getOrDefault(d[0], new TreeNode(d[0]));

            if (!mp.containsKey(ch)) 
                mp.put(d[1], ch);

            if (!mp.containsKey(par)) 
                mp.put(d[0], par);

            if (d[2] == 1) {
                par.left = ch;
            } else {
                par.right = ch;
            }

            if (!childs.contains(par.val)) 
                parent = par;
        }

        return parent;
    }
}