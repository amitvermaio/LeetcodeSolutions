class Solution {
    int[][] dp; 

    private boolean palindrome(String s, int i, int j) {
        if (i >= j)
            return true;

        if (dp[i][j] != -1)
            return dp[i][j] == 1;

        if (s.charAt(i) == s.charAt(j)) {
            dp[i][j] = palindrome(s, i + 1, j - 1) ? 1 : 0;
            return dp[i][j] == 1;
        }

        dp[i][j] = 0;
        return false;
    }

    public String longestPalindrome(String s) {
        int n = s.length();
        dp = new int[n][n];

        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], -1);

        int sp = 0;
        int maxLen = 1;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (j - i + 1 > maxLen && palindrome(s, i, j)) {
                    maxLen = j - i + 1;
                    sp = i;
                }
            }
        }

        return s.substring(sp, sp + maxLen);
    }
}