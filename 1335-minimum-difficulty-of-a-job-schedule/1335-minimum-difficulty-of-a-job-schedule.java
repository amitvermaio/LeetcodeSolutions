class Solution {
    int n;
    int[][] dp;
    private int solve(int[] nums, int d, int i) {
        if (i == n)
            return d==0 ? 0 : Integer.MAX_VALUE;
        
        if (d == 0)
            return Integer.MAX_VALUE;
        
        if (dp[d][i] != -1)
            return dp[d][i];
        
        int max = 0;
        int ans = Integer.MAX_VALUE;

        for (int j=i; j<=n-d; j++) {
            max = Math.max(max, nums[j]);

            int next = solve(nums, d-1, j+1);

            if (next != Integer.MAX_VALUE) {
                ans = Math.min(ans, max + next);
            }
        }

        return dp[d][i] = ans;
    }
    
    public int minDifficulty(int[] jobDifficulty, int d) {
        n = jobDifficulty.length;
        if (n < d)
            return -1;
        
        dp = new int[d+1][n+1];
        for (int i=0; i<=d; i++)
            Arrays.fill(dp[i], -1);
        
        return solve(jobDifficulty, d, 0);
    }
}