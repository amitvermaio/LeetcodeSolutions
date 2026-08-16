class Solution {
    public int longestSubsequence(int[] nums) {
        // Corner Case: All Elements Zero! -> that's why I took flag
        int n = nums.length;

        int resultXor = 0;
        boolean flag = false;

        for (int num : nums) {
            resultXor ^= num;
            if (num != 0)
                flag = true;
        }

        if (!flag)
            return 0;
        return resultXor==0 ? n-1 : n;
    }
}