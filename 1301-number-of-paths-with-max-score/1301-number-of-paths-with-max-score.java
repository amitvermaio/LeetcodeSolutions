class Solution {
    private static final int MOD = 1_000_000_007;
    private int n;

    private int[][] scoreMemo;
    private int[][] waysMemo;
    private boolean[][] vis;
    private List<String> board;

    public int[] pathsWithMaxScore(List<String> board) {
        this.board = board;
        this.n = board.size();

        scoreMemo = new int[n][n];
        waysMemo = new int[n][n];
        vis = new boolean[n][n];

        dfs(0, 0);

        if (waysMemo[0][0] == 0) {
            return new int[]{0, 0};
        }

        return new int[]{scoreMemo[0][0], waysMemo[0][0]};
    }

    private void dfs(int r, int c) {
        if (r >= n || c >= n || board.get(r).charAt(c) == 'X')
            return;

        if (vis[r][c])
            return;

        vis[r][c] = true;

        if (r == n - 1 && c == n - 1) {
            scoreMemo[r][c] = 0;
            waysMemo[r][c] = 1;
            return;
        }

        int[][] dir = {{1, 0}, {0, 1}, {1, 1}};

        int best = -1;
        int ways = 0;

        for (int[] d : dir) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (nr >= n || nc >= n)
                continue;

            dfs(nr, nc);

            if (waysMemo[nr][nc] == 0)
                continue;

            if (scoreMemo[nr][nc] > best) {
                best = scoreMemo[nr][nc];
                ways = waysMemo[nr][nc];
            } else if (scoreMemo[nr][nc] == best) {
                ways = (ways + waysMemo[nr][nc]) % MOD;
            }
        }

        if (best == -1) {
            scoreMemo[r][c] = 0;
            waysMemo[r][c] = 0;
            return;
        }

        char ch = board.get(r).charAt(c);
        int val = (ch == 'S' || ch == 'E') ? 0 : ch - '0';

        scoreMemo[r][c] = best + val;
        waysMemo[r][c] = ways;
    }
}