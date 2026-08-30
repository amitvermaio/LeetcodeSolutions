class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int minElIdx = 0, maxElIdx = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minElIdx]) minElIdx = i;
            if (nums[i] > nums[maxElIdx]) maxElIdx = i;
        }

        int left  = Math.min(minElIdx, maxElIdx);
        int right = Math.max(minElIdx, maxElIdx);

        return Math.min(left + 1 + n - right, Math.min(right + 1, n - left));
    }
}