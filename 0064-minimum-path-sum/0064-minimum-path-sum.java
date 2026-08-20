class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[2], b[2])
        );

        dist[0][0] = grid[0][0];
        pq.offer(new int[]{0, 0, grid[0][0]});

        int[] dr = {0, 1};
        int[] dc = {1, 0};

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            int r = curr[0];
            int c = curr[1];
            int sum = curr[2];

            if (sum > dist[r][c]) {
                continue;
            }

            if (r==m-1 && c==n-1) {
                return sum;
            }

            for (int i=0; i<2; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < m && nc < n) {
                    int newSum = sum + grid[nr][nc];

                    if (newSum < dist[nr][nc]) {
                        dist[nr][nc] = newSum;
                        pq.offer(new int[]{nr, nc, newSum});
                    }
                }
            }
        }

        return 0;
    }
}