import java.util.*;

class Solution {
    private TreeMap<Integer, Integer> left = new TreeMap<>();
    private TreeMap<Integer, Integer> right = new TreeMap<>();
    private int leftCnt = 0, rightCnt = 0;

    private void add(TreeMap<Integer, Integer> m, int v) {
        m.merge(v, 1, Integer::sum);
    }

    private void remove(TreeMap<Integer, Integer> m, int v) {
        int c = m.get(v);
        if (c == 1) m.remove(v);
        else m.put(v, c - 1);
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        final long INF = (long) 1e15;
        long[] cost = new long[n + 1];

        // ---------- Part A: cost of every window using sliding median ----------
        long leftSum = 0, rightSum = 0;
        int i = 0, j = 0;

        while (j < n) {
            // insert nums[j]
            if (leftCnt <= rightCnt) {
                add(left, nums[j]);
                leftCnt++;
                leftSum += nums[j];
            } else {
                add(right, nums[j]);
                rightCnt++;
                rightSum += nums[j];
            }

            // balancing: max(left) <= min(right)
            if (leftCnt > 0 && rightCnt > 0) {
                int maxLeft = left.lastKey();
                int minRight = right.firstKey();
                if (maxLeft > minRight) {
                    remove(left, maxLeft);
                    remove(right, minRight);
                    add(left, minRight);
                    add(right, maxLeft);
                    leftSum += (long) minRight - maxLeft;
                    rightSum += (long) maxLeft - minRight;
                }
            }

            // window of size x is ready
            if (j - i + 1 == x) {
                long median = left.lastKey();
                cost[i] = (leftCnt * median - leftSum) + (rightSum - rightCnt * median);

                // remove nums[i] as the window slides
                if (left.containsKey(nums[i])) {
                    remove(left, nums[i]);
                    leftCnt--;
                    leftSum -= nums[i];
                } else {
                    remove(right, nums[i]);
                    rightCnt--;
                    rightSum -= nums[i];
                }
                i++;
            }
            j++;
        }

        // ---------- Part B: pick k non-overlapping windows (bottom-up DP, avoids deep recursion) ----------
        // dp[c][idx] = min cost to place windows c..k-1 using indices idx..n-1
        long[][] dp = new long[k + 1][n + 2];
        for (int idx = 0; idx <= n + 1; idx++) dp[k][idx] = 0;

        for (int c = k - 1; c >= 0; c--) {
            dp[c][n + 1] = INF;
            dp[c][n] = INF;
            for (int idx = n - 1; idx >= 0; idx--) {
                long cellsNeeded = (long) (k - c) * x;
                if (idx + cellsNeeded - 1 >= n) {
                    dp[c][idx] = INF;
                    continue;
                }
                long take = cost[idx] + dp[c + 1][idx + x];
                long skip = dp[c][idx + 1];
                dp[c][idx] = Math.min(take, skip);
            }
        }

        return dp[0][0];
    }
}