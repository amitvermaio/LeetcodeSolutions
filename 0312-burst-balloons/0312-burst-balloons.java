class Solution {
    int n;
    Integer[][] dp;

    private int solve(int[] nums, int left, int right) {
        if (left > right) return 0;
        if (dp[left][right] != null) return dp[left][right];

        int best = 0;
        for (int i = left; i <= right; i++) {
            int coins = nums[i];
            if (left > 0) coins *= nums[left - 1];
            if (right < n-1) coins *= nums[right + 1];

            coins += solve(nums, left, i-1) + solve(nums, i+1, right);
            best = Math.max(best, coins);
        }

        return dp[left][right] = best;
    }

    public int maxCoins(int[] nums) {
        n = nums.length;
        dp = new Integer[n][n];
        return solve(nums, 0, n - 1);
    }
}