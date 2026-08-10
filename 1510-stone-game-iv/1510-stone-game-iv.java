class Solution {
    int[] memo;
    private boolean solve(int m) {
        if (m == 0)
            return false;
        
        if (memo[m] != 0)
            return memo[m] == 1;

        for (int i=1; i*i<=m; i++) {
            int square = i*i;

            if (!(solve(m - square))) {
                memo[m] = 1;
                return true;
            }
        }

        memo[m] = -1;

        return false;
    }

    public boolean winnerSquareGame(int n) {
        memo = new int[n+1];
        return solve(n);
    }
}