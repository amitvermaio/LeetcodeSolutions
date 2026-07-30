// [l:r] -> max: We can figure out easily using Segment Tree
class Solution {
    int[] tree;
    private void build(int[] arr, int node, int left, int right) {
        if (left == right) {
            tree[node] = arr[left];
            return;
        }

        int mid = (left + right) / 2;

        build(arr, 2*node+1, left, mid);
        build(arr, 2*node+2, mid+1, right);

        tree[node] = Math.max(tree[2*node+1], tree[2*node+2]);
    }

    private boolean placedFruit(int fruit, int node, int left, int right) {
        if (tree[node] < fruit) return false;

        if (left == right) {
            tree[node] = -1;
            return true;
        }

        int mid = (left + right) / 2;

        boolean ans = false;
        if (tree[2*node+1] >= fruit) {
            ans |= placedFruit(fruit, 2*node+1, left, mid);
        } else {
            ans |= placedFruit(fruit, 2*node+2, mid+1, right);
        }

        tree[node] = Math.max(tree[2*node+1], tree[2*node+2]);

        return ans;
    }

    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        tree = new int[4*n];
        build(baskets, 0, 0, n-1);

        int ans = 0;
        for (int i=0; i<n; i++) {
            if (!placedFruit(fruits[i], 0, 0, n-1)) {
                ans++;
            }
        }

        return ans;
    }
}