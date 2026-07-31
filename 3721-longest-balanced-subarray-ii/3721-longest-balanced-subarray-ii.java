class Solution {
    int[] segMin;
    int[] segMax;
    int[] lazy;
    private void propagate(int node, int left, int right) {
        if (lazy[node] != 0) {
            segMin[node] += lazy[node];
            segMax[node] += lazy[node];

            if (left != right) {
                lazy[2*node+1] += lazy[node];
                lazy[2*node+2] += lazy[node];
            }

            lazy[node] = 0;
        }
    }

    private void updateRange(int node, int val, int ql, int qr, int left, int right) {
        propagate(node, left, right);

        if (qr<left || right<ql) return;

        if (ql<=left && right<=qr) {
            segMin[node] += val;
            segMax[node] += val;
            if (left != right) {
                lazy[2*node+1] += val;
                lazy[2*node+2] += val;
            }
            return;
        }

        int mid = (left + right) / 2;

        updateRange(2*node+1, val, ql, qr, left, mid);
        updateRange(2*node+2, val, ql, qr, mid+1, right);

        segMin[node] = Math.min(segMin[2*node+1], segMin[2*node+2]);
        segMax[node] = Math.max(segMax[2*node+1], segMax[2*node+2]);
    }

    private int findLeftMostZero(int node, int left, int right) {
        propagate(node, left, right);

        if (segMin[node]>0 || segMax[node]<0) 
            return -1;
        
        if (left == right) 
            return left;

        int mid = left + (right - left) / 2;
        int leftRes = findLeftMostZero(2*node+1, left, mid);

        if (leftRes != -1)
            return leftRes;

        return findLeftMostZero(2*node+2, mid+1, right);
    }

    public int longestBalanced(int[] nums) {
        int n = nums.length;
        segMin = new int[4*n];
        segMax = new int[4*n];
        lazy = new int[4*n];

        HashMap<Integer, Integer> mp = new HashMap<>();
        int maxL = 0;
        for (int r=0; r<n; r++) {
            int val = (nums[r] & 1) == 0 ? 1 : -1;

            int prev = mp.getOrDefault(nums[r], -1);

            if (prev != -1) {
                updateRange(0, -val, 0, prev, 0, n-1);
            }

            updateRange(0, val, 0, r, 0, n-1);

            int l = findLeftMostZero(0, 0, n-1);
            if (l != -1)
                maxL = Math.max(maxL, r-l+1);
            mp.put(nums[r], r);
        }
        
        return maxL;
    }
}