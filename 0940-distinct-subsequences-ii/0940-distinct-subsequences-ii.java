class Solution {
    static final int MOD = 1_000_000_007;
    public int distinctSubseqII(String s) {
        int n = s.length();

        long[] dp = new long[n + 1];
        int[] last = new int[26];

        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            int c = s.charAt(i - 1) - 'a';

            dp[i] = (2 * dp[i - 1]) % MOD;

            if (last[c] != 0) {
                dp[i] = (dp[i] - dp[last[c] - 1] + MOD) % MOD;
            }

            last[c] = i;
        }

        return (int) ((dp[n] - 1 + MOD) % MOD);
    }
}