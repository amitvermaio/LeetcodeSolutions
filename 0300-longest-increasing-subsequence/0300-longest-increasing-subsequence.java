class Solution {
    public int lengthOfLIS(int[] nums) {
        List<Integer> tails = new ArrayList<>();
        for (int num : nums) {
            int i = Collections.binarySearch(tails, num);

            if (i < 0) i = -(i+1);

            if (i == tails.size()) {
                tails.add(num);
            } else {
                tails.set(i, num);
            }
        }
        return tails.size();
    }
}