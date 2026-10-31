class Solution {
    int m, n;
    int[][] grid;
    int[][][] dp;
    final int MOD = 1_000_000_007;

    private int solve(int r, int c, int d) { // d: 0 = (down), 1 = (right)
        if (r >= m || c >= n) return 0;
        if (r == m - 1 && c == n - 1) return 1;
        if (dp[r][c][d] != -1) return dp[r][c][d];

        int ans;
        if (grid[r][c] == 0) {
            ans = (solve(r+1, c, 0) + solve(r, c+1, 1)) % MOD;
        } else if (d == 1) {
            ans = solve(r+1, c, 0);
        } else {
            ans = solve(r, c+1, 1);
        }

        return dp[r][c][d] = ans;
    }

    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        dp = new int[m][n][2];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                dp[i][j][0] = dp[i][j][1] = -1;

        return solve(0, 0, 0);
    }
}