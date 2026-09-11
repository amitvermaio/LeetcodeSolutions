class Solution {

    boolean exists(int mid, int h, TreeNode root) {
        int bit = 1 << (h - 2);

        while (bit > 0) {
            if ((mid & bit) == 0) {
                root = root.left;
            } else {
                root = root.right;
            }

            if (root == null)
                return false;

            bit >>= 1;
        }

        return true;
    }

    int height(TreeNode root) {
        if (root == null)
            return 0;

        return height(root.left) + 1;
    }

    public int countNodes(TreeNode root) {
        int h = height(root);

        if (h == 0)
            return 0;

        int nodesCount = (int) Math.pow(2, h - 1) - 1;

        int left = 0;
        int right = (int) Math.pow(2, h - 1) - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (exists(mid, h, root)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        int lastLevelNodes = right + 1;

        return nodesCount + lastLevelNodes;
    }
}