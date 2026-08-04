class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        int max = -1;
        int min = 101;
        for (int num : nums) {
            set.add(num);
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        for (int i=min; i<=max; i++) {
            if (!set.contains(i)) {
                ans.add(i);
            }
        }

        return ans;
    }
}