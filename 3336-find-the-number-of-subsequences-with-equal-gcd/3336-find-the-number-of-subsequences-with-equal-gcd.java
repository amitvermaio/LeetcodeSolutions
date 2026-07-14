class Solution {
    int[][][] dp;
    final static int MOD = 1_000_000_007;

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a%b);
    }

    private int solve(int[] nums, int i, int gcd1, int gcd2) {
        if (i == nums.length) {
            if (gcd1!=0 && gcd1==gcd2) { 
                return 1;
            }

            return 0;
        }

        if (dp[i][gcd1][gcd2] != -1) {
            return dp[i][gcd1][gcd2];
        }

        int skip = solve(nums, i+1, gcd1, gcd2);
        int take1 = solve(nums, i+1, gcd(gcd1, nums[i]), gcd2);
        int take2 = solve(nums, i+1, gcd1, gcd(gcd2, nums[i]));

        return dp[i][gcd1][gcd2] = ((skip + take1) % MOD + take2) % MOD;
    }

    public int subsequencePairCount(int[] nums) {
        int n = nums.length;
        
        // Size 201 ensures ki max value 200 tak ke indices access ho sakein.
        // First dimension 'n' tak rakhna for 'i'.
        dp = new int[n][201][201]; 
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 201; j++) {
                for (int k = 0; k < 201; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        return solve(nums, 0, 0, 0);
    }
}