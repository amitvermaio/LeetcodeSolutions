class Solution {
    int[] minTree;
    int[] maxTree;
    int[] lazy;

    private void propagate(int node, int left, int right) {
        if (lazy[node] != 0) {
            minTree[node] += lazy[node];
            maxTree[node] += lazy[node];

            if (left != right) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }

            lazy[node] = 0;
        }
    }

    private void updateRange(int node, int val, int ql, int qr, int left, int right) {
        propagate(node, left, right);

        if (left > qr || right < ql)
            return;

        if (ql <= left && right <= qr) {
            lazy[node] += val;
            propagate(node, left, right);
            return;
        }

        int mid = (left + right) / 2;

        updateRange(2 * node + 1, val, ql, qr, left, mid);
        updateRange(2 * node + 2, val, ql, qr, mid + 1, right);

        minTree[node] = Math.min(minTree[2 * node + 1], minTree[2 * node + 2]);
        maxTree[node] = Math.max(maxTree[2 * node + 1], maxTree[2 * node + 2]);
    }

    private int findLeftMostZero(int node, int left, int right) {
        propagate(node, left, right);

        // No zero exists in this segment
        if (minTree[node] > 0 || maxTree[node] < 0)
            return -1;

        if (left == right)
            return left;

        int mid = (left + right) / 2;

        int ans = findLeftMostZero(2 * node + 1, left, mid);
        if (ans != -1)
            return ans;

        return findLeftMostZero(2 * node + 2, mid + 1, right);
    }

    public int longestBalanced(int[] nums) {
        int n = nums.length;

        minTree = new int[4 * n];
        maxTree = new int[4 * n];
        lazy = new int[4 * n];

        HashMap<Integer, Integer> mp = new HashMap<>();

        int maxL = 0;

        for (int r = 0; r < n; r++) {

            int val = (nums[r] % 2 == 0) ? 1 : -1;

            int prev = mp.getOrDefault(nums[r], -1);

            if (prev != -1) {
                updateRange(0, -val, 0, prev, 0, n - 1);
            }

            updateRange(0, val, 0, r, 0, n - 1);

            int leftIdx = findLeftMostZero(0, 0, n - 1);

            if (leftIdx != -1) {
                maxL = Math.max(maxL, r - leftIdx + 1);
            }

            mp.put(nums[r], r);
        }

        return maxL;
    }
}