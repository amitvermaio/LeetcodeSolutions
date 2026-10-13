class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        for (int i=0; i<n; i++) {
            if (nums[i] < 0) {
                nums[i] = -nums[i];
            }
        }

        long ans = 0;
        Arrays.sort(nums);
        int trav = n%2 == 0 ? n/2 : n/2 + 1;

        for (int i=0; i<n; i++) {
            if (i < n-trav) {
                ans -= (nums[i] * nums[i]);
            } else {
                ans += (nums[i] * nums[i]);
            }
        }

        return ans;
    }
}