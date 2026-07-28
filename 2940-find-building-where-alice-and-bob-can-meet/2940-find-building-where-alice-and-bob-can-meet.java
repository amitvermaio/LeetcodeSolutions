class Solution {
    int[] tree;

    void build(int[] arr, int node, int left, int right) {
        if (left == right) {
            tree[node] = left;
            return;
        }

        int mid = left + (right - left) / 2;
        build(arr, 2 * node + 1, left, mid);
        build(arr, 2 * node + 2, mid + 1, right);

        // FIX: tree[] me indices store hote hain, isliye arr ki values compare karni hongi.
        int leftIdx = tree[2 * node + 1];
        int rightIdx = tree[2 * node + 2];

        if (arr[leftIdx] >= arr[rightIdx]) {
            tree[node] = leftIdx;
        } else {
            tree[node] = rightIdx;
        }
    }

    int querySt(int[] arr, int node, int left, int right, int ql, int qr) {
        if (left > qr || right < ql) {
            return -1;
        }

        // FIX: Total overlap condition me right <= qr hona chahiye.
        if (ql <= left && right <= qr) {
            return tree[node];
        }

        int mid = (left + right) / 2;
        int leftIdx = querySt(arr, 2 * node + 1, left, mid, ql, qr);
        int rightIdx = querySt(arr, 2 * node + 2, mid + 1, right, ql, qr);

        if (leftIdx == -1) return rightIdx;
        if (rightIdx == -1) return leftIdx;

        return arr[leftIdx] >= arr[rightIdx] ? leftIdx : rightIdx;
    }

    int RMIQ(int[] arr, int n, int ql, int qr) {
        return querySt(arr, 0, 0, n - 1, ql, qr);
    }

    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length;
        tree = new int[4 * n];
        build(heights, 0, 0, n - 1);

        int sz = queries.length;
        int[] ans = new int[sz];
        int idx = 0;

        for (int[] q : queries) {
            int l = Math.min(q[0], q[1]);
            int r = Math.max(q[0], q[1]);

            /* this condition for 
                heights = [1, 2, 1, 2, 1, 2]
                query = [0, 2]
                heights[r] >= heights[l]   // 1 >= 1 -> true
                but 1 is not > 1
            */
            if (l == r) {
                ans[idx++] = l;
                continue;
            }

            if (heights[r] > heights[l]) {
                ans[idx++] = r;
                continue;
            }

            int max = Math.max(heights[q[0]], heights[q[1]]);
            int left = r + 1; // r+1 coz right side me dhundhna hai hume next greater element
            int right = n - 1;
            int resultIdx = -1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                int idxFromSt = RMIQ(heights, n, left, mid);
                // RMIQ -> right side max element index query

                // Agar query range me koi element nahi mila to idxFromSt = -1 ho sakta hai.
                if (idxFromSt != -1 && heights[idxFromSt] > max) {
                    resultIdx = idxFromSt;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            ans[idx++] = resultIdx;
        }

        return ans;
    }
}