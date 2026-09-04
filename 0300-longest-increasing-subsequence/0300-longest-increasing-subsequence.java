class Solution {
    int n;
    int[][] dp;

    private int solve(int[] nums, int i, int prev) {
        if (i >= n)
            return 0;

        if (prev!=-1 && dp[i][prev]!=-1)
            return dp[i][prev];
        
        int skip = solve(nums, i+1, prev);
        int take = 0;
        if (prev==-1 || nums[prev]<nums[i]) {
            take = 1 + solve(nums, i+1, i);
        }
        if (prev != -1)
            dp[i][prev] = Math.max(skip, take);
        return Math.max(skip, take);
    }

    public int lengthOfLIS(int[] nums) {
        n = nums.length;
        dp = new int[n][n];

        for (int i=0; i<n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return solve(nums, 0, -1);
    }
}