class Solution {
    private int solve(int n) {
        if (n==0 || n==1) return 1;
        
        int total = 0;
        for (int root=1; root<=n; root++) {
            int leftBSTCount = solve(root-1);
            int rightBSTCount = solve(n-root);
            total += leftBSTCount * rightBSTCount;
        }

        return total;
    }

    public int numTrees(int n) {
        return solve(n);
    }
}