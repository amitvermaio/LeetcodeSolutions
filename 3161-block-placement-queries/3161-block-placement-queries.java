class Solution {
    int[] segmentTree;
    int n = 50_001;

    void constructSegmentTree() {
        segmentTree = new int[4*n];
    }

    void updateSegTree(int idx, int val, int node, int left, int right) {
        if (left == right) {
            segmentTree[node] = val;
            return;
        }

        int mid = left + (right - left) / 2;

        if (idx <= mid) {
            updateSegTree(idx, val, 2*node+1, left, mid);
        } else {
            updateSegTree(idx, val, 2*node+2, mid+1, right);
        }

        segmentTree[node] = Math.max(
            segmentTree[2*node+1],
            segmentTree[2*node+2]
        );
    }

    int querySegTree(int node, int ql, int qr, int left, int right) {
        if (left > qr || right < ql) 
            return 0;

        if (ql<=left && right<=qr)
            return segmentTree[node];
        
        int mid = left + (right - left) / 2;

        int leftMax = querySegTree(2*node+1, ql, qr, left, mid);
        int rightMax = querySegTree(2*node+2, ql, qr, mid+1, right);

        return Math.max(leftMax, rightMax);
    }

    public List<Boolean> getResults(int[][] queries) {
        constructSegmentTree();    

        List<Boolean> result = new ArrayList<>();
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);

        for (int[] query : queries) {
            if (query[0] == 1) {
                int x = query[1];
                int prev = set.lower(x);
                Integer next = set.higher(x);

                updateSegTree(x, x-prev, 0, 0, n-1);
                if (next != null)
                    updateSegTree(next, next-x, 0, 0, n-1);
                
                set.add(x);
            } else {
                int x = query[1];
                int sz = query[2];

                int prev = set.lower(x);
                // yha next nahi nikal rahe hain kyuki check karna hai 0-x ke bich ka usse jyada me nahi

                int maxGap = querySegTree(0, 0, prev, 0, n-1);
                int best = Math.max(maxGap, x - prev);
                result.add(best >= sz);
            }
        }

        return result;
    }
}