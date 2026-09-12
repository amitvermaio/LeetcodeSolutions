class Solution {
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 0;

        int maxSum = Integer.MIN_VALUE;
        int ansLevel = 0;

        while (!q.isEmpty()) {
            int sz = q.size();

            int sum = 0;
            level++;

            for (int i=0; i<sz; i++) {
                TreeNode curr = q.poll();

                if (curr.left != null)  q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);

                sum += curr.val;
            }

            if (sum > maxSum) {
                maxSum = sum;
                ansLevel = level;
            }
        }        

        return ansLevel;
    }
}