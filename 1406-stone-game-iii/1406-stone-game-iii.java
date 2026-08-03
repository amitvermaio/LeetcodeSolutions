class Solution {
    int[] dp;
    int n;

    private int solve(int[] stone, int i) {
        if (i >= n)
            return 0;

        if (dp[i] != Integer.MIN_VALUE)
            return dp[i];

        int s2 = solve(stone, i + 2);
        int s3 = solve(stone, i + 3);
        int s4 = solve(stone, i + 4);
        int s5 = solve(stone, i + 5);
        int s6 = solve(stone, i + 6);

        int takeOne = stone[i] + Math.min(s2, Math.min(s3, s4));

        int takeTwo = Integer.MIN_VALUE;
        if (i + 1 < n) {
            takeTwo = stone[i] + stone[i+1]
                    + Math.min(s3, Math.min(s4, s5));
        }

        int takeThree = Integer.MIN_VALUE;
        if (i + 2 < n) {
            takeThree = stone[i] + stone[i+1] + stone[i+2]
                    + Math.min(s4, Math.min(s5, s6));
        }

        return dp[i] = Math.max(takeOne, Math.max(takeTwo, takeThree));
    }

    public String stoneGameIII(int[] stoneValue) {
        n = stoneValue.length;
        int total = 0;
        dp = new int[n];

        for (int i = 0; i < n; i++) {
            total += stoneValue[i];
            dp[i] = Integer.MIN_VALUE;
        }

        int alice = solve(stoneValue, 0);
        int bob = total - alice;

        if (alice == bob)
            return "Tie";

        return alice > bob ? "Alice" : "Bob";
    }
}