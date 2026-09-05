class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));

        // System.out.println(Arrays.deepToString(pairs));

        int n = pairs.length;

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i=0; i<n; i++) {
            for (int j=i-1; j>=0; j--) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
}