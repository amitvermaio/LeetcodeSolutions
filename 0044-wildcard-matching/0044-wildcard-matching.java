class Solution {
    int m, n;
    int[][] dp;
    private boolean solve(String s, String p, int i, int j) {
        if (i==m && j==n) return true;
        if (i == m) {
            for (int k=j; k<p.length(); k++) {
                char c = p.charAt(k);
                if (c != '*') return false;
            }
            return true;
        }

        if (i>=m || j>=n) return false;

        if (dp[i][j] != -1) {
            return dp[i][j]==1 ? true : false;
        }

        boolean isEq = s.charAt(i)==p.charAt(j) || p.charAt(j)=='?';

        if (p.charAt(j) == '*') {
            boolean opt1 = solve(s, p, i, j+1);
            boolean opt2 = solve(s, p, i+1, j);
            boolean opt3 = solve(s, p, i+1, j+1);
            dp[i][j] = opt1 || opt2 || opt3 ? 1 : 0; 
            return opt1 || opt2 || opt3;
        }

        dp[i][j] = isEq && solve(s, p, i+1, j+1) ? 1 : 0;

        return dp[i][j]==1 ? true : false;
    }

    public boolean isMatch(String s, String p) {
        m = s.length();
        n = p.length();
        dp = new int[m][n];
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                dp[i][j] = -1;
            }
        }

        return solve(s, p, 0, 0);
    }
}