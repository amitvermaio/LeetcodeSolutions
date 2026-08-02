class Solution {
    int[][] dp;
    private int solve(int[] nums, int i, int j) {
        if (i > j) 
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];
        
        int take_start = nums[i] + Math.min(
            solve(nums, i+2, j),
            solve(nums, i+1, j-1)
        );

        int take_end = nums[j] + Math.min(
            solve(nums, i+1, j-1),
            solve(nums, i, j-2)
        );

        return dp[i][j] = Math.max(take_start, take_end);
    }

    public boolean stoneGame(int[] piles) {
        int n = piles.length;

        dp = new int[n][n];

        int total = 0;
        for (int i=0; i<n; i++) {
            Arrays.fill(dp[i], -1);
            total += piles[i];
        }

        int alice = solve(piles, 0, n-1);
        int bob = total - alice;

        return alice > bob;
    }
}