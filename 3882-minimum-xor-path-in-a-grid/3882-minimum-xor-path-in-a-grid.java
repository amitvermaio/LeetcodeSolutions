import java.util.BitSet;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // dp[i][j] = BitSet of size 1024, index = XOR value, bit = reachable
        BitSet[][] dp = new BitSet[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new BitSet(1024);
            }
        }

        dp[0][0].set(grid[0][0]);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0)
                    continue;

                BitSet up = new BitSet(1024);    // [i-1][j]
                BitSet left = new BitSet(1024);  // [i][j-1]

                if (i > 0) {
                    up = (BitSet) dp[i - 1][j].clone();
                }
                if (j > 0) {
                    left = (BitSet) dp[i][j - 1].clone();
                }

                BitSet combined = (BitSet) up.clone();
                combined.or(left);               // OR operation

                for (int xorVal = 0; xorVal < 1024; xorVal++) {
                    if (combined.get(xorVal)) {
                        int resultXorVal = xorVal ^ grid[i][j];
                        dp[i][j].set(resultXorVal);
                    }
                }
            }
        }

        // dp[m-1][n-1]
        for (int xorVal = 0; xorVal < 1024; xorVal++) {
            if (dp[m - 1][n - 1].get(xorVal)) {
                return xorVal;
            }
        }

        return -1;
    }
}