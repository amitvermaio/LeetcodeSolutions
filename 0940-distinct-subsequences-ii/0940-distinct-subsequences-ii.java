import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    int[] dp;
    int[] prev;

    int solve(int i) {
        // dp[i] = number of distinct subsequences
        // using first i characters, including empty subsequence
        if (i == 0)
            return 1;

        if (dp[i] != -1)
            return dp[i];

        // Every existing subsequence:
        // 1. don't take s[i-1]
        // 2. take s[i-1]
        long ans = 2L * solve(i - 1);

        // If this character appeared before,
        // some subsequences are duplicates.
        if (prev[i] != -1) {
            ans -= solve(prev[i] - 1);
        }

        ans = (ans % MOD + MOD) % MOD;

        return dp[i] = (int) ans;
    }

    public int distinctSubseqII(String s) {
        int n = s.length();

        dp = new int[n + 1];
        Arrays.fill(dp, -1);

        // prev[i] = 1-based position of previous occurrence
        // of s[i-1]
        prev = new int[n + 1];
        Arrays.fill(prev, -1);

        int[] last = new int[26];
        Arrays.fill(last, -1);

        for (int i = 1; i <= n; i++) {
            int c = s.charAt(i - 1) - 'a';

            prev[i] = last[c];

            last[c] = i;
        }

        // solve includes empty subsequence
        // so subtract 1
        return (solve(n) - 1 + MOD) % MOD;
    }
}