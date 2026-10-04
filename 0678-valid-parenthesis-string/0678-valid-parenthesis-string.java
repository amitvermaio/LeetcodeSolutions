class Solution {
    public boolean checkValidString(String s) {
        int n = s.length();
        int[][] t = new int[n][n];
        for (int[] row: t) {
            Arrays.fill(row, -1);
        }
        return isValidString(0, 0, s, t);
    }

    private boolean isValidString(int idx, int open, String str, int[][] t) {
        // If reached end of the string, check if all brackets are balanced
        if (idx == str.length()) {
            return (open == 0);
        }
        // If already computed, return memoized result
        if (t[idx][open] != -1) {
            return t[idx][open] == 1;
        }
        boolean isValid = false;
        // If encountering '*', try all possibilities
        if (str.charAt(idx) == '*') {
            isValid |= isValidString(idx + 1, open + 1, str, t); // Treat '*' as '('
            if (open > 0) {
                isValid |= isValidString(idx + 1, open - 1, str, t); // Treat '*' as ')'
            }
            isValid |= isValidString(idx + 1, open, str, t); // Treat '*' as empty
        } else {
            // Handle '(' and ')'
            if (str.charAt(idx) == '(') {
                isValid = isValidString(idx + 1, open + 1, str, t); // Increment count for '('
            } else if (open > 0) {
                isValid = isValidString(idx + 1, open - 1, str, t); // Decrement count for ')'
            }
        }

        // Memoize and return the result
        t[idx][open] = isValid ? 1 : 0;
        return isValid;
    }
}