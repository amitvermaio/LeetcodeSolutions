class Solution {
    Boolean[][] dp;
    private boolean isPalindrome(String s, int i, int j) {
        if (i >= j) {
            return true;
        }

        if (dp[i][j] != null) return dp[i][j];

        if (s.charAt(i) == s.charAt(j)) {
            return dp[i][j] = isPalindrome(s, i+1, j-1);
        }

        return dp[i][j] = false;
    }

    public String longestPalindrome(String s) {
        int n = s.length();
        int sp = 0; // store starting point
        int maxlen = 1;

        dp = new Boolean[n+1][n+1];

        for (int i=0; i<n-1; i++) {
            for (int j=i+1; j<n; j++) {
                if (j-i+1 > maxlen && isPalindrome(s, i, j)) {
                    maxlen = j-i+1;
                    sp = i;
                }
            }
        }

        return s.substring(sp, sp+maxlen);
    }
}