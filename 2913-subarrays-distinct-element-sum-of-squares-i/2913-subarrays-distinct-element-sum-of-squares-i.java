import java.util.*;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int sum = 0;

        for (int i = 0; i < n; i++) {
            Set<Integer> vis = new HashSet<>();

            for (int j = i; j < n; j++) {
                vis.add(nums.get(j));

                int count = vis.size();
                sum += count * count;
            }
        }

        return sum;
    }
}