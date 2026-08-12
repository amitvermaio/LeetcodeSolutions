class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int i=0, j=0;
        int maxlen = 1;
        Map<Integer, Integer> mp = new HashMap<>();
        while (j < n) {
            mp.put(nums[j], mp.getOrDefault(nums[j], 0) + 1);

            while (i<j && mp.get(nums[j]) > k) {
                mp.put(nums[i], mp.get(nums[i])-1);
                i++;
            }

            maxlen = Math.max(maxlen, j-i+1);
            j++;
        }

        return maxlen;
    }
}