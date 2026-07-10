class Solution {
    int rows;
    int cols;
    int[][] ancestorTable;

    private int customUpperBound(int[][] arr, int target) {
        int n = arr.length;
        int l = 0;
        int r = n - 1;
        int result = 0;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (arr[mid][0] <= target) {
                result = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return result;
    }

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // arr : {value, originalIndex}
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (x, y) -> Integer.compare(x[0], y[0]));

        int[] nodeToIdx = new int[n];
        for (int i = 0; i < n; i++) {
            int node = arr[i][1];
            nodeToIdx[node] = i;
        }

        rows = n;
        cols = (int) (Math.log(n) / Math.log(2)) + 1;
        ancestorTable = new int[rows][cols];

        // Fill 0th column first
        for (int node = 0; node < n; node++) { // nlogn
            int farthestIdxOneHop = customUpperBound(arr, arr[node][0] + maxDiff);
            ancestorTable[node][0] = farthestIdxOneHop;
        }

        // Fill remaining columns
        for (int j = 1; j < cols; j++) { // logn
            for (int node = 0; node < n; node++) { // n
                ancestorTable[node][j] = ancestorTable[ ancestorTable[node][j - 1] ][j - 1];
            }
        }

        int[] result = new int[queries.length];
        int idx = 0;
        for (int[] query : queries) { // O(q)
            int u = query[0];
            int v = query[1];

            int a = nodeToIdx[u];
            int b = nodeToIdx[v];
            if (a == b) {
                result[idx++] = 0;
                continue;
            }

            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            int curr  = a;
            int jumps = 0;

            for (int j = cols - 1; j >= 0; j--) { // log(n)
                if (ancestorTable[curr][j] < b) {
                    curr = ancestorTable[curr][j];
                    jumps += (1 << j); // pow(2, j)
                }
            }

            if (ancestorTable[curr][0] >= b) {
                result[idx++] = jumps + 1;
            } else {
                result[idx++] = -1;
            }
        }

        return result;
    }
}