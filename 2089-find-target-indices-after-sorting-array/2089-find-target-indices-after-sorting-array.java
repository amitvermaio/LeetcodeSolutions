class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> ans = new ArrayList<>();
        
        int small = 0;
        int identical = 0;

        for (int num : nums) {
            if (num < target) {
                small++;
            } if (num == target) {
                identical++;
            }
        }

        for (int i=0; i<identical; i++) {
            ans.add(small+i);
        }

        return ans;
    }
}