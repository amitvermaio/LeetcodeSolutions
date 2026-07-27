class NumArray {
    int[] tree;
    int n;
    public NumArray(int[] nums) {
        n = nums.length;
        tree = new int[4*n];
        build(nums, 0, 0, n-1);
    }

    private void build(int[] nums, int node, int left, int right) {
        if (left == right) {
            tree[node] = nums[left];
            return;
        }

        int mid = left + (right-left) / 2;

        build(nums, 2*node+1, left, mid);
        build(nums, 2*node+2, mid+1, right);

        tree[node] = tree[2*node+1] + tree[2*node+2];
    } 
    
    public void update(int index, int val) {
        updateTree(index, val, 0, 0, n-1);
    }

    private void updateTree(int idx, int val, int node, int left, int right) {
        if (left == right) {
            tree[node] = val;
            return;
        }    

        int mid = left + (right - left) / 2;

        if (idx <= mid) {
            updateTree(idx, val, 2*node+1, left, mid);
        } else {
            updateTree(idx, val, 2*node+2, mid+1, right);
        }

        tree[node] = tree[2*node+1] + tree[2*node+2];
    }
    
    public int sumRange(int left, int right) {
        return queryTree(0, 0, n-1, left, right);
    }

    private int queryTree(int node, int left, int right, int ql, int qr) {
        if (ql<=left && qr>=right) return tree[node];
        if (left>qr || ql>right) return 0;

        int mid = left + (right - left) / 2;
        int leftS = queryTree(2*node+1, left, mid, ql, qr);
        int rightS = queryTree(2*node+2, mid+1, right, ql, qr);

        return leftS + rightS;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */