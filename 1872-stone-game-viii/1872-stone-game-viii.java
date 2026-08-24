class Solution {
    int[] prefixSum;
    int[] dp;

    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        prefixSum = new int[n];
        prefixSum[0] = stones[0];

        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + stones[i];
        }

        dp = new int[n];

        Arrays.fill(dp, Integer.MIN_VALUE);

        return solve(1, n);
    }

    int solve(int i, int n) {
        if (i == n - 1) {
            return prefixSum[n - 1];
        }

        if (dp[i] != Integer.MIN_VALUE) {
            return dp[i];
        }

        int take = prefixSum[i] - solve(i + 1, n);
        int skip = solve(i + 1, n);

        return dp[i] = Math.max(take, skip);
    }
}