
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];

        long k = (long) k1 + k2;
        int maxDiff = 0;
        long totalDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
            totalDiff += diff[i];
        }

        if (k >= totalDiff) return 0;

        int low = 0, high = maxDiff;

        // Find the minimum maximum difference achievable
        while (low < high) {
            int mid = low + (high - low) / 2;
            long ops = 0;

            for (int d : diff) {
                if (d > mid) {
                    ops += d - mid;
                }
            }

            if (ops <= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        int target = low;
        long used = 0;
        long ans = 0;

        for (int d : diff) {
            if (d > target) {
                used += d - target;
            }

            long val = Math.min(d, target);
            ans += val * val;
        }

        // Use remaining operations to reduce target to target - 1
        long remaining = k - used;

        ans -= remaining * (2L * target - 1);

        return ans;
    }
}
