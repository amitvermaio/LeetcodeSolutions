class Solution {
    int[] dp;
    private int solve(int[] nums, int i) {
        if (i == nums.length) return 1;
        if (nums[i] == 0) return 0;

        if (dp[i] != -1) {
            return dp[i];
        }

        int ans = 0;
        ans += solve(nums, i+1);
        if (i+1<nums.length && nums[i]*10+nums[i+1] <= 26) {
            ans += solve(nums, i+2);
        }

        return dp[i] = ans;
    }

    public int numDecodings(String s) {
        int n = s.length();
        int[] nums = new int[n];
        dp = new int[n];
        for (int i=0; i<n; i++) {
            nums[i] = s.charAt(i) - '0';
            dp[i] = -1;
        }

        return solve(nums, 0);
    }
}