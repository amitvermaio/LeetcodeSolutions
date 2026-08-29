class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        int[] vec = nums.clone();
        Arrays.sort(vec);

        int groupNum = 0;

        Map<Integer, Integer> numToGroup = new HashMap<>();
        Map<Integer, Queue<Integer>> groupToList = new HashMap<>();

        numToGroup.put(vec[0], groupNum);

        groupToList.put(groupNum, new LinkedList<>());
        groupToList.get(groupNum).offer(vec[0]);

        for (int i = 1; i < n; i++) {
            if (Math.abs(vec[i] - vec[i - 1]) > limit) {
                groupNum++;

                groupToList.put(groupNum, new LinkedList<>());
            }

            numToGroup.put(vec[i], groupNum);
            groupToList.get(groupNum).offer(vec[i]);
        }

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int group = numToGroup.get(num);

            result[i] = groupToList.get(group).poll();
        }

        return result;
    }
}