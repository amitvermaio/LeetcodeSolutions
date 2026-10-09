class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int open = 0;
        int ans = 0;
        int i = 0;

        while (i < n) {
            char c = s.charAt(i);

            if (c == '(') {
                open++;
                i++;
            } else {
                // c == ')'
                boolean pairFound = (i + 1 < n && s.charAt(i + 1) == ')');
                if (pairFound) {
                    i += 2;
                } else {
                    ans++;      // need one more ')'
                    i += 1;
                }

                if (open > 0) {
                    open--;
                } else {
                    ans++;      // need a matching '('
                }
            }
        }

        if (open > 0) {
            ans += 2 * open;
        }

        return ans;
    }
}