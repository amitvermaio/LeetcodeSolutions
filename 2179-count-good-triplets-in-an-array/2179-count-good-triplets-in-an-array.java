class Solution {
    static void build(long[] tree, int node, int left, int right) {
        if (left == right) {
            tree[node] = 0;
            return;
        }

        int mid = (left + right) / 2;
        build(tree, 2*node+1, left, mid);
        build(tree, 2*node+2, mid+1, right);

        tree[node] = 0;
    } 

    static void markVisited(long[] tree, int idx, int node, int left, int right) {
        if (left == right) {
            tree[node] = 1;
            return;
        }

        int mid = (left + right) / 2;
        if (idx <= mid) {
            markVisited(tree, idx, 2*node+1, left, mid);
        } else {
            markVisited(tree, idx, 2*node+2, mid+1, right);
        }

        tree[node] = tree[2*node+1] + tree[2*node+2];
    }

    public long leftCommonCount(long[] tree, int node, int ql, int qr, int left, int right) {
        if (ql>right || qr<left) return 0;
        if (ql<=left && right<=qr) return tree[node];

        int mid = (left + right) / 2;

        long leftCount = leftCommonCount(tree, 2*node+1, ql, qr, left, mid);
        long rightCount = leftCommonCount(tree, 2*node+2, ql, qr, mid+1, right);

        return leftCount + rightCount;
    }

    public long goodTriplets(int[] nums1, int[] nums2) {
        // xyz me y pick karo then x & z ki count pata karo
        // leftCommonCount = 1; // nums1 & nums2 ka
        // leftUncommonCount = 1; // nums 2 me
        // elementsAfterIdxNums2 = (n - idx - 1); // 3 // nums2 me ith element ke right me kitne elements hain
        // rightCommonCount = elementsAfterIdxNums2 - leftUncommonCount;
        // nums2 ke right me kitne common hai as nums1 ke ith element ke jo ki nums2 me any jth position pe hai

        int n = nums1.length;
        long[] tree = new long[4*n];
        build(tree, 0, 0, n-1);       
        HashMap<Integer, Integer> nums2ElPos = new HashMap<>();
        for (int i=0; i<n; i++) {
            nums2ElPos.put(nums2[i], i);
        }
        markVisited(tree, nums2ElPos.get(nums1[0]), 0, 0, n-1);
        long ans = 0;
        for (int i=1; i<n-1; i++) {
            int idx = nums2ElPos.get(nums1[i]);

            long leftCommonCount = leftCommonCount(tree, 0, 0, idx, 0, n-1);
            long leftUncommonCount = i - leftCommonCount;
            
            long elementsAfterIdxNums2 = (n-1) - idx;
            long rightCommonCount = elementsAfterIdxNums2 - leftUncommonCount;
            
            ans += (leftCommonCount * rightCommonCount);
            
            markVisited(tree, idx, 0, 0, n-1);
        }
        return ans;
    }
}