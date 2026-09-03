class Solution {
    long[][] dp;
    int n;

    public long solve(int[] nums, int i, boolean even) {
        if (i >= n)
            return 0;
        
        int flagIdx = even ? 1 : 0;

        if (dp[i][flagIdx] != -1) 
            return dp[i][flagIdx];
        
        long skip = solve(nums, i+1, even);
        int num = nums[i];
        if (!even) num = -num;
        long take = num + solve(nums, i+1, !even);

        return dp[i][flagIdx] = Math.max(take, skip);
    }

    public long maxAlternatingSum(int[] nums) {
        n = nums.length;

        dp = new long[n+1][2];
        for (int i=0; i<n+1; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }

        return solve(nums, 0, true);
    }
}