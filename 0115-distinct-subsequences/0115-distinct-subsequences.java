class Solution {
    int m, n;
    int[][] dp;
    int solve(String s, String t, int i, int j) {
        if (j == n)
            return 1;
        if (i == m)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];
        
        int skip = solve(s, t, i+1, j);
        int take = 0;
        if (s.charAt(i) == t.charAt(j))
            take = solve(s, t, i+1, j+1);
        
        return dp[i][j] = skip + take;
    }

    public int numDistinct(String s, String t) {
        m = s.length();
        n = t.length();
        
        if (n > m)
            return 0;

        dp = new int[m][n];
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                dp[i][j] = -1;
            }
        }

        return solve(s, t, 0, 0);
    }
}