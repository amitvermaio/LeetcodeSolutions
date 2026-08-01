// Min Max Optimal Game Strategy Problem
// In problems me hume apna best pick karna hota hai (max)
// aur opponent se worst result expect karna hota hai - that doesn't mean ki opponent ganda khelega but opponent apne liye best khelega taaki humara result worst bana sake. (min).

class Solution {
    int[][] dp;

    private int solve(int[] nums, int i, int j) {
        if (i > j) 
            return 0;
        if (i == j) 
            return nums[i];

        if (dp[i][j] != -1) 
            return dp[i][j];

        int take_i = nums[i] + Math.min(
            solve(nums, i+2, j),
            solve(nums, i+1, j-1)
        );

        int take_j = nums[j] + Math.min(
            solve(nums, i+1, j-1),
            solve(nums, i, j-2)
        );

        return dp[i][j] = Math.max(take_i, take_j);
    }

    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        dp = new int[n][n];

        int sum = 0;
        for (int i=0; i<n; i++) {
            sum += nums[i];
            Arrays.fill(dp[i], -1);
        }

        int p1 = solve(nums, 0, n-1);
        int p2 = sum - p1;

        return p1 >= p2;
    }
}