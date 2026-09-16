class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
        q.offer(new Pair<>(root, 0));

        int maxWidth = 0;

        while (!q.isEmpty()) {
            int sz = q.size();

            int start = 0;
            int end = 0;

            for (int i=0; i<sz; i++) {
                Pair<TreeNode, Integer> p = q.poll();
                TreeNode curr = p.getKey();
                int index = p.getValue();

                if (i == 0) 
                    start = index;
                
                if (i == sz-1)
                    end = index;
                
                if (curr.left != null)
                    q.offer(new Pair<>(curr.left, 2*index+1));

                if (curr.right != null)
                    q.offer(new Pair<>(curr.right, 2*index+2));
            }

            maxWidth = Math.max(maxWidth, end-start+1);
        }

        return maxWidth;
    }
}